package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import java.util.List;

public class ServiceDaoImp implements IServiceDao {
    private final EntityManager entityManager;
    private IEspaceDao espaceDao;

    public ServiceDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
        espaceDao = new EspaceDaoImp();
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
        espaceDao.create(new Stock(service));
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
    public void update(Service service, int id) {

    }
}
