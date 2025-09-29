package lab1.ex1;

public class Paie {
    public static void main(String args[]) {
        Employe employes[] = new Employe[5];

        employes[0] = new Commercial("Dumon", 15000, 1900);
        employes[1] = new Commercial("Cerge", 75000, 1200);
        employes[2] = new Commercial("John", 25000, 1500);

        employes[3] = new Commercial("Dupond");
        employes[4] = new Directeur("Durand", 50000, 2000, 10000);
        for (Employe e : employes) {
            System.out.println(e.getName() + " gagne " + e.getSalary() + " euros.");
        }
    }
}