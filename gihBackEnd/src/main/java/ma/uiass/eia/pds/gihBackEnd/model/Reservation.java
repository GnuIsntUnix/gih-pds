package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TReservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idReservation;

    @Column(name = "dateDebut")
    private Date dateDebut;

    @Column(name = "dateFin")
    private Date dateFin;

    @OneToOne
    private Lit lit;
    //test
}
