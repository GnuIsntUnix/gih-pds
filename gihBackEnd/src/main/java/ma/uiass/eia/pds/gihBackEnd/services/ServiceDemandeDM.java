package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.DemandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IDemandeDao;
import ma.uiass.eia.pds.gihBackEnd.dao.IServiceDao;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.DetailDemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;
import ma.uiass.eia.pds.gihBackEnd.model.Service;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ServiceDemandeDM {
    private IServiceDao serviceDaoImp = new ServiceDaoImp();
    private IDemandeDao demandeDao=new DemandeDaoImp();
    private ServiceDM serviceDM=new ServiceDM();
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
    public void traiter(DemandeDm demandeDm){
        System.out.println("i am in traiter() methode"+demandeDm);
        Service service=serviceDaoImp.getById(demandeDm.getService().getIdService());
        boolean valide=true;
        for(DetailDemandeDm detailDemandeDm:demandeDm.getDetailDemandeDms()){
            System.out.println("inside for"+detailDemandeDm);
            int i=1;
            if(serviceDM.number(1,detailDemandeDm.getDm().getId())>=detailDemandeDm.getQte()){
                System.out.println("inside if true");
                for(ExemplaireDm exemplaireDm:detailDemandeDm.getDm().getExemplaireDmList()) {
                    System.out.println("i am inside for");
                    System.out.println(exemplaireDm);
                    serviceDM.affecterExemplaire(exemplaireDm.getId(), service.getStock().getIdEspace());
                    i++;
                    if (i > detailDemandeDm.getQte())
                        break;
                }
            }
            else{
                valide=false;
                System.out.println("indside else false");
            }
        }
        if (valide) {
            demandeDm.setValide(true);
            demandeDao.update(demandeDm);
            System.out.println("indside valide if");
        }
    }
}
