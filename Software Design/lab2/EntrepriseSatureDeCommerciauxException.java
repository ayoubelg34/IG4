package lab2;

public class EntrepriseSatureDeCommerciauxException extends Exception {
    
    private Entreprise entreprise;
    
    public EntrepriseSatureDeCommerciauxException(String message, Entreprise entreprise) {
        super(message);
        this.entreprise = entreprise;
    }
    
    public Entreprise getEntreprise() {
        return entreprise;
    }
}