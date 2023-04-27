package ma.uiass.eia.pds.gihBackEnd.model;

public enum EtatLit {
    E ("En Stock") , N ("Brand new") , G ("Good") , O ("Operating") , P ("Poor") , D ("Defective");
    String nom;
    EtatLit(String  nom){
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