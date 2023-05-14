package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class F extends State{
    @Column
    private double q;

    public F(double a, double b, double x, double y, String stateName, Revision revision, double q) {
        super(a, b, x, y, stateName, revision);
        this.q = q;
    }

    public F() {
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }
}
