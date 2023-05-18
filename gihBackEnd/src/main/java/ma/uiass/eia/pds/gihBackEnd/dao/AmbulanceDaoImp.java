package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class AmbulanceDaoImp implements IAmbulanceDao{

    private EntityManager entityManager= HibernateUtil.getEntityManager();

    @Override
    public void create(Ambulance ambulance) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            this.entityManager.persist(ambulance);
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
    public Ambulance getById(int id) {
        return entityManager.find(Ambulance.class, id);
    }

    @Override
    public List<Ambulance> getAll() {
        Query query = entityManager.createQuery("from Ambulance", Ambulance.class);
        return query.getResultList();
    }

    @Override
    public void delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.remove(this.getById(id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Ambulance ambulance) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.merge(ambulance);
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
    public void update(Ambulance ambulance, int id) {

    }
}
