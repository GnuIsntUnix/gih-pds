package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.DemandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IDemandeDao;
import ma.uiass.eia.pds.gihBackEnd.dao.IServiceDao;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceDemandeDM {
    private IServiceDao serviceDaoImp = new ServiceDaoImp();
    private IDemandeDao demandeDao;
    public ServiceDemandeDM(){
        IDemandeDao demandeDao= new DemandeDaoImp();
    }

    public void add(DemandeDm demandeDm){
        demandeDao.create(demandeDm);
    }

    public List<DemandeDm> getDemandeDMByService(int id) {
        Service service = serviceDaoImp.getById(id);
        List<DemandeDm> Demandes = new ArrayList<>();
        return service.getDemandeDms();

    }

    public void deleteById(int id) {
        demandeDao.delete(id);
    }
}
