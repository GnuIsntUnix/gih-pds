package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;
import ma.uiass.eia.pds.gihBackEnd.model.Chambre;
import ma.uiass.eia.pds.gihBackEnd.model.Marque;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import java.util.List;

public class MarqueDaoImp implements Dao<Marque> {
    private EntityManager entityManager;

    public MarqueDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(Marque marque) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(marque);
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
    public Marque getById(int id) {
        return entityManager.find(Marque.class, id);
    }

    @Override
    public List<Marque> getAll() {
        Query query = entityManager.createQuery("from Marque");
        return query.getResultList();
    }

    @Override
    public void delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.remove(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}