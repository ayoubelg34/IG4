package lab2;

import java.util.Comparator;

public class CompareSal implements Comparator {
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Employe && o2 instanceof Employe) {
            Employe emp1 = (Employe) o1;
            Employe emp2 = (Employe) o2;
            return Double.compare(emp1.getSalary(), emp2.getSalary());
        }
        return 0;
    }
}
