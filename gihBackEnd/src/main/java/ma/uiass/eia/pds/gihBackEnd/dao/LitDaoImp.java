package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import java.util.List;

public class LitDaoImp implements Dao<Lit>{
    private EntityManager entityManager;

    public LitDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(Lit lit) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(lit);
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
    public Lit getById(int id) {
        return entityManager.find(Lit.class, id);
    }

    @Override
    public List<Lit> getAll() {
        Query query = entityManager.createQuery("from Lit");
        return query.getResultList();
    }

    @Override
    public void delete(int id) {
        entityManager.remove(getById(id));
    }

//    public List<Lit> getAllByChambre(int id) {
//        Query query = entityManager.createQuery("from Lit where idChambre =" + id);
//        return query.getResultList();
//    }
}
