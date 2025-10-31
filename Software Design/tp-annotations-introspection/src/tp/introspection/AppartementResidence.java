package tp.introspection;

public class AppartementResidence extends Appartement {
    private String services = "aucun service";

    public AppartementResidence() {}

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    @Override
    public String feuilleLoyer() {
        return super.feuilleLoyer() + "\nen résidence";
    }
}
