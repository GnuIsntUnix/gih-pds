package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class F extends State{
    @Column
    private static double q=7./9;

    public F(double a, double b, double x, double y, Revision revision) {
        super(a, b, x, y, "F", revision);
    }

    public F() {
        this.setStateName("F");
    }

    public static double getQ() {
        return q;
    }

    public static void setQ(double q) {
        F.q = q;
    }

    public  String toString(){
        return "Fonctionnelle";
    }
}
