package lab2;

import lab2.EntrepriseSatureDeCommerciauxException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Entreprise {
    private String name;
    private List employes = new ArrayList();
    private int nb_commerciaux;
    private int maxCommerciaux;

    public Entreprise(String name, int maxCommerciaux) {
        this.name = name;
        this.maxCommerciaux = maxCommerciaux;
    }

    public String getName() {
        return name;
    }


    public int getMaxCommerciaux() {
        return maxCommerciaux;
    }

    public int getNbCommerciaux() {
        return nb_commerciaux;
    }

    public String listCommerciaux() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < employes.size(); i++) {
            Employe e = (Employe) employes.get(i);
            if (e != null && e instanceof Commercial) {
                sb.append(((Commercial) e).getName()).append(", ");
            }
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); //Enlever la dernière virgule et espace.
        }
        return sb.toString();
    }


    public void addEmploye(Employe employe) throws Exception {
        if (employe instanceof Commercial) {
            if (nb_commerciaux >= maxCommerciaux) {
                throw new EntrepriseSatureDeCommerciauxException("Tu ne peux pas ajouter plus de " + maxCommerciaux + " commerciaux", this);
            }
            nb_commerciaux++;
        }

        employes.add(employe);
    }

    
    //décrit une entreprise en affichant son nom et les noms de tous ses employés (parcourez le ArrayList avec les indices).
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entreprise: ").append(this.name).append("\nEmployés:\n");
        for (int i = 0; i < employes.size(); i++) {
            Object e = employes.get(i);
            if (e != null && e instanceof Employe) {
                sb.append("- ").append(((Employe) e).getName()).append("\n");
            }
        }
        return sb.toString();
    }

    public Iterator iterEmployes () {
        return employes.iterator();
    }

    public List getEmployes() {
        List copie = new ArrayList();
        for (int i = 0; i < employes.size(); i++) {
            copie.add(employes.get(i));
        }
        return copie; // Retourne une copie pour éviter les modifications externes
    }

}