package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.Entity;

@Entity
public class NFCD extends State{
    public NFCD() {
    }

    public NFCD(double a, double b, double x, double y, String stateName, Revision revision) {
        super(a, b, x, y, stateName, revision);
    }
}
