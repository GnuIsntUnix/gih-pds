package ma.uiass.eia.pds.gihBackEnd.model;

import com.mysql.jdbc.PreparedStatement;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name= "TAdmission")

public class Admission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idAdmission;


    @Column(name="DateDébut")
    private LocalDate dateDebut;
    @Column(name="DateFin")
    private LocalDate dateFin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLit")
    private Lit lit;

    public Admission( LocalDate dateE, Lit lit) {
        this.dateDebut=dateE;
        this.lit=lit;
        lit.setAdmission(this);
    }


    public int getIdRAdmission() {
        return idAdmission;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDébut) {
        this.dateDebut = dateDébut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public int getIdAdmission() {
        return idAdmission;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }


    public Lit getLit() {
        return lit;
    }
    public void setLit(Lit lit) {
        this.lit = lit;
    }
    public void setIdAdmission(int idAdmission) {
        this.idAdmission = idAdmission;
    }
    public Admission(){}

    public Admission(int idAdmission, LocalDate dateDebut, LocalDate dateFin, Lit lit) {
        this.idAdmission = idAdmission;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lit = lit;
        lit.setAdmission(this);
    }

    public Admission(int idAdmission, LocalDate dateDebut, Lit lit) {
        this.idAdmission = idAdmission;
        this.dateDebut = dateDebut;
        this.lit=lit;
        lit.setAdmission(this);
    }

    public int getIdLit() {
        return lit.getN_lit();
    }


    @Override
    public String toString() {
        return "Admission{" +
                lit + dateDebut +
                 dateFin
               ;
    }

}

