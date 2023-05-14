package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TAmbulance")
public class Ambulance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(unique = true)
    private String immatriculation;
    @Column(name = "miseEnCirculation")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateMiseEnCirculation;
    @Column(name = "dateDeCreation")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateDeCreation;
    @JsonIgnore
    @OneToMany(mappedBy = "ambulance",cascade = CascadeType.ALL)
    private List<Historique> historiques = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "ambulance",cascade = CascadeType.ALL)
    private List<Revision> revisions = new ArrayList<>();
    public Ambulance() {
    }

    public Ambulance(String immatriculation, LocalDate dateMiseEnCirculation) {
        this.immatriculation = immatriculation;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.dateDeCreation=LocalDate.now();
    }

    public Ambulance(String immatriculation, LocalDate dateMiseEnCirculation, LocalDate dateDeCreation, List<Historique> historiques, List<Revision> revisions) {
        this.immatriculation = immatriculation;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.dateDeCreation = dateDeCreation;
        this.historiques = historiques;
        this.revisions = revisions;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateMiseEnCirculation() {
        return dateMiseEnCirculation;
    }

    public void setDateMiseEnCirculation(LocalDate dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }
}
