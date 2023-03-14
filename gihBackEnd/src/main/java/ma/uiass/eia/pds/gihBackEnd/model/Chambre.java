package ma.uiass.eia.pds.gihBackEnd.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.uiass.eia.pds.gihBackEnd.util.Instances;

import java.util.List;
import javax.persistence.*;


@Entity
@DiscriminatorValue("Chambre")
public class Chambre extends Espace {

    @Column(name = "SousType")
    @Enumerated(EnumType.STRING)
    private TypeChambre typeChambre;

    public Chambre(int numEspace, Batiment batiment, int etage, List<Lit> lits, TypeChambre typeChambre) {
        super(numEspace, batiment, etage, lits);
        this.typeChambre = typeChambre;
    }

    public Chambre(int numEspace, Batiment batiment, int etage, TypeChambre typeChambre) {
        super(numEspace, batiment, etage);
        this.typeChambre = typeChambre;
    }

    public Chambre() {
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }
}










