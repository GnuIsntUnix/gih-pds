package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Stock")
public class Stock extends Espace{


    @OneToMany(mappedBy="stock")
    private List<ExemplaireDm> dms;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idService", referencedColumnName = "Id")
    private Service service;

    public Stock() {
    }

    public Stock(Service service) {
        super();
        this.service = service;
    }

    public List<ExemplaireDm> getDms() {
        return dms;
    }

    public void setDms(List<ExemplaireDm> dms) {
        this.dms = dms;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
