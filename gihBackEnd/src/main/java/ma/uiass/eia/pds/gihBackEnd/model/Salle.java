package ma.uiass.eia.pds.gihBackEnd.model;


import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Salle")
public class Salle extends Espace{

    @Column(name = "SousType")
    @Enumerated(EnumType.STRING)
    private TypeSalle typeSalle;

    public Salle(int numEspace, Batiment batiment, int etage, TypeSalle typeSalle) {
        super(numEspace, batiment, etage);
        this.typeSalle = typeSalle;
    }

    public Salle(int numEspace, Batiment batiment, int etage, List<Lit> lits, TypeSalle typeSalle) {
        super(numEspace, batiment, etage, lits);
        this.typeSalle = typeSalle;
    }

    public Salle() {
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    public void setTypeSalle(TypeSalle typeSalle) {
        this.typeSalle = typeSalle;
    }
}
