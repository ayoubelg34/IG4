package lab1.ex1;

public class Commercial extends Employe {
    private double chiffreAffaires;
    private double FixSalary;

    public Commercial(String name, double chiffreAffaires, double FixSalary) {
        super(name);
        this.chiffreAffaires = chiffreAffaires;
        this.FixSalary = FixSalary;
    }

    public Commercial(String name) {
        super(name);
        
    }

    public double getSalary() {
        return FixSalary + (0.01 * chiffreAffaires);
    }

    public void SetSalaryInfos(double chiffreAffaires, double FixSalary){ 
        this.chiffreAffaires = chiffreAffaires;
        this.FixSalary = FixSalary;
    }
}
