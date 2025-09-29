package lab1.ex1;

public class Employe30 extends Employe {

    private double nb_hours;
    private double tarif_hour;

    public Employe30(String name, double nb_hours, double tarif_hour) {
        super(name);
        this.nb_hours = nb_hours;
        this.tarif_hour = tarif_hour;
    }
    
    public double getSalary() {
        if (nb_hours <= 35) {
            return nb_hours * tarif_hour;
        }
        else {
            return (35 * tarif_hour) + ((nb_hours - 35) * tarif_hour * 1.3);
        }
    }

    public void SetSalaryInfos(double nb_hours, double tarif_hour){ 
        this.nb_hours = nb_hours;
        this.tarif_hour = tarif_hour;
    }
}