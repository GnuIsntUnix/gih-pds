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
    @Column(name="kilométrage")
    private int km;
    @Column(name = "miseEnCirculation")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateMiseEnCirculation;
    @Column(name = "dateDeCreation")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateDeCreation = LocalDate.now();


    @JsonIgnore
    @OneToMany(mappedBy = "ambulance",cascade = CascadeType.ALL)
    private List<Revision> revisions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idState", referencedColumnName = "id")
    private State state;

    @Column(name= "kilometrage")
    private String kilometrage;

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Ambulance(int id, String immatriculation, LocalDate dateMiseEnCirculation, LocalDate dateDeCreation, List<Revision> revisions, State state, String kilometrage) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.dateDeCreation = dateDeCreation;
        this.revisions = revisions;
        this.state = state;
        this.kilometrage = kilometrage;
    }

    public Ambulance() {
    }

    public Ambulance(String immatriculation, LocalDate dateMiseEnCirculation,int km) {
        this.immatriculation = immatriculation;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.km=km;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}
