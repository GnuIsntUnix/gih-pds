package ma.uiass.eia.pds.gihBackEnd.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "TLit")
public class Lit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int N_lit;

    @Column(name = "Etat")
    @Enumerated(EnumType.STRING)
    private EtatLit Etat;

    @JsonIgnore
    @JoinColumn(name = "idMarque", referencedColumnName = "Id")
    @ManyToOne
    private Marque marque;

    @JsonIgnore
    @JoinColumn(name = "idType", referencedColumnName = "Id")
    @ManyToOne
    private TypeLit typeLit;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idChambre", referencedColumnName = "Id")
    private Chambre chambre;
    //constructor    public Lit(int n_lit, EtatLit etat, Marque marque, TypeLit tpl, Chambre chambre) {

    public Lit(EtatLit etat, Marque marque, TypeLit typeLit, Chambre chambre) {
        Etat = etat;
        this.marque = marque;
        this.typeLit = typeLit;
        this.chambre = chambre;
    }

    public Lit() {
    }

    public int getN_lit() {
        return N_lit;
    }

    public void setN_lit(int n_lit) {
        N_lit = n_lit;
    }

    public EtatLit getEtat() {
        return Etat;
    }

    public void setEtat(EtatLit etat) {
        Etat = etat;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public TypeLit getTypeLit() {
        return typeLit;
    }

    public void setTypeLit(TypeLit typeLit) {
        this.typeLit = typeLit;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }
}
