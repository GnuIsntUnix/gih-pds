package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

@Entity
@Table(name = "TLit")
public class Lit extends Emplacement{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int n_lit;

    @Column(name = "Etat")
    @Enumerated(EnumType.STRING)
    private EtatLit etat;
    @Column(name = "Disponibilite")
    @Enumerated(EnumType.STRING)
    private DisponibiliteLit disponibiliteLit;

 //   @JsonIgnore
    @JoinColumn(name = "idMarque", referencedColumnName = "Id")
    @ManyToOne
    private Marque marque;

 //   @JsonIgnore
    @JoinColumn(name = "idType", referencedColumnName = "Id")
    @ManyToOne
    private TypeLit typeLit;


    @JoinColumn(name = "idAdmission", referencedColumnName = "Id")
    @OneToOne(mappedBy = "lit")
    private Admission admission;
    //@JoinColumn(name = "idStock", referencedColumnName = "Id")
    //@OneToOne(mappedBy = "lit")
   // private Stock stock;



  //  @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idEspace", referencedColumnName = "Id")
    private Espace espace;
    @OneToOne
    private Admission admision;
    //constructor    public Lit(int n_lit, EtatLit etat, Marque marque, TypeLit tpl, Chambre chambre) {

    public Lit(EtatLit etat, DisponibiliteLit dsp, Marque marque, TypeLit typeLit, Espace espace) {
        this.disponibiliteLit = dsp;
        this.etat = etat;
        this.marque = marque;
        this.typeLit = typeLit;
        this.espace = espace;
    }

    public Lit() {
    }

    public int getN_lit() {
        return this.n_lit;
    }

    public void setN_lit(int n_lit) {
        this.n_lit = n_lit;
    }

    public EtatLit getEtat() {
        return this.etat;
    }

    public void setDisponibiliteLit(DisponibiliteLit disponibiliteLit) {
        this.disponibiliteLit = disponibiliteLit;
    }
    public DisponibiliteLit getDisponibiliteLit() {
        return this.disponibiliteLit;
    }

    public void setEtat(EtatLit etat) {
        this.etat = etat;
    }

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
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

    @Override
    public String toString() {
        return "Lit{" +
                "N_lit=" + n_lit +
                ", Etat=" + etat +
                ", marque=" + marque +
                ", typeLit=" + typeLit +
                ", espace=" + espace +
                '}';
    }
    public String getDisplayedName() {
        return "Lit " + n_lit + " - " + typeLit.getNomTypeLit() + " (" + marque.getNomMarque() + ")";
    }
}
