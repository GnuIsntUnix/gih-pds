package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "THistorique")
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "dateDebut")
    private LocalDate dateDebut;
    @Column(name = "DateProchainRevision")
    private LocalDate dateProchaineRevision;
    @JoinColumn(name = "idAmbulance", referencedColumnName = "Id")
    @ManyToOne
    private Ambulance ambulance;
    @JoinColumn(name = "idEtatAmbulance", referencedColumnName = "Id")
    @ManyToOne
    private EtatAmbulance etatAmbulance;

    public Historique() {
    }

    public Historique(LocalDate dateDebut, LocalDate dateProchaineRevision) {
        this.dateDebut = dateDebut;
        this.dateProchaineRevision = dateProchaineRevision;
    }

    public Historique(LocalDate dateDebut, LocalDate dateProchaineRevision, Ambulance ambulance, EtatAmbulance etatAmbulance) {
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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateProchaineRevision() {
        return dateProchaineRevision;
    }

    public void setDateProchaineRevision(LocalDate dateProchaineRevision) {
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
