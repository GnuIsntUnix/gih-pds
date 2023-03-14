package ma.uiass.eia.pds.gihBackEnd.model;

public enum TypeChambre {
    S ("Single") ,D ("Double"), St ("Suite");
    String nom;
    TypeChambre(String  nom){
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
