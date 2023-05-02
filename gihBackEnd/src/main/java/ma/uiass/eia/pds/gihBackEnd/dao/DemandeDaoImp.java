package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.DetailDemandeDm;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class DemandeDaoImp implements IDemandeDao {

    private EntityManager entityManager;

    public DemandeDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }
    @Override
    public void create(DemandeDm demandeDm) {
        EntityTransaction transaction = entityManager.getTransaction();
        List<DetailDemandeDm> demandeDms = demandeDm.getDetailDemandeDms();
        DetailDemandeDaoImp detailDemandeDaoImp = new DetailDemandeDaoImp();
        try {
            transaction.begin();
            this.entityManager.persist(demandeDm);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        demandeDms.forEach(detailDemandeDm -> {
            detailDemandeDm.setDemandeDm(demandeDm);
            detailDemandeDaoImp.create(detailDemandeDm);
        });

    }

    @Override
    public DemandeDm getById(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        DemandeDm demandeDm =null;
        try {
            transaction.begin();
            System.out.println("Transaction started successfully");
            demandeDm = entityManager.find(DemandeDm.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return demandeDm;
    }

    @Override
    public List<DemandeDm> getAll() {
        Query query = entityManager.createQuery("from DemandeDm", DemandeDm.class);
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
    public void update(DemandeDm demandeDm) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.merge(demandeDm);
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
    public void update(DemandeDm demandeDm, int id) {

    }
}
