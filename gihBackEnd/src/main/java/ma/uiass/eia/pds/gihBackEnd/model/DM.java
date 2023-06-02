package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@DiscriminatorColumn(name = "Type", length = 255)
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=DMwithExemplaire.class,name="DMwithExemplaire"),
        @JsonSubTypes.Type(value =DMwithQuantity.class,name="DMwithQuantity")
})
@Table(name = "TDm")
public abstract class DM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Code", unique = true)
    private String code;

    @Column(name = "Nom", unique = true)
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
    @JoinColumn(name = "idStock", referencedColumnName = "Id")
    @ManyToOne
    private Stock stock;

    public DM() {
    }

    public DM(String code, String nom, TypeDM typeDM) {
        this.code=code;
        this.nom=nom;
        this.typeDM=typeDM;
        typeDM.getDms().add(this);
    }
    public DM(String code, String nom, TypeDM typeDM,Stock stock) {
        this.code=code;
        this.nom=nom;
        this.typeDM=typeDM;
        typeDM.getDms().add(this);
        this.stock=stock;
        stock.getDms().add(this);
    }

    public DM(String code, String nom, TypeDM typeDM, DetailDemandeDm detailDemandeDm, DetailLivraison detailLivraison,Stock stock) {
        this.code = code;
        this.nom = nom;
        this.typeDM=typeDM;
        typeDM.getDms().add(this);
        this.detailDemandeDm = detailDemandeDm;
        this.detailLivraison = detailLivraison;
        this.stock=stock;
        stock.getDms().add(this);
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
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


}
