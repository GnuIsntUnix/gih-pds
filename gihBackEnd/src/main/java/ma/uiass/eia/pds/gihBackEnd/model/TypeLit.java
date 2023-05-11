package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="TypeLit")

public class TypeLit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idType;

    @Column(name = "Nom", unique = true)
    private String nomTypeLit;

    @JsonIgnore
    @OneToMany(mappedBy = "typeLit")
    private List<Lit> lits = new ArrayList<>();

    @OneToMany(mappedBy = "typeLit")
    private List<Equipement> equipements;

    public TypeLit(String nomTypeLit, List<Lit> lits, List<Equipement> equipements) {
        this.nomTypeLit = nomTypeLit;
        this.lits = lits;
        this.equipements = equipements;
    }

    public TypeLit(String nomTypeLit) {
        this.nomTypeLit = nomTypeLit;
    }

    public TypeLit(){

    }

    public TypeLit(int t,String n){

        this.idType =t;

        this.nomTypeLit=n;

    }

    public int getIdType() {

        return idType;

    }

    public void setIdType(int idType) {

        this.idType = idType;

    }

    public String getNomTypeLit() {

        return nomTypeLit;

    }

    public void setNomTypeLit(String nomTypeLit) {

        this.nomTypeLit = nomTypeLit;

    }

    public List<Lit> getLits() {
        return lits;
    }

    public void setLits(List<Lit> lits) {
        this.lits = lits;
    }

    public List<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }

    @Override
    public String toString() {
        return nomTypeLit;
    }

//    @Override
//    public boolean equals(Object obj) {
//        TypeLit typeLit = (TypeLit) obj;
//        return typeLit.nomTypeLit.equalsIgnoreCase(this.nomTypeLit);
//    }
}