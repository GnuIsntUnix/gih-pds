package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;
import ma.uiass.eia.pds.gihBackEnd.model.Marque;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ExemplaireDMDaoImp implements IExemplaireDMDao{



    private EntityManager entityManager;

    public ExemplaireDMDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public void create(ExemplaireDm exemplaireDm) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(exemplaireDm);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public ExemplaireDm getById(int id) {
        return entityManager.find(ExemplaireDm.class, id);
    }

    @Override
    public List<ExemplaireDm> getAll() {
        Query query = entityManager.createQuery("from ExemplaireDm");
        return query.getResultList();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(ExemplaireDm exemplaireDm) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.merge(exemplaireDm);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(ExemplaireDm exemplaireDm, int id) {
        exemplaireDm.getStock().setIdEspace(id);
    }


}
