package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ServiceDM {
    private IDemandeDao demandeDao;
    private DmDaoImp dmDao;
    private IServiceDao serviceDao;
    private ITypeDmDao typeDmDao;
    private ExemplaireDMDaoImp exemplaireDMDaoImp;


    public ServiceDM(){
        dmDao = new DmDaoImp();
        serviceDao = new ServiceDaoImp();
        demandeDao = new DemandeDaoImp();
    }

    public List<ExemplaireDm> getDMsByService(int idService){
        Service s = serviceDao.getById(idService);
        List<ExemplaireDm> dms = s.getStock().getDms();
        return dms;
    }

    public void add(DM dm){

        dmDao.create(dm);

    }

    public void addv2(DM dm){

        dmDao.createv2(dm);

    }
    public List<DM> getAll(){

        return dmDao.getAll();

    }

    public DM searchById(int id){

        return dmDao.getById(id);

    }

    public List<DM> getDMbyIdType(int idType){
        List<TypeDM> typeDMs = typeDmDao.getAll();
        List<DM> dms = new ArrayList<>();

        for (TypeDM tdm: typeDMs){
            if (tdm.getIdType()==idType){
                dms = tdm.getDms();
            }
        }

        return dms;
    }
    public void affecterDM(int id){
        DemandeDm demandeDm=demandeDao.getById(id);
        EntityManager entityManager= HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("Transaction started successfully");
        try {
            List<ExemplaireDm> dms= new ArrayList<>();
            for (DetailDemandeDm detailDemandeDm:demandeDm.getDetailDemandeDms()){
                 dms=detailDemandeDm.getDm().getExemplaireDmList();
                 for (ExemplaireDm dm:dms){
                    entityManager.merge(dm);
                }
            }
            transaction.commit();
            System.out.println("Transaction commited successfully");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
