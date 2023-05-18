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
    @JoinColumn(name = "idAmbulance", referencedColumnName = "Id")
    @ManyToOne
    private Ambulance ambulance;

    @OneToOne
    @JoinColumn(name = "idInState", referencedColumnName = "id")
    private State inState;

    @OneToOne
    @JoinColumn(name = "idOutState", referencedColumnName = "id")
    private State outState;

    public Revision() {
    }

    public Revision(LocalDate dateRevision) {
        this.dateRevision = dateRevision;
    }

    public Revision(LocalDate dateRevision, Ambulance ambulance) {
        this.dateRevision = dateRevision;
        this.setAmbulance(ambulance);
    }

    public Revision(LocalDate dateRevision, Ambulance ambulance, State inState, State outState) {
        this.dateRevision = dateRevision;
        this.ambulance = ambulance;
        this.inState = inState;
        this.outState = outState;
    }

    public State getInState() {
        return inState;
    }

    public void setInState(State inState) {
        this.inState = inState;
    }

    public State getOutState() {
        return outState;
    }

    public void setOutState(State outState) {
        this.outState = outState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
