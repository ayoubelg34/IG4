package tp.annotations;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public final class RefactoringInspector {

    private RefactoringInspector() {}

    public static void main(String[] args) {
        Class<?> target = Personne.class;
        System.out.println("=== Inspection de " + target.getSimpleName() + " ===");
        inspect(target);
    }

    public static void inspect(Class<?> target) {
        printAnnotations(target, "Classe");

        for (Field field : target.getDeclaredFields()) {
            printAnnotations(field, "Attribut " + field.getName());
        }

        for (Method method : target.getDeclaredMethods()) {
            printAnnotations(method, "Méthode " + signatureFor(method));
        }
    }

    private static void printAnnotations(AnnotatedElement element, String label) {
        Refactoring[] annotations = element.getAnnotationsByType(Refactoring.class);
        if (annotations.length == 0) {
            return;
        }
        System.out.println(label + " :");
        for (Refactoring ref : annotations) {
            System.out.printf(
                    "  - auteur=%s, refactoring=%s, occurrences=%d, eclipse=%s, commentaire=\"%s\"%n",
                    ref.author(),
                    ref.value(),
                    ref.occurrences(),
                    ref.presentInEclipse(),
                    ref.comment());
        }
    }

    private static String signatureFor(Method method) {
        String params = Arrays.stream(method.getParameterTypes())
                .map(Class::getSimpleName)
                .reduce((left, right) -> left + ", " + right)
                .orElse("");
        return method.getName() + "(" + params + ")";
    }
}
