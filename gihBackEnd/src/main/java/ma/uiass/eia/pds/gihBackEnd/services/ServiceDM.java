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
        TypeDM typeDM = typeDmDao.getById(idType);
        List<DM> dms = new ArrayList<>();

        return typeDM.getDms();
    }
    public void affecterDM(int id){
        DemandeDm demandeDm=demandeDao.getById(id);
        EntityManager entityManager= HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Service service = serviceDao.getById(1);
        try {
            Query query = entityManager.createQuery("from texemplairesdm;");
            List<DM> dms=query.getResultList();
            service.getStock().getDms().forEach(dm -> {
                dm.setStock(demandeDm.getService().getStock());
            });
            for (DM dm:dms){
                entityManager.merge(dm);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        entityManager.close();
        }
    }
}
