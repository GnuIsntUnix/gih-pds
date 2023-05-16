package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import javax.persistence.Query;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ServiceAmbulance {
    private IAmbulanceDao ambulanceDao = new AmbulanceDaoImp();
    private IRevisionDao revisionDao = new RevisionDaoImp();
    private IStateDao stateDao=new StateDaoImp();

    public ServiceAmbulance() {

    }
    public void add(Ambulance ambulance){
        ambulanceDao.create(ambulance);
        ambulance.setState(stateDao.getById(1));
        System.out.println(stateDao.getById(1));
        ambulanceDao.update(ambulance);
    }
    public Ambulance searchById(int id){
        Ambulance ambulance = ambulanceDao.getById(id);
        return ambulance;
    }

    public void deleteById(int id) {
        ambulanceDao.delete(id);
    }


}
