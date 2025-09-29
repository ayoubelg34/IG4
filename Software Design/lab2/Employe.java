package lab2;

public abstract class Employe implements Comparable {
    private String name;

    public Employe(String name) {
        this.name = name;
    }

    public String setName() {
        return name;
    }
 
    public abstract double getSalary();
    
    public String getName() {
        return name;
    }

    public int compareTo(Object e) {
        if (this.name.compareTo(((Employe) e).getName()) > 0) {
            return 1;
        } else if (this.name.compareTo(((Employe) e).getName()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
