package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Batiment;
import ma.uiass.eia.pds.gihBackEnd.model.Utilisateur;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UtilisateurDaoImp implements Dao<Utilisateur>{

    private EntityManager entityManager;

    public UtilisateurDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }


    @Override
    public void create(Utilisateur utilisateur) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(utilisateur);
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
    public Utilisateur getById(int id) {
        return entityManager.find(Utilisateur.class, id);
    }

    @Override
    public List<Utilisateur> getAll() {
        Query query = entityManager.createQuery("from Utilisateur");
        return query.getResultList();
    }

    @Override
    public void delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.remove(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Utilisateur utilisateur) {

    }

    @Override
    public void update(Utilisateur utilisateur, int id) {

    }

    public Utilisateur verifierUser(String user, String mdp) {
        Query query = entityManager.createQuery("from Utilisateur where nomUtil=:user and motDePasse=:mdp");
        query.setParameter("user", user);
        query.setParameter("mdp", mdp);
        return (Utilisateur) query.getSingleResult();
    }
}
