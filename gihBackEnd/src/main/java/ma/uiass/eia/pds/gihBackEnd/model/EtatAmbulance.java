package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEtatAmbulance")
public class EtatAmbulance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "nomEtat", unique = true)
    private String nom;
    @JsonIgnore
    @OneToMany(mappedBy = "etatAmbulance")
    private List<Historique> historiques;

    public EtatAmbulance() {
    }

    public EtatAmbulance(String nom) {
        this.nom = nom;
    }

    public EtatAmbulance(String nom, List<Historique> historiques) {
        this.nom = nom;
        this.historiques = historiques;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }
}
