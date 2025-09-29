package lab1.ex1;

public abstract class Employe {
    String name;

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

}

    