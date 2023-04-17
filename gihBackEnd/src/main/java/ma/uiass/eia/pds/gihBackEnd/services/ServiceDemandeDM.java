package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.DemandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IDemandeDao;
import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;

public class ServiceDemandeDM {
    private IDemandeDao demandeDao;
    public ServiceDemandeDM(){
        IDemandeDao demandeDao= new DemandeDaoImp();
    }

    public void add(DemandeDm demandeDm){
        demandeDao.create(demandeDm);
    }
}
