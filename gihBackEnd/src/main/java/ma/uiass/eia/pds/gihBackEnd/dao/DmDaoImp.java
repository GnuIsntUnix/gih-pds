package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.DMwithExemplaire;
import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class DmDaoImp implements IDmDao{

    private EntityManager entityManager;

    public DmDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public void create(DM dm) {
        IStockDao stockDao=new StockDaoImp();
        EntityTransaction transaction = entityManager.getTransaction();
        List<ExemplaireDm> exemplaireDms = null;
        if(dm instanceof DMwithExemplaire) {
            ExemplaireDm e1 = new ExemplaireDm(dm, stockDao.getById(1));
            ExemplaireDm e2 = new ExemplaireDm(dm, stockDao.getById(1));
            ExemplaireDm e3 = new ExemplaireDm(dm, stockDao.getById(1));
            ExemplaireDm e4 = new ExemplaireDm(dm, stockDao.getById(1));
            ExemplaireDm e5 = new ExemplaireDm(dm, stockDao.getById(1));
            List<ExemplaireDm> list = Arrays.asList(e1, e2, e3, e4, e5);
            ((DMwithExemplaire) dm).setExemplaireDmList(list);
            exemplaireDms = ((DMwithExemplaire) dm).getExemplaireDmList();
        }
        ExemplaireDMDaoImp exemplaireDMDaoImp = new ExemplaireDMDaoImp();
        try {
            transaction.begin();
            this.entityManager.persist(dm);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if(dm instanceof DMwithExemplaire){
            exemplaireDms.forEach(exemplaireDm -> {
                exemplaireDm.setDm(dm);
//                exemplaireDm.setStock(stockDao.getById(1));
                exemplaireDMDaoImp.create(exemplaireDm);
            });
        }

    }
    public void createv2(DM dm) {
        EntityTransaction transaction = entityManager.getTransaction();
        List<ExemplaireDm> exemplaireDms=null;
        if(dm instanceof DMwithExemplaire)
            exemplaireDms = ((DMwithExemplaire) dm).getExemplaireDmList();
        else {
            create(dm);
        }
        ExemplaireDMDaoImp exemplaireDMDaoImp = new ExemplaireDMDaoImp();
        try {
            transaction.begin();
            this.entityManager.merge(dm);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        exemplaireDms.forEach(exemplaireDm -> {
            exemplaireDm.setDm(dm);
            exemplaireDMDaoImp.create(exemplaireDm);
        });
    }

    @Override
    public DM getById(int id) {
        return entityManager.find(DM.class, id);
    }

    @Override
    public List<DM> getAll() {
        Query query = entityManager.createQuery("from DM", DM.class);
        return query.getResultList();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(DM dm) {

    }

    @Override
    public void update(DM dm, int id) {

    }
}
