package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.uiass.eia.pds.gihBackEnd.util.Instances;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBatiment")
public class Batiment extends Instances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idBatiment;

    @Column(name = "Nom")
    private String nomBatiment;

    @JsonIgnore
    @JoinColumn(name = "idService", referencedColumnName = "Id")
    @ManyToOne
    private Service service;

    @OneToMany(mappedBy = "batiment")
    private List<Espace> espaces;


    public Batiment(String nomBatiment, Service service, List<Espace> espaces) {
        this.nomBatiment = nomBatiment;
        this.service = service;
        this.espaces = espaces;
    }

    public Batiment(String nomBatiment, Service service) {
        this.nomBatiment = nomBatiment;
        this.service = service;
    }

    public Batiment() {
    }

    public int getIdBatiment() {
        return idBatiment;
    }

    public void setIdBatiment(int idBatiment) {
        this.idBatiment = idBatiment;
    }

    public String getNomBatiment() {
        return nomBatiment;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public List<Espace> getEspaces() {
        return espaces;
    }

    public void setEspaces(List<Espace> espaces) {
        this.espaces = espaces;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return nomBatiment;
    }
}
