public class TrieurR implements Runnable {
    private int[] t;
    private int debut, fin;

    public TrieurR(int[] t) {
        this(t, 0, t.length - 1);
    }

    public TrieurR(int[] t, int debut, int fin) {
        this.t = t;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void run() {
        if (fin - debut < 2) {
            if (t[debut] > t[fin]) {
                echanger(debut, fin);
            }
        } else {
            int milieu = (debut + fin) / 2;

            TrieurR trieurGauche = new TrieurR(t, debut, milieu);
            TrieurR trieurDroit = new TrieurR(t, milieu + 1, fin);

            Thread threadGauche = new Thread(trieurGauche);
            Thread threadDroit = new Thread(trieurDroit);

            threadGauche.start();
            threadDroit.start();

            try {
                threadGauche.join();
                threadDroit.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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

        for (int i = 0; i < tFusion.length; i++) {
            t[debut + i] = tFusion[i];
        }
    }

    public static void main(String[] args) {
        int[] t = {5, 8, 3, 2, 7, 10, 1};
        TrieurR trieur = new TrieurR(t);
        Thread thread = new Thread(trieur);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i : t) {
            System.out.print(i + " ; ");
        }
        System.out.println();
    }
}
