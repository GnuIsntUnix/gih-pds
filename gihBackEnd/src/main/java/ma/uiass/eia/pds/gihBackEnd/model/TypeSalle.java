package ma.uiass.eia.pds.gihBackEnd.model;

public enum TypeSalle {

    At("Salle d'attente") ,V ("Salle de visite"),O("Opération"), A ("Analyse"),I("imagerie");
    String nom;
    TypeSalle(String  nom){
        this.nom = nom;
    }
    public String getNom(){
        return this.nom;
    }
}