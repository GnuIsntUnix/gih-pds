package ma.uiass.eia.pds.gihBackEnd.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.uiass.eia.pds.gihBackEnd.util.Instances;

import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="TChambre")
public class Chambre extends Instances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int idChambre;

    @Column(name = "Numero")
    private int numChambre;

    @JsonIgnore
    @JoinColumn(name = "idBatiment", referencedColumnName = "Id")
    @ManyToOne
    private Batiment batiment;

    @Column(name = "Type")
    private String type_chambre;

    @Column(name = "Etage")
    private int etage;

    @OneToMany(mappedBy="chambre")
    private List<Lit> lits ;

    public Chambre(int numChambre, Batiment batiment, String type_chambre, int etage, List<Lit> lits) {
        this.numChambre = numChambre;
        this.batiment = batiment;
        this.type_chambre = type_chambre;
        this.etage = etage;
        this.lits = lits;
    }

    public Chambre(int numChambre, Batiment batiment, String type_chambre, int etage) {
        this.numChambre = numChambre;
        this.batiment = batiment;
        this.type_chambre = type_chambre;
        this.etage = etage;
    }

    public Chambre() {
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public int getNumChambre() {
        return numChambre;
    }

    public void setNumChambre(int numChambre) {
        this.numChambre = numChambre;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public String getType_chambre() {
        return type_chambre;
    }

    public void setType_chambre(String type_chambre) {
        this.type_chambre = type_chambre;
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
        return "Chambre " + numChambre;
    }
}










