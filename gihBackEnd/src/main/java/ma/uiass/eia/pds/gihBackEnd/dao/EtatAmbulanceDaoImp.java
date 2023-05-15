package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.EtatAmbulance;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class EtatAmbulanceDaoImp implements IEtatAmbulanceDao{

    private EntityManager entityManager = HibernateUtil.getEntityManager();

    @Override
    public void create(EtatAmbulance etatAmbulance) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(etatAmbulance);
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
    public EtatAmbulance getById(int id) {
        return entityManager.find(EtatAmbulance.class, id);
    }

    @Override
    public List<EtatAmbulance> getAll() {
        Query query = entityManager.createQuery("from EtatAmbulance", EtatAmbulance.class);
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
    public void update(EtatAmbulance etatAmbulance) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.merge(etatAmbulance);
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
    public void update(EtatAmbulance etatAmbulance, int id) {

    }
}
