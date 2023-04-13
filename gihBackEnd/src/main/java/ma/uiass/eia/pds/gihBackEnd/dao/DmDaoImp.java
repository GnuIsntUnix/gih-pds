package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class DmDaoImp implements IDmDao{

    private EntityManager entityManager;

    public DmDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public void create(DM dm) {
        EntityTransaction transaction = entityManager.getTransaction();
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
    }

    @Override
    public DM getById(int id) {
        return null;
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
    public void update(DM dm, int id) {

    }
}
