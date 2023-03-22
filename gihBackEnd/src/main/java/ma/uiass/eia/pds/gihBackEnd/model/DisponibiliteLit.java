package ma.uiass.eia.pds.gihBackEnd.model;

public enum DisponibiliteLit {
   O ("Occupé"), Di ("Disponible");
    String nom;
    DisponibiliteLit(String  nom){
        this.nom = nom;
    }
    public String getNom(){
        return this.nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}

