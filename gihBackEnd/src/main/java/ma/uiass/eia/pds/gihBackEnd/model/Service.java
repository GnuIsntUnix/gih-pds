package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.uiass.eia.pds.gihBackEnd.util.Instances;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TService")
public class Service extends Instances implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idService;

    @Column(name = "Code", unique = true)
    private String codeS;

    @Column(name = "Nom", unique = true)
    private String nomService;


    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Batiment> batiments = new ArrayList<>();

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DemandeDm> demandeDms = new ArrayList<>();

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Commande> commandes = new ArrayList<>();

    @OneToOne(mappedBy = "service", cascade = CascadeType.ALL)
    @JsonIgnore
    private Stock stock;

    public Service() {
    }

    public Service(String codeS, String nomService, List<Batiment> batiments) {
        this.codeS = codeS;
        this.nomService = nomService;
        this.batiments = batiments;
    }

    public Service(String codeS, String nomService) {
        this.codeS = codeS;
        this.nomService = nomService;
    }

    public List<DemandeDm> getDemandeDms() {
        return demandeDms;
    }

    public void setDemandeDms(List<DemandeDm> demandeDms) {
        this.demandeDms = demandeDms;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getCodeS() {
        return codeS;
    }

    public void setCodeS(String codeS) {
        this.codeS = codeS;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public List<Batiment> getBatiments() {
        return batiments;
    }

    public void setBatiments(List<Batiment> batiments) {
        this.batiments = batiments;
    }

    @Override
    public String toString() {
        return nomService;
    }
}
