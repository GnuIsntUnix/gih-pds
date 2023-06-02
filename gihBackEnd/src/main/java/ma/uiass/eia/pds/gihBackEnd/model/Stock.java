package ma.uiass.eia.pds.gihBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("Stock")
public class Stock extends Espace{


    @OneToMany(mappedBy="stock")
    @JsonIgnore
    private  List<DM> dms = new ArrayList<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idService", referencedColumnName = "Id")
    private Service service;

    public Stock() {
    }

    public Stock(Service service) {
        super();
        this.service = service;
        service.setStock(this);
    }


    public List<DM> getDms() {
        dms=dms.stream()
                .distinct()
                .collect(Collectors.toList());
        return dms;
    }

    public void setDms(List<DM> dms) {
        this.dms = dms;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
