import java.util.Arrays;
import java.lang.Thread;


public class Trieur extends Thread {

  private int[] t;
  private int debut, fin;

  public Trieur(int[] t) {
    this(t, 0, t.length - 1);
  }

  public Trieur(int[] t, int debut, int fin) {
    this.t = t;
    this.debut = debut;
    this.fin = fin;
  }


  //trier remplacé par run pour Thread
  public void run() {
    if (fin - debut < 2) {
      if (t[debut] > t[fin]) {
        echanger(debut, fin);
      }
    } else {
      int milieu = (debut + fin) / 2;

      // Création de 2 sous-trieurs
      Trieur trieurGauche = new Trieur(t, debut, milieu);
      Trieur trieurDroit = new Trieur(t, milieu + 1, fin);

      // Lancer les 2 threads
      trieurGauche.start();
      trieurDroit.start();

      try {
        // Attendre que les 2 threads aient terminé
        trieurGauche.join();
        trieurDroit.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // Fusion après le tri des deux moitiés
      triFusion(debut, fin);
    }
  }

  private void echanger(int i, int j) {
    int tmp = t[i];
    t[i] = t[j];
    t[j] = tmp;
  }

  private void triFusion(int debut, int fin) {
    int[] tFusion = new int[fin - debut + 1];
    int milieu = (debut + fin) / 2;

    int i1 = debut;
    int i2 = milieu + 1;
    int iFusion = 0;

    while (i1 <= milieu && i2 <= fin) {
      if (t[i1] < t[i2]) {
        tFusion[iFusion++] = t[i1++];
      } else {
        tFusion[iFusion++] = t[i2++];
      }
    }

    while (i1 <= milieu) {
      tFusion[iFusion++] = t[i1++];
    }

    while (i2 <= fin) {
      tFusion[iFusion++] = t[i2++];
    }

    // Copier dans le tableau original
    for (int i = 0; i < tFusion.length; i++) {
      t[debut + i] = tFusion[i];
    }
  }

  public static void main(String[] args) {
    int[] t = {5, 8, 3, 2, 7, 10, 1};
    Trieur trieur = new Trieur(t);

    // Démarrage du tri
    trieur.start();

    try {
      // Attente de la fin du tri avant affichage
      trieur.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Affichage du tableau trié
    for (int val : t) {
      System.out.print(val + " ; ");
    }
    System.out.println();
  }
}
