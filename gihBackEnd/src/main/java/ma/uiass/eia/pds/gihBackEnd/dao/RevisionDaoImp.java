package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class RevisionDaoImp implements IRevisionDao{
    private EntityManager entityManager;
    private AmbulanceDaoImp ambulanceDao = new AmbulanceDaoImp();

    public RevisionDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public void create(Revision revision) {
        EntityTransaction transaction = entityManager.getTransaction();
        Ambulance ambulance = revision.getAmbulance();
        ambulance.setKilometrage(revision.getKilometrage());
        try {
            transaction.begin();
            this.entityManager.persist(revision);
            ambulanceDao.update(ambulance);
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
    public Revision getById(int id) {
        return entityManager.find(Revision.class, id);
    }

    @Override
    public List<Revision> getAll() {
        Query query = entityManager.createQuery("from Revision", Revision.class);
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
    public void update(Revision revision) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.merge(revision);
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
    public void update(Revision revision, int id) {

    }
}
