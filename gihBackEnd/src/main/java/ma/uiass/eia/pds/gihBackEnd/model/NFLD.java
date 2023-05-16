package ma.uiass.eia.pds.gihBackEnd.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NFLD")
public class NFLD extends State{
    public NFLD() {
        this.setStateName("NFLD");
    }

    public NFLD(double a, double b, double x, double y, Revision revision) {
        super(a, b, x, y, "NFLD", revision);
    }
    public String toString(){
        return "Non Fonctionelle longue Durée";
    }
}
