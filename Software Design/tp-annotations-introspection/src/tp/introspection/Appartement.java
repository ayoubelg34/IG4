package tp.introspection;

import java.io.Serializable;

public class Appartement implements Cloneable, Serializable {
    private String adresse;
    private int nbPieces;
    private double superficie;
    public static final double taxeFonciereAuM2 = 5;

    public Appartement() {}

    public double loyer() {
        return valLocBase() * coeffModerateur();
    }

    public double valLocBase() {
        return superficie * 5 * (1.0 + this.nbPieces / 10.0);
    }

    public double coeffModerateur() {
        return 1;
    }

    public String feuilleLoyer() {
        return "adresse = " + this.adresse + " val loc base = " + this.valLocBase()
                + " coeff moderateur = " + this.coeffModerateur() + " loyer = " + this.loyer();
    }

    public static double getTaxefonciereaum2() {
        return taxeFonciereAuM2;
    }
}
