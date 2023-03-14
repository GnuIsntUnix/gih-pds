package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Batiment;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class BatimentDaoImp implements Dao<Batiment>{
    private EntityManager entityManager;

    public BatimentDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(Batiment batiment) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(batiment);
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
    public Batiment getById(int id) {
        return entityManager.find(Batiment.class, id);
    }

    @Override
    public List<Batiment> getAll() {
        Query query = entityManager.createQuery("from Chambre");
        return query.getResultList();
    }

    @Override
    public void delete(int id) {

    }
}
