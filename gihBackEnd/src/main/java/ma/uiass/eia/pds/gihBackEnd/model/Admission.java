package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name= "TAdmission")

public class Admission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdmission;
    @Column(name="NumAdmission")
    private String numAdmission;
    @Column(name="DateDébut")
    private String dateDébut;
    @Column(name="DateFin")
    private String dateFin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLit")
    private Lit lit;

    public Admission(String numAdmission, String dateE, Lit lit) {
        this.numAdmission=numAdmission;
        this.dateDébut=dateE;
        this.lit=lit;
    }


    public String getNumAdmission() {
        return numAdmission;
    }
    public void setNumAdmission(String numAdmission) {
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

    public String getDateDébut() {
        return dateDébut;
    }

    public void setDateDébut(String dateDébut) {
        this.dateDébut = dateDébut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public int getIdAdmission() {
        return idAdmission;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    public Admission(String numAdmission, String dateDébut, String dateFin, Lit lit) {
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
}

