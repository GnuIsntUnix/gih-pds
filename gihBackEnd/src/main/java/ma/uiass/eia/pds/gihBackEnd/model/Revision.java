package ma.uiass.eia.pds.gihBackEnd.model;

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
    private LocalDate dateRevision;
    @JoinColumn(name = "idAmbulance", referencedColumnName = "Id")
    @ManyToOne
    private Ambulance ambulance;

    public Revision() {
    }

    public Revision(LocalDate dateRevision) {
        this.dateRevision = dateRevision;
    }

    public Revision(LocalDate dateRevision, Ambulance ambulance) {
        this.dateRevision = dateRevision;
        this.ambulance = ambulance;
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
    }
}
