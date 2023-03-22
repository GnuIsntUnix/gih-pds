package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class LitDaoImp implements ILitDao{
    private EntityManager entityManager;

    public LitDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(Lit lit) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(lit);
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
    public Lit getById(int id) {
        return entityManager.find(Lit.class, id);
    }

    @Override
    public List<Lit> getAll() {
        Query query = entityManager.createQuery("from Lit", Lit.class);
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
    public void update(Lit lit, int id) {
        Lit lit1 = getById(id);
        lit1.setEtat(lit.getEtat());
        lit1.setDisponibiliteLit(lit.getDisponibiliteLit());
        lit1.setEspace(lit.getEspace());
        entityManager.unwrap(Session.class).update(lit1);

    }


}
