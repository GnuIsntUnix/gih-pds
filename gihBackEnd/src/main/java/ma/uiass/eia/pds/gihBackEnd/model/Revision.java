package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRevision")
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "dateRevision")
    private Date dateRevision;
    @JoinColumn(name = "idAmbulance", referencedColumnName = "Id")
    @ManyToOne
    private Ambulance ambulance;

    public Revision() {
    }

    public Revision(Date dateRevision) {
        this.dateRevision = dateRevision;
    }

    public Revision(Date dateRevision, Ambulance ambulance) {
        this.dateRevision = dateRevision;
        this.ambulance = ambulance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateRevision() {
        return dateRevision;
    }

    public void setDateRevision(Date dateRevision) {
        this.dateRevision = dateRevision;
    }

    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }
}
