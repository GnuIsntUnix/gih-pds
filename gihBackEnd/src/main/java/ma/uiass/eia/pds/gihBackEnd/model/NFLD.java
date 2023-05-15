package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NFLD")
public class NFLD extends State{
    public NFLD() {
    }

    public NFLD(double a, double b, double x, double y, String stateName, Revision revision) {
        super(a, b, x, y, stateName, revision);
    }
}
