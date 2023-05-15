package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class F extends State{
    @Column
    private double q=0;

    public F(double a, double b, double x, double y, Revision revision, double q) {
        super(a, b, x, y, "F", revision);
        this.q = q;
    }

    public F() {
        this.setStateName("F");
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }
    public  String toString(){
        return "Fonctionnelle";
    }
}
