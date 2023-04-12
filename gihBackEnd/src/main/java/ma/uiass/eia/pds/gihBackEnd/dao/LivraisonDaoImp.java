package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Livraison;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class LivraisonDaoImp implements ILivraisonDao{

    private EntityManager entityManager;

    public LivraisonDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    @Override
    public void create(Livraison livraison) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(livraison);
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
    public Livraison getById(int id) {
        return null;
    }

    @Override
    public List<Livraison> getAll() {
        Query query = entityManager.createQuery("from Livraison", Livraison.class);
        return query.getResultList();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Livraison livraison, int id) {

    }
}
