package ma.uiass.eia.pds.gihBackEnd.model;

public enum EtatLit {
    E ("En Stock") ,O ("Occupé"), D ("Deféctueux"), Di ("Disponible");
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