package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.DetailDemandeDm;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class DetailDemandeDaoImp implements IDetailDemandeDao{

    private EntityManager entityManager;

    public DetailDemandeDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }
    @Override
    public void create(DetailDemandeDm detailDemandeDm) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(detailDemandeDm);
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
    public DetailDemandeDm getById(int id) {
        return entityManager.find(DetailDemandeDm.class, id);
    }

    @Override
    public List<DetailDemandeDm> getAll() {
        Query query = entityManager.createQuery("from DetailDemandeDm", DetailDemandeDm.class);
        return query.getResultList();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(DetailDemandeDm detailDemandeDm) {

    }

    @Override
    public void update(DetailDemandeDm detailDemandeDm, int id) {

    }
}
