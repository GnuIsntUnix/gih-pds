package ma.uiass.eia.pds.gihBackEnd.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "TLit")
public class Lit extends Emplacement{

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
    @JoinColumn(name = "idEspace", referencedColumnName = "Id")
    private Espace espace;
    //constructor    public Lit(int n_lit, EtatLit etat, Marque marque, TypeLit tpl, Chambre chambre) {

    public Lit(EtatLit etat, Marque marque, TypeLit typeLit, Espace espace) {
        Etat = etat;
        this.marque = marque;
        this.typeLit = typeLit;
        this.espace = espace;
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

    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }
}
