package ma.uiass.eia.pds.gihBackEnd.model;

public enum TypeAmbulance {
    B ("soins de base"), I ("soins intensifs"), U ("soins d'urgence");
    String nom;

   TypeAmbulance(String s) {
       this.nom=s;
   }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
