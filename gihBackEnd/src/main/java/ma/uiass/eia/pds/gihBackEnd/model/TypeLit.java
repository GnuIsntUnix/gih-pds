package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.*;

import java.util.List;


@Entity
@Table(name="TypeLit")

public class TypeLit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idType;

    @Column(name = "Nom")
    private String nomTypeLit;

    @OneToMany(mappedBy = "typeLit")
    private List<Lit> lits;

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
}