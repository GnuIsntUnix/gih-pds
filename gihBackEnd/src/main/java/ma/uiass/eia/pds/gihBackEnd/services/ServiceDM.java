package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.DmDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IDmDao;
import ma.uiass.eia.pds.gihBackEnd.dao.IServiceDao;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ServiceDM {
    private IDmDao dmDao;
    private IServiceDao serviceDao;

    public ServiceDM(){
        dmDao = new DmDaoImp();
        serviceDao = new ServiceDaoImp();
    }

    public List<DM> getDMsByService(int idService){
        Service s = serviceDao.getById(idService);
        List<DM> dms = s.getStock().getDms();
        return dms;
    }

    public void add(DM dm){

        dmDao.create(dm);

    }
    public void affecter(int id){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

    }
}
