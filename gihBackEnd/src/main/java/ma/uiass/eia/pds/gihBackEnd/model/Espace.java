package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEspace")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", length = 255)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Chambre.class, name = "Chambre"),
        @JsonSubTypes.Type(value = Salle.class, name = "Salle")
})
public abstract class Espace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    protected int idEspace;

    @Column(name = "Type", insertable = false, updatable = false)
    private String type;
    @Column(name = "Numero")
    protected int numEspace;

    @JsonIgnore
    @JoinColumn(name = "idBatiment", referencedColumnName = "Id")
    @ManyToOne
    protected Batiment batiment;

    @Column(name = "Etage")
    protected int etage;

    @JsonIgnore
    @OneToMany(mappedBy="espace")
    protected List<Lit> lits ;


    public Espace(String type, int numEspace, Batiment batiment, int etage, List<Lit> lits) {
        this.type = type;
        this.numEspace = numEspace;
        this.batiment = batiment;
        this.etage = etage;
        this.lits = lits;
    }

    public Espace(String type, int numEspace, Batiment batiment, int etage) {
        this.type = type;
        this.numEspace = numEspace;
        this.batiment = batiment;
        this.etage = etage;
    }

    public Espace() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return type + " " + numEspace;
    }
}
