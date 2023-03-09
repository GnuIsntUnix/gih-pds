package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEspace")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", length = 255)
public abstract class Espace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    protected int idEspace;

    @Column(name = "Numero")
    protected int numEspace;

    @JsonIgnore
    @JoinColumn(name = "idBatiment", referencedColumnName = "Id")
    @ManyToOne
    protected Batiment batiment;

    @Column(name = "Etage")
    protected int etage;

    @OneToMany(mappedBy="espace")
    protected List<Lit> lits ;

    public Espace(int numEspace, Batiment batiment, int etage, List<Lit> lits) {
        this.numEspace = numEspace;
        this.batiment = batiment;
        this.etage = etage;
        this.lits = lits;
    }

    public Espace(int numEspace, Batiment batiment, int etage) {
        this.numEspace = numEspace;
        this.batiment = batiment;
        this.etage = etage;
    }

    public Espace() {
    }

    public int getIdEspace() {
        return idEspace;
    }

    public void setIdEspace(int idEspace) {
        this.idEspace = idEspace;
    }

    public int getNumEspace() {
        return numEspace;
    }

    public void setNumEspace(int numEspace) {
        this.numEspace = numEspace;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public List<Lit> getLits() {
        return lits;
    }

    public void setLits(List<Lit> lits) {
        this.lits = lits;
    }
}
