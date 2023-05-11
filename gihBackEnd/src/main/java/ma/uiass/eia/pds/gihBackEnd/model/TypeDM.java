package ma.uiass.eia.pds.gihBackEnd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TypeDm")
public class TypeDM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idType;

    @Column(name = "Nom", unique = true)
    private String nomType;

    @OneToMany(mappedBy = "typeDM")
    @JsonIgnore
    private List<DM> dms = new ArrayList<>();

    public TypeDM(String nomType, List<DM> dms) {
        this.nomType = nomType;
        this.dms = dms;
    }

    public TypeDM(String nomType) {
        this.nomType = nomType;
    }

    public TypeDM() {
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public List<DM> getDms() {
        return dms;
    }

    public void setDms(List<DM> dms) {
        this.dms = dms;
    }

    @Override
    public String toString() {
        return nomType;
    }
}
