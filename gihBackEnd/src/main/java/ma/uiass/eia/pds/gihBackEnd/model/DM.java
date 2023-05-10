package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name = "TDm")
public abstract class DM {

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

    @OneToOne(mappedBy = "dm")
    @JsonIgnore
    private DetailDemandeDm detailDemandeDm;

    @OneToOne(mappedBy = "dm")
    @JsonIgnore
    private DetailLivraison detailLivraison;

    public DM(String code, String nom, TypeDM typeDM) {

    }

    public DM(String code, String nom, TypeDM typeDM, DetailDemandeDm detailDemandeDm, DetailLivraison detailLivraison) {
        this.code = code;
        this.nom = nom;
        this.typeDM = typeDM;
        this.detailDemandeDm = detailDemandeDm;
        this.detailLivraison = detailLivraison;
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

    public DetailDemandeDm getDetailDemandeDm() {
        return detailDemandeDm;
    }

    public void setDetailDemandeDm(DetailDemandeDm detailDemandeDm) {
        this.detailDemandeDm = detailDemandeDm;
    }

    public DetailLivraison getDetailLivraison() {
        return detailLivraison;
    }

    public void setDetailLivraison(DetailLivraison detailLivraison) {
        this.detailLivraison = detailLivraison;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
