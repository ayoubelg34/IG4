package tp.introspection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public final class InterfaceExtractor {

    private InterfaceExtractor() {}

    public static void main(String[] args) {
        Map<Class<?>, InterfaceModel> cache = new HashMap<>();
        List<Class<?>> targets = Arrays.asList(Appartement.class, AppartementResidence.class);

        for (Class<?> target : targets) {
            InterfaceModel model = buildModel(target, cache);
            System.out.println(model.render());
        }
    }

    private static InterfaceModel buildModel(Class<?> clazz, Map<Class<?>, InterfaceModel> cache) {
        InterfaceModel cached = cache.get(clazz);
        if (cached != null) {
            return cached;
        }

        InterfaceModel model = new InterfaceModel(clazz);
        cache.put(clazz, model);

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            InterfaceModel superModel = buildModel(superClass, cache);
            model.extendedInterfaces.add(superModel.interfaceName);
            model.inheritedSignatures.addAll(superModel.allSignatures);
        }

        for (Class<?> iface : clazz.getInterfaces()) {
            model.extendedInterfaces.add(iface.getSimpleName());
            model.inheritedSignatures.addAll(MethodSignature.fromInterface(iface));
        }

        for (Method method : clazz.getDeclaredMethods()) {
            int modifiers = method.getModifiers();
            if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                continue;
            }
            MethodSignature signature = MethodSignature.from(method);
            if (!model.inheritedSignatures.contains(signature)) {
                model.ownSignatures.add(signature);
            }
        }

        model.consolidateSignatures();
        return model;
    }

    private static final class InterfaceModel {
        private final Class<?> source;
        private final String interfaceName;
        private final List<String> extendedInterfaces = new ArrayList<>();
        private final Set<MethodSignature> inheritedSignatures = new LinkedHashSet<>();
        private final List<MethodSignature> ownSignatures = new ArrayList<>();
        private final Set<MethodSignature> allSignatures = new LinkedHashSet<>();

        private InterfaceModel(Class<?> source) {
            this.source = source;
            this.interfaceName = "I" + source.getSimpleName();
        }

        private void consolidateSignatures() {
            allSignatures.addAll(inheritedSignatures);
            allSignatures.addAll(ownSignatures);
        }

        private String render() {
            StringBuilder builder = new StringBuilder();
            builder.append("interface ").append(interfaceName);

            if (!extendedInterfaces.isEmpty()) {
                String extendsClause = extendedInterfaces.stream()
                        .collect(Collectors.joining(", "));
                builder.append(" extends ").append(extendsClause);
            }

            builder.append(" {\n");
            if (!ownSignatures.isEmpty()) {
                for (MethodSignature signature : ownSignatures.stream()
                        .sorted()
                        .collect(Collectors.toList())) {
                    builder.append("    ").append(signature.toDeclaration()).append("\n");
                }
            }
            builder.append("}\n");
            builder.append("source: ").append(source.getName()).append("\n");
            return builder.toString();
        }
    }

    private static final class MethodSignature implements Comparable<MethodSignature> {
        private final String name;
        private final String returnType;
        private final List<String> parameterTypes;
        private final Set<String> thrownTypes;

        private MethodSignature(String name, String returnType, List<String> parameterTypes, Set<String> thrownTypes) {
            this.name = name;
            this.returnType = returnType;
            this.parameterTypes = parameterTypes;
            this.thrownTypes = thrownTypes;
        }

        static MethodSignature from(Method method) {
            List<String> params = Arrays.stream(method.getParameterTypes())
                    .map(MethodSignature::typeToString)
                    .collect(Collectors.toList());
            Set<String> exceptions = Arrays.stream(method.getExceptionTypes())
                    .map(MethodSignature::typeToString)
                    .collect(Collectors.toCollection(TreeSet::new));
            return new MethodSignature(
                    method.getName(),
                    typeToString(method.getReturnType()),
                    params,
                    exceptions);
        }

        static Set<MethodSignature> fromInterface(Class<?> iface) {
            Set<MethodSignature> signatures = new HashSet<>();
            for (Method method : iface.getMethods()) {
                int modifiers = method.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    signatures.add(from(method));
                }
            }
            return signatures;
        }

        String toDeclaration() {
            String params = "";
            for (int i = 0; i < parameterTypes.size(); i++) {
                if (i > 0) {
                    params += ", ";
                }
                params += parameterTypes.get(i) + " param" + i;
            }
            String throwsClause = "";
            if (!thrownTypes.isEmpty()) {
                throwsClause = " throws " + String.join(", ", thrownTypes);
            }
            return String.format("public %s %s(%s)%s;", returnType, name, params, throwsClause);
        }

        private static String typeToString(Class<?> type) {
            if (type.isArray()) {
                return typeToString(type.getComponentType()) + "[]";
            }
            return type.getSimpleName();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodSignature)) {
                return false;
            }
            MethodSignature other = (MethodSignature) obj;
            return name.equals(other.name)
                    && returnType.equals(other.returnType)
                    && parameterTypes.equals(other.parameterTypes)
                    && thrownTypes.equals(other.thrownTypes);
        }

        @Override
        public int hashCode() {
            return name.hashCode() * 31 + parameterTypes.hashCode();
        }

        @Override
        public int compareTo(MethodSignature other) {
            int nameCmp = name.compareTo(other.name);
            if (nameCmp != 0) {
                return nameCmp;
            }
            int paramCmp = Integer.compare(parameterTypes.size(), other.parameterTypes.size());
            if (paramCmp != 0) {
                return paramCmp;
            }
            for (int i = 0; i < parameterTypes.size(); i++) {
                int cmp = parameterTypes.get(i).compareTo(other.parameterTypes.get(i));
                if (cmp != 0) {
                    return cmp;
                }
            }
            return returnType.compareTo(other.returnType);
        }
    }
}
