package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TRevision")
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;


    @Column(name= "kilometrage")
    private String kilometrage;
    @Column(name = "dateRevision")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateRevision;
    @Column(name="dateSortie")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateSortie;
    @Column(name="description")
    private String description;





    @JoinColumn(name = "idAmbulance", referencedColumnName = "Id")
    @ManyToOne
    private Ambulance ambulance;

    @OneToOne
    @JoinColumn(name = "idState", referencedColumnName = "id")
    private State state;

    @Enumerated(value = EnumType.STRING)
    private TypeRevision typeRev;

    public Revision() {
    }

    public Revision(LocalDate dateRevision) {
        this.dateRevision = dateRevision;
    }

    public Revision(LocalDate dateRevision, Ambulance ambulance) {
        this.dateRevision = dateRevision;
        this.setAmbulance(ambulance);
    }

    public Revision(String kilometrage, LocalDate dateRevision, LocalDate dateSortie, String description, Ambulance ambulance, State state, TypeRevision typeRev) {
        this.kilometrage = kilometrage;
        this.dateRevision = dateRevision;
        this.dateSortie = dateSortie;
        this.description = description;
        this.ambulance = ambulance;
        this.state = state;
        this.typeRev = typeRev;
    }
    public Revision(Ambulance ambulance, LocalDate dateRevision, LocalDate dateSortie, String description, TypeRevision typeRev, String kilometrage){
        this.ambulance=ambulance;
        this.dateRevision=dateRevision;
        this.dateSortie=dateSortie;
        this.description=description;
        this.typeRev=typeRev;
        this.kilometrage=kilometrage;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Revision( LocalDate dateRevision, String description, Ambulance ambulance,String kilometrage, State state) {
        this.dateRevision = dateRevision;
        this.description = description;
        this.ambulance = ambulance;
        this.state = state;
        this.kilometrage=kilometrage;
    }
    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public TypeRevision getTypeRev() {
        return typeRev;
    }

    public void setTypeRev(TypeRevision typeRev) {
        this.typeRev = typeRev;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateRevision() {
        return dateRevision;
    }

    public void setDateRevision(LocalDate dateRevision) {
        this.dateRevision = dateRevision;
    }

    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
        ambulance.getRevisions().add(this);
    }

    @Override
    public String toString() {
        return "Revision{" +
                ", ambulance=" + ambulance +
                ", dateRevision=" + dateRevision +
                ", dateSortie=" + dateSortie +
                ", typeRev=" + typeRev +
                ", description='" + description + '\'' +
                "kilometrage='" + kilometrage + '\'' +
                '}';
    }
}
