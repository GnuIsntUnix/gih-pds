package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import ma.uiass.eia.pds.gihBackEnd.model.State;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class AmbulanceDaoImp implements IAmbulanceDao{

    private EntityManager entityManager= HibernateUtil.getEntityManager();

    private Dao<Revision> revisionDao = new RevisionDaoImp();
    private Dao<State> stateDao = new StateDaoImp();


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
            if(!transaction.isActive()){
                transaction.begin();
            }
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

        if (ambulance.getState().getStateName().equalsIgnoreCase("f")){
            ambulance.setDateMiseEnCirculation(LocalDate.now());
        }
        State state = ambulance.getState();
        stateDao.create(state);
        try {
            if(!transaction.isActive())
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

    public void merge(Ambulance ambulance) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if(!transaction.isActive())
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

    public List<Ambulance> getByState(int id){
        Query query = entityManager.createQuery("from Ambulance a where a.state.id = "+id, Ambulance.class);
        return query.getResultList();
    }

    public Ambulance getByRevision(int id) {
        Query query = entityManager.createQuery("from Ambulance a where a.revision.id = "+id, Ambulance.class);
        return (Ambulance) query.getSingleResult();
    }
}
