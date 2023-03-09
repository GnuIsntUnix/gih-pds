package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;
import ma.uiass.eia.pds.gihBackEnd.model.Chambre;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import java.util.List;

public class ChambreDaoImp implements Dao<Chambre>{
    private EntityManager entityManager;

    public ChambreDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(Chambre chambre) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(chambre);
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
    public Chambre getById(int id) {
        return entityManager.find(Chambre.class, id);
    }

    @Override
    public List<Chambre> getAll() {
        Query query = entityManager.createQuery("from Chambre");
        return query.getResultList();
    }
}
