package lab2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.Iterator;

public class Paie {
    public static void main(String args[]) {
        List employes = new ArrayList();

        employes.add(new Commercial("Dumon", 15000, 1900));
        employes.add(new Commercial("Cerge", 75000, 1200));
        employes.add(new Commercial("John", 25000, 1500));
        employes.add(new Commercial("Dupond"));
        employes.add(new Directeur("Durand", 50000, 2000, 10000));

        Employe lastCommercial = new Commercial("James", 28000, 1200);

        
        Entreprise entreprise1 = new Entreprise("Polytech", 8);
        Entreprise entreprise2 = new Entreprise("Apple", 5);

        
        try {
            entreprise1.addEmploye(lastCommercial);
            System.out.println("Ajouté: " + lastCommercial.getName());
        }
        catch(Exception e1) {
            e1.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            Employe extra = new Commercial("ExtraCommercial" + i, 10000 + i * 1000, 1000);
            try {
                entreprise1.addEmploye(extra);
                System.out.println("Ajouté: " + extra.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }



        try {
            entreprise1.addEmploye(new Commercial("Alice"));
            System.out.println("Ajouté: Alice");
        } catch (Exception e) {
            if (e instanceof EntrepriseSatureDeCommerciauxException) {
                EntrepriseSatureDeCommerciauxException ex = (EntrepriseSatureDeCommerciauxException) e;
                System.out.println("Erreur: " + ex.getMessage());
                Entreprise ent = ex.getEntreprise();
                System.out.println("Entreprise max commerciaux: " + ent.getMaxCommerciaux());
                System.out.println("Commerciaux actuels: " + ent.getNbCommerciaux());
                System.out.println("Liste des commerciaux: " + ent.listCommerciaux());
            } else {
                e.printStackTrace();
            }
        }

        try {
            entreprise1.addEmploye(new Commercial("Bob"));
            System.out.println("Ajouté: Bob");
        } catch (Exception e) {
            if (e instanceof EntrepriseSatureDeCommerciauxException) {
                EntrepriseSatureDeCommerciauxException ex = (EntrepriseSatureDeCommerciauxException) e;
                System.out.println("Erreur: " + ex.getMessage());
                Entreprise ent = ex.getEntreprise();
                System.out.println("Entreprise max commerciaux: " + ent.getMaxCommerciaux());
                System.out.println("Commerciaux actuels: " + ent.getNbCommerciaux());
                System.out.println("Liste des commerciaux: " + ent.listCommerciaux());
            } else {
                e.printStackTrace();
            }
        }

        try {
            entreprise1.addEmploye(new Commercial("Charlie"));
            System.out.println("Ajouté: Charlie");
        } catch (Exception e) {
            if (e instanceof EntrepriseSatureDeCommerciauxException) {
                EntrepriseSatureDeCommerciauxException ex = (EntrepriseSatureDeCommerciauxException) e;
                System.out.println("Erreur: " + ex.getMessage());
                Entreprise ent = ex.getEntreprise();
                System.out.println("Entreprise max commerciaux: " + ent.getMaxCommerciaux());
                System.out.println("Commerciaux actuels: " + ent.getNbCommerciaux());
                System.out.println("Liste des commerciaux: " + ent.listCommerciaux());
            } else {
                e.printStackTrace();
            }
        }

        // Add employees to entreprise2
        try {
            entreprise2.addEmploye((Employe) employes.get(0)); // Dumon
            entreprise2.addEmploye((Employe) employes.get(4)); // Durand
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print toString for both enterprises
        System.out.println(entreprise1.toString());
        System.out.println(entreprise2.toString());

        try {
            ((Commercial) employes.get(0)).enregistreToi();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        try {
            ((Commercial) employes.get(0)).lire();
        } catch (IOException e ) {
            e.printStackTrace();
        }

        try {
            ((Commercial) employes.get(0)).engistreBinaire();
        } catch (IOException e ) {
            e.printStackTrace();
        }


        //utiliser Iterator pour calculer salaires des employés de chacune des 2 entreprises
        Iterator it1 = entreprise1.iterEmployes();
        double totalSalaires1 = 0;
        while (it1.hasNext()) {
            Employe e = (Employe) it1.next();
            totalSalaires1 += e.getSalary();
        }
        System.out.println("Total salaires entreprise 1: " + totalSalaires1);
        Iterator it2 = entreprise2.iterEmployes();
        double totalSalaires2 = 0;
        while (it2.hasNext()) {
            Employe e = (Employe) it2.next();
            totalSalaires2 += e.getSalary();
        }
        System.out.println("Total salaires entreprise 2: " + totalSalaires2);


        List employesEntreprise1 = entreprise1.getEmployes();

        // Tri manuel utilisant compareTo, tri à bulles
        for (int i = 0; i < employesEntreprise1.size() - 1; i++) {
            for (int j = 0; j < employesEntreprise1.size() - 1 - i; j++) {
                Employe emp1 = (Employe) employesEntreprise1.get(j);
                Employe emp2 = (Employe) employesEntreprise1.get(j + 1);
                if (emp1.compareTo(emp2) > 0) {
                    // Échanger les éléments
                    employesEntreprise1.set(j, emp2);
                    employesEntreprise1.set(j + 1, emp1);
                }
            }
        }
        
        System.out.println("\nEmployés de " + entreprise1.getName() + " par ordre alphabétique:");
        for (int i = 0; i < employesEntreprise1.size(); i++) {
            Employe emp = (Employe) employesEntreprise1.get(i);
            System.out.println("- " + emp.getName() + " : " + emp.getSalary() + " euros");
        }



        //créez une classe qui implémente l'interface Comparator pour comparer les employés par leur salaire
        System.out.println("\nEmployés de " + entreprise1.getName() + " par ordre de salaire: (décroissant)");
        employesEntreprise1.sort(new CompareSal());
        for (int i = 0; i < employesEntreprise1.size(); i++) {
            Employe emp = (Employe) employesEntreprise1.get(i);
            System.out.println("- " + emp.getName() + " : " + emp.getSalary() + " euros");
        }

        //utiliser methode reverse de Collections pour inverser l'ordre
        System.out.println("\nEmployés de " + entreprise1.getName() + " par ordre de salaire: (croissant)");
        employesEntreprise1.sort(new CompareSal().reversed());
        for (int i = 0; i < employesEntreprise1.size(); i++) {
            Employe emp = (Employe) employesEntreprise1.get(i);
            System.out.println("- " + emp.getName() + " : " + emp.getSalary() + " euros");
        }


        //dans la méthode main, ajoutez tous les employés dans une liste des employés de l'entreprise et triez la liste avec ce comparateur.
        List tousLesEmployes = new ArrayList();
        tousLesEmployes.addAll(entreprise1.getEmployes());
        tousLesEmployes.addAll(entreprise2.getEmployes());
        tousLesEmployes.sort(new CompareSal());

        //afficher par ordre alphabétique les noms des employés des 2 entreprises
        System.out.println("\nTous les employés des deux entreprises par ordre alphabétique:");
        for (int i = 0; i < tousLesEmployes.size(); i++) {
            Employe emp = (Employe) tousLesEmployes.get(i);
            System.out.println("- " + emp.getName() + " : " + emp.getSalary() + " euros");
        }
    }
} 