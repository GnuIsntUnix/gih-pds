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
    private int idAdmission;
    @Column(name="NumAdmission")
    private int numAdmission;
    @Column(name="DateDébut")
    private LocalDate dateDébut;
    @Column(name="DateFin")
    private LocalDate dateFin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLit")
    private Lit lit;

    public Admission( LocalDate dateE, Lit lit) {
        this.dateDébut=dateE;
        this.lit=lit;
    }


    public int getNumAdmission() {
        return numAdmission;
    }
    public void setNumAdmission(int numAdmission) {
        this.numAdmission = numAdmission;
    }
    //public Date getDateDébut() {return dateDébut;}
    //public void setDateDébut(Date dateDébut) {this.dateDébut = dateDébut;}
    //public Date getDateFin() {return dateFin;}
       /*public void setDateFin(Date dateFin) {
            this.dateFin = dateFin;
        }*/
    public int getIdRAdmission() {
        return idAdmission;
    }

    public LocalDate getDateDébut() {
        return dateDébut;
    }

    public void setDateDébut(LocalDate dateDébut) {
        this.dateDébut = dateDébut;
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
    public Admission(int numAdmission, LocalDate dateDébut, LocalDate dateFin, Lit lit) {
        this.numAdmission = numAdmission;
        this.dateDébut = dateDébut;
        this.dateFin = dateFin;
        this.lit = lit;
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
    public String toString(){
        return this.idAdmission + " "+this.numAdmission+" "+" "+this.dateDébut+" "+this.dateFin +" "+this.lit.getN_lit();        }

    public int getIdLit() {
        return lit.getN_lit();
    }


    /////////////////////////////////////////////////////////////////////////////:::::
    public void ajouterAdmission(int numAdmission, LocalDate dateDébut, LocalDate dateFin, Lit lit) {
        Admission admission = new Admission(numAdmission, dateDébut, dateFin, lit);
        // Ajouter la nouvelle admission à la base de données
        EntityManager entityManager = null;
        entityManager.persist(admission);
    }

    public void modifierAdmission(Admission admission, int numAdmission, LocalDate dateDébut, LocalDate dateFin, Lit lit) {
        admission.setNumAdmission(numAdmission);
        admission.setDateDébut(dateDébut);
        admission.setDateFin(dateFin);
        admission.setLit(lit);
        // Mettre à jour l'admission dans la base de données
        EntityManager entityManager = null;
        entityManager.merge(admission);
    }

}

