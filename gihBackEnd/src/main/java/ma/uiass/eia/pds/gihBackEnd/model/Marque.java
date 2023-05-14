package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity@Table(name="TMarque")

public class Marque implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idMarque;

    @Column(name="Nom", unique = true)
    private String nomMarque;

    @JsonIgnore
    @OneToMany(mappedBy = "marque")
    private List<Lit> lits;

    public Marque(){

    }

    public Marque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public Marque(String nomMarque, List<Lit> lits) {
        this.nomMarque = nomMarque;
        this.lits = lits;
    }


    public int getIdMarque() {

        return idMarque;

    }

    public void setIdMarque(int idMarque) {

        this.idMarque = idMarque;

    }

    public String getNomMarque() {

        return nomMarque;

    }

    public void setNomMarque(String nomMarque) {

        this.nomMarque = nomMarque;

    }

    @Override
    public String toString() {
        return nomMarque;
    }
}