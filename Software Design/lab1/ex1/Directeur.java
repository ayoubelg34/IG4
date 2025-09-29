package lab1.ex1;

public class Directeur extends Employe {
    private double fixe;
    private double chiffreAffaires;

    public Directeur(String name, double chiffreAffaires, double fixe, double pourcentage) {
        super(name) ;
        this.fixe = fixe;
        this.chiffreAffaires = chiffreAffaires;
    }

    public static Directeur createDirecteur(String name, double chiffreAffaires, double fixe, double pourcentage) {
        return new Directeur(name, chiffreAffaires, fixe, pourcentage);
    }

    
    @Override
    public double getSalary() {
        return fixe + (0.004 * chiffreAffaires);
    }
}