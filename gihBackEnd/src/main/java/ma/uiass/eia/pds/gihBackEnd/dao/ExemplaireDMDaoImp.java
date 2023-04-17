package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        return null;
    }

    @Override
    public List<ExemplaireDm> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(ExemplaireDm exemplaireDm, int id) {

    }
}
