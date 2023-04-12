package ma.uiass.eia.pds.gihBackEnd.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TLivraison")
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Fournisseur")
    private String fournisseur;

    @OneToMany(mappedBy = "livraison", fetch = FetchType.EAGER)
    private List<DetailLivraison> detailLivraisonList;

    public Livraison(LocalDate date, String fournisseur, List<DetailLivraison> detailLivraisonList) {
        this.date = date;
        this.fournisseur = fournisseur;
        this.detailLivraisonList = detailLivraisonList;
    }

    public Livraison() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<DetailLivraison> getDetailLivraisonList() {
        return detailLivraisonList;
    }

    public void setDetailLivraisonList(List<DetailLivraison> detailLivraisonList) {
        this.detailLivraisonList = detailLivraisonList;
    }
}
