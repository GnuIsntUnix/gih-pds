package ma.uiass.eia.pds.gihBackEnd.model;
import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="TEquipement")

public class Equipement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "Id")
    private int idEquipement;
    @Column(name = "Nom")
    private String nomEquipement;

    @ManyToOne
    @JoinColumn(name = "idType", referencedColumnName = "Id")
    private TypeLit typeLit;

    public Equipement(){

    }

    public Equipement(int e, String n){

        this.idEquipement=e;

        this.nomEquipement=n;

    }

    public int getIdEquipement() {

        return idEquipement;

    }

    public void setIdEquipement(int idEquipement) {

        this.idEquipement = idEquipement;

    }

    public String getNomEquipement() {

        return nomEquipement;

    }

    public void setNomEquipement(String nomEquipement) {

        this.nomEquipement = nomEquipement;

    }

}