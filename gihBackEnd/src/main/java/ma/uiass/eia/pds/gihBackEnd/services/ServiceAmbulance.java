package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

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
//        Revision revision=new Revision(LocalDate.now().plusDays(30),ambulance);
//        revisionDao.create(revision);
        //State state=new F(0,0,0,0,"Fonctionnel",revision,0);
        //stateDao.create(state);
        //ambulance.setState(stateDao.getById(1));
        //ambulanceDao.update(ambulance);

    }

    public void deleteById(int id) {
        ambulanceDao.delete(id);
    }
}
