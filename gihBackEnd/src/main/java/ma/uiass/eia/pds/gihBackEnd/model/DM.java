package ma.uiass.eia.pds.gihBackEnd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TDm")
public class DM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Code")
    private String code;

    @Column(name = "Nom")
    private String nom;


    @JoinColumn(name = "idType", referencedColumnName = "Id")
    @ManyToOne
    private TypeDM typeDM;

    @OneToMany(mappedBy = "dm", fetch = FetchType.LAZY)
    private List<ExemplaireDm> exemplaireDmList = new ArrayList<>();

    @OneToOne(mappedBy = "dm")
    @JsonIgnore
    private DetailLivraison detailLivraison;

    @OneToOne(mappedBy = "dm")
    @JsonIgnore
    private DetailDemandeDm detailDemandeDm;

    public DM(String code, String nom, TypeDM typeDM) {
        this.code = code;
        this.nom = nom;
        this.typeDM = typeDM;
    }

    public DM(String code, String nom, TypeDM typeDM, DetailLivraison detailLivraison) {
        this.code = code;
        this.nom = nom;
        this.typeDM = typeDM;
        this.detailLivraison = detailLivraison;
    }

    public List<ExemplaireDm> getExemplaireDmList() {
        return exemplaireDmList;
    }

    public void setExemplaireDmList(List<ExemplaireDm> exemplaireDmList) {
        this.exemplaireDmList = exemplaireDmList;
    }


    public DetailDemandeDm getDetailDemandeDm() {
        return detailDemandeDm;
    }

    public void setDetailDemandeDm(DetailDemandeDm detailDemandeDm) {
        this.detailDemandeDm = detailDemandeDm;
    }

    public DM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeDM getTypeDM() {
        return typeDM;
    }

    public void setTypeDM(TypeDM typeDM) {
        this.typeDM = typeDM;
    }

    public DetailLivraison getDetailLivraison() {
        return detailLivraison;
    }

    public void setDetailLivraison(DetailLivraison detailLivraison) {
        this.detailLivraison = detailLivraison;
    }

    @Override
    public String toString() {
        return nom ;
    }
}
