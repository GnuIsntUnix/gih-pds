package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "TDetailLivraison")
public class DetailLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @OneToOne
    @JoinColumn(name = "idDm", referencedColumnName = "Id")
    private DM dm;

    @Column(name = "Qte")
    private int qte;

    @JoinColumn(name = "idLivraison", referencedColumnName = "Id")
    @ManyToOne
    @JsonIgnore
    private Livraison livraison;

    @JoinColumn(name = "idFournisseur", referencedColumnName = "Id")
    @ManyToOne
    private Fournisseur fournisseur;

    public DetailLivraison(DM dm, int qte, Livraison livraison, Fournisseur fournisseur) {
        this.dm = dm;
        this.qte = qte;
        this.livraison = livraison;
        this.fournisseur = fournisseur;
    }
    public DetailLivraison(DM dm, int qte, Fournisseur fournisseur) {
        this.dm = dm;
        this.qte = qte;
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "DetailLivraison{" +
                "dm=" + dm +
                ", qte=" + qte +
                ", fournisseur=" + fournisseur +
                '}';
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public DetailLivraison() {
    }


    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DM getDm() {
        return dm;
    }

    public void setDm(DM dm) {
        this.dm = dm;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
