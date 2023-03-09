package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import java.util.List;

public class ServiceDaoImp implements Dao<Service> {
    private final EntityManager entityManager;

    public ServiceDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(Service service) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(service);
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
    public Service getById(int id) {
        return entityManager.find(Service.class, id);
    }

    @Override
    public List<Service> getAll() {
        Query query = entityManager.createQuery("from Service");
        return query.getResultList();
    }
}
