package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("Stock")
public class Stock extends Espace{

    public Stock() {
    }

    public Stock(int numEspace, Batiment batiment, int etage) {
        super(numEspace, batiment, etage);
    }

    public Stock(int numEspace, Batiment batiment, int etage, List<Lit> lits) {
        super(numEspace, batiment, etage, lits);
    }
}
