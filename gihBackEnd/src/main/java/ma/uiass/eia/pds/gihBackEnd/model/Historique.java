package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "THistorique")
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "dateDebut")
    private Date dateDebut;
    @Column(name = "DateProchainRevision")
    private Date dateProchaineRevision;
    @JoinColumn(name = "idAmbulance", referencedColumnName = "Id")
    @ManyToOne
    private Ambulance ambulance;
    @JoinColumn(name = "idEtatAmbulance", referencedColumnName = "Id")
    @ManyToOne
    private EtatAmbulance etatAmbulance;

    public Historique() {
    }

    public Historique(Date dateDebut, Date dateProchaineRevision) {
        this.dateDebut = dateDebut;
        this.dateProchaineRevision = dateProchaineRevision;
    }

    public Historique(Date dateDebut, Date dateProchaineRevision, Ambulance ambulance, EtatAmbulance etatAmbulance) {
        this.dateDebut = dateDebut;
        this.dateProchaineRevision = dateProchaineRevision;
        this.ambulance = ambulance;
        this.etatAmbulance = etatAmbulance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateProchaineRevision() {
        return dateProchaineRevision;
    }

    public void setDateProchaineRevision(Date dateProchaineRevision) {
        this.dateProchaineRevision = dateProchaineRevision;
    }

    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }

    public EtatAmbulance getEtatAmbulance() {
        return etatAmbulance;
    }

    public void setEtatAmbulance(EtatAmbulance etatAmbulance) {
        this.etatAmbulance = etatAmbulance;
    }
}
