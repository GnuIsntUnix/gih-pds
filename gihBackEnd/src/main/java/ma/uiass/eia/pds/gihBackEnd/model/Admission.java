package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateDebut;
    @Column(name="DateFin")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateFin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLit")
    private Lit lit;

    public Admission( LocalDate dateE, Lit lit) {
        this.dateDebut=dateE;
        this.lit=lit;
        lit.setAdmission(this);
    }



    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
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

    public Admission(LocalDate dateDebut, LocalDate dateFin, Lit lit) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lit = lit;
        lit.setAdmission(this);
    }



    @Override
    public String toString() {
        if (this.getLit() != null)
            return "Existe";
        return "Non existante";
    }
}

