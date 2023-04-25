package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class FournisseurDaoImp implements IFournisseurDao{


    private EntityManager entityManager;

    public FournisseurDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }
    @Override
    public void create(Fournisseur fournisseur) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(fournisseur);
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
    public Fournisseur getById(int id) {
        return null;
    }

    @Override
    public List<Fournisseur> getAll() {
        Query query = entityManager.createQuery("from Fournisseur", Fournisseur.class);
        return query.getResultList();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Fournisseur fournisseur) {

    }

    @Override
    public void update(Fournisseur fournisseur, int id) {

    }
}
