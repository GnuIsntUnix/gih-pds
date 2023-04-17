package ma.uiass.eia.pds.gihBackEnd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

    @JsonIgnore
    @JoinColumn(name = "idStock", referencedColumnName = "Id")
    @ManyToOne
    private Stock stock;

    @JoinColumn(name = "idType", referencedColumnName = "Id")
    @ManyToOne
    @JsonIgnore
    private TypeDM typeDM;

    @OneToOne(mappedBy = "dm")
    private DetailLivraison detailLivraison;

    @OneToOne(mappedBy = "dm")
    private DetailDemandeDm detailDemandeDm;

    public DM(String code, String nom, Stock stock, TypeDM typeDM) {
        this.code = code;
        this.nom = nom;
        this.stock = stock;
        this.typeDM = typeDM;
    }

    public DM(String code, String nom, Stock stock, TypeDM typeDM, DetailLivraison detailLivraison) {
        this.code = code;
        this.nom = nom;
        this.stock = stock;
        this.typeDM = typeDM;
        this.detailLivraison = detailLivraison;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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
