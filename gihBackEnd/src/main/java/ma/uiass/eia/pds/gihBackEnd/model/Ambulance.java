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
    private String kilometrage;
    @Column(name = "miseEnCirculation")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateMiseEnCirculation;
    @Column(name = "dateDeCreation")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateDeCreation = LocalDate.now();
    @Column(name = "typeAmbulance")
    private TypeAmbulance typeAmbulance;

    @JsonIgnore
    @OneToMany(mappedBy = "ambulance",cascade = CascadeType.ALL)
    private List<Revision> revisions = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "idState", referencedColumnName = "id")
    private State state;


    public Ambulance() {
    }

    public Ambulance(String immatriculation,LocalDate dateMiseEnCirculation, String km, TypeAmbulance typeAmbulance) {
        this.immatriculation = immatriculation;
        this.kilometrage = km;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.typeAmbulance = typeAmbulance;
    }

    public Ambulance(String immatriculation, LocalDate dateMiseEnCirculation, String km) {
        this.immatriculation = immatriculation;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.kilometrage=km;
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

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String km) {
        this.kilometrage = km;
    }

    public TypeAmbulance getTypeAmbulance() {
        return typeAmbulance;
    }

    public void setTypeAmbulance(TypeAmbulance typeAmbulance) {
        this.typeAmbulance = typeAmbulance;
    }
}
