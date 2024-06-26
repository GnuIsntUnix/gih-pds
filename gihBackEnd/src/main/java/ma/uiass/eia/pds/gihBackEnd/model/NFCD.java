package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NFCD")
public class NFCD extends State{
    public NFCD() {
        this.setStateName("NFCD");
    }

    public NFCD(double a, double b, double x, double y, Revision revision) {
        super(a, b, x, y, "NFCD", revision);
    }
    public String toString(){
        return "Non Fonctionelle Courte Durée";
    }
}
