package ma.uiass.eia.pds.gihBackEnd.dto;


public class LitDto {

    private int id;

    private String etat;

    private String marque;

    private String type;

    private int chambre;

    private int etage;

    private String batiment;

    public LitDto(int id, String etat, String marque, String type, int chambre, int etage, String batiment) {
        this.id = id;
        this.etat = etat;
        this.marque = marque;
        this.type = type;
        this.chambre = chambre;
        this.etage = etage;
        this.batiment = batiment;
    }

    public LitDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getChambre() {
        return chambre;
    }

    public void setChambre(int chambre) {
        this.chambre = chambre;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }
}
