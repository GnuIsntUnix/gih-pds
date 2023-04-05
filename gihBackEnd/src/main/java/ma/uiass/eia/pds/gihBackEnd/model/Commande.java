package ma.uiass.eia.pds.gihBackEnd.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "TCommande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idCommande;


    @Column(name = "valide", columnDefinition = "tinyint default false")
    private boolean valide = false;

    @JoinColumn(name = "idService", referencedColumnName = "Id")
    @ManyToOne
    private Service service;

    @OneToOne
    @JoinColumn(name = "idType", referencedColumnName = "Id")
    private TypeLit typeLit;

    @Column(name = "Quantite")
    private int quantite;

    public Commande() {
    }

    public Commande(Service service, TypeLit typeLit, int quantite) {
        this.service = service;
        this.typeLit = typeLit;
        this.quantite = quantite;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public TypeLit getTypeLit() {
        return typeLit;
    }

    public void setTypeLit(TypeLit typeLit) {
        this.typeLit = typeLit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Type De Lit : " + typeLit
                + " Quantite : " + quantite
                + " Valide : " + valide;
    }
}
