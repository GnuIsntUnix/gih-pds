//package ma.uiass.eia.pds.gihBackEnd.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
//
//@Entity
//@Table(name="TReservation")
//public class Reservation implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Id")
//    private int idReservation;
//
//    @Column(name = "dateDebut")
//    private Date dateDebut;
//
//    @Column(name = "dateFin")
//    private Date dateFin;
//
//    @OneToOne
//    private Lit lit;
//
//    public Reservation(Date dateDebut, Date dateFin, Lit lit) {
//        this.dateDebut = dateDebut;
//        this.dateFin = dateFin;
//        this.lit = lit;
//    }
//
//    public int getIdReservation() {
//        return idReservation;
//    }
//
//    public void setIdReservation(int idReservation) {
//        this.idReservation = idReservation;
//    }
//
//    public Date getDateDebut() {
//        return dateDebut;
//    }
//
//    public void setDateDebut(Date dateDebut) {
//        this.dateDebut = dateDebut;
//    }
//
//    public Date getDateFin() {
//        return dateFin;
//    }
//
//    public void setDateFin(Date dateFin) {
//        this.dateFin = dateFin;
//    }
//
//    public Lit getLit() {
//        return lit;
//    }
//
//    public void setLit(Lit lit) {
//        this.lit = lit;
//    }
//}
