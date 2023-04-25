package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.DetailLivraison;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DetailLivraisonDaoImp implements IDetailLivraisonDao{

    private EntityManager entityManager;

    public DetailLivraisonDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public void create(DetailLivraison detailLivraison) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(detailLivraison);
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
    public DetailLivraison getById(int id) {
        return null;
    }

    @Override
    public List<DetailLivraison> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(DetailLivraison detailLivraison) {

    }

    @Override
    public void update(DetailLivraison detailLivraison, int id) {

    }
}
