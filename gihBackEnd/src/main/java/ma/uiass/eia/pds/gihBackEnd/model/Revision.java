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
    @Column(name = "dateRevision")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateRevision;
    @Column(name="dateSortie")
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
    public Revision(int id, LocalDate dateRevision, String description, Ambulance ambulance, State state) {
        this.id = id;
        this.dateRevision = dateRevision;
        this.description = description;
        this.ambulance = ambulance;
        this.state = state;
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
}
