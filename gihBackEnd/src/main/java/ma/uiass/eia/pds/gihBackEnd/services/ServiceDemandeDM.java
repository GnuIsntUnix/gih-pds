package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ServiceDemandeDM {
    private IServiceDao serviceDaoImp = new ServiceDaoImp();
    private IDemandeDao demandeDao=new DemandeDaoImp();
    private ServiceDM serviceDM=new ServiceDM();
    private IStockDao stockDao=new StockDaoImp();
    public ServiceDemandeDM(){
        IDemandeDao demandeDao= new DemandeDaoImp();
    }

    public void add(DemandeDm demandeDm){
        demandeDao.create(demandeDm);
    }

    public List<DemandeDm> getDemandeDMByService(int id) {
        List<DemandeDm> demandeDm = demandeDao.getAll();
        List<DemandeDm> byService = new ArrayList<>();
        demandeDm.forEach(demandeDm1 -> {
            if (demandeDm1.getService().getIdService() == id)
                byService.add(demandeDm1);
        });
        return byService;

    }

    public void deleteById(int id) {
        demandeDao.delete(id);
    }
    public void traiter(DemandeDm demandeDm){
        Service service=serviceDaoImp.getById(demandeDm.getService().getIdService());
        boolean valide=true;
        for(DetailDemandeDm detailDemandeDm:demandeDm.getDetailDemandeDms()){
            int i=1;
            if(detailDemandeDm.getDm() instanceof DMwithExemplaire){
                if(serviceDM.number(1,detailDemandeDm.getDm().getId())>=detailDemandeDm.getQte()){
                    for(ExemplaireDm exemplaireDm:((DMwithExemplaire)detailDemandeDm.getDm()).getExemplaireDmList()) {
                        serviceDM.affecterExemplaire(exemplaireDm.getId(), service.getStock().getIdEspace());
                        i++;
                        if (i > detailDemandeDm.getQte())
                            break;
                    }
                }
                else{
                    valide=false;
                }
            }
            else{
                if(((DMwithQuantity)detailDemandeDm.getDm()).getQuantite()>=detailDemandeDm.getQte()){
                    for(int j=1;j<=detailDemandeDm.getQte();j++){
                        int id= stockDao.getById(1).getDms().get(j).getId();
                        serviceDM.affecterDm(id,service.getStock().getIdEspace());
                    }
                }
                else{
                    valide=false;
                }

            }
        }
        demandeDao.update(demandeDm);
        if (valide) {
            demandeDm.setValide(true);
            demandeDao.update(demandeDm);
        }
    }
}
