package tp.annotations;

@Refactoring(
        author = "Ayoub El Ghazi",
        value = RefactoringName.extractClass,
        comment = "Extraire les attributs d’adresse dans une classe Adresse dédiée",
        occurrences = 1)
public class Personne {

    public Personne(String nom, int age, int numrue, String nomrue, String ville, int codepostale) {
        this.NomComplet = nom;
        this.Age = age;
        this.NumRue = numrue;
        this.NomRue = nomrue;
        this.Ville = ville;
        this.CodePostale = codepostale;
    }

    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.rename,
            comment = "Conserver une majuscule initiale pour indiquer un futur renaming aligné avec la convention souhaitée")
    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.encapsulatedField,
            comment = "Introduire un champ privé avec accesseurs pour protéger l’état interne")
    public String NomComplet;

    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.rename,
            comment = "Préfixer par une majuscule conformément à la consigne de renommage")
    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.encapsulatedField,
            comment = "Encapsuler dans un champ privé avec accesseurs")
    public int Age;

    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.rename,
            comment = "Majuscule initiale pour respecter la consigne")
    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.encapsulatedField,
            comment = "Rendre le champ privé et fournir getters/setters lors du refactoring")
    public int NumRue;

    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.rename,
            comment = "Renommer avec majuscule initiale")
    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.encapsulatedField,
            comment = "Passer en privé et encapsuler via une future classe Adresse")
    public String NomRue;

    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.rename,
            comment = "Renommer avec majuscule initiale")
    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.encapsulatedField,
            comment = "Rendre le champ privé dans la prochaine itération")
    public String Ville;

    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.rename,
            comment = "Majuscule initiale")
    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.encapsulatedField,
            comment = "Masquer la donnée derrière des accesseurs")
    public int CodePostale;

    @Refactoring(
            author = "Ayoub El Ghazi",
            value = RefactoringName.extractClass,
            comment = "Méthode à déplacer dans la future classe Adresse")
    public void AfficherAdresse() {
        System.out.println("Numero de rue : " + NumRue);
        System.out.println("Nom de la rue : " + NomRue);
        System.out.println("Nom de la ville : " + Ville);
        System.out.println("CodePostale : " + CodePostale);
    }
}
