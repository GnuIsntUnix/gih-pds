package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TDemande")
public class DemandeDm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idDemande;


    @Column(name="DateDemande")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateDemande;


    @Column(name = "valide", columnDefinition = "tinyint default false")
    private boolean valide = false;

    @JoinColumn(name = "idService", referencedColumnName = "Id")
    @ManyToOne
    private Service service;
    @OneToMany(mappedBy = "demandeDm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailDemandeDm> detailDemandeDms= new ArrayList<>();




    public DemandeDm() {
    }

    public DemandeDm(Service service, LocalDate dateDemande,List<DetailDemandeDm> detailDemandeDms) {
        this.service = service;
        this.dateDemande=dateDemande;
        this.detailDemandeDms=detailDemandeDms;

    }

    public DemandeDm(Service service, LocalDate date) {
        this.service = service;
        this.dateDemande=date;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public List<DetailDemandeDm> getDetailDemandeDms() {
        return detailDemandeDms;
    }

    public void setDetailDemandeDms(List<DetailDemandeDm> detailDemandeDms) {
        this.detailDemandeDms = detailDemandeDms;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return " Valide : " + valide+ " date demande "+ this.dateDemande;
    }
}
