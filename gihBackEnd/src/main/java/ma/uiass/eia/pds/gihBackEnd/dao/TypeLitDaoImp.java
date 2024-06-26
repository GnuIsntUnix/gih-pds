package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;
import ma.uiass.eia.pds.gihBackEnd.model.Chambre;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import java.util.List;

public class TypeLitDaoImp implements ITypeLitDao {
    private EntityManager entityManager;

    public TypeLitDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(TypeLit typeLit) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(typeLit);
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
    public TypeLit getById(int id) {
        return entityManager.find(TypeLit.class, id);
    }

    @Override
    public List<TypeLit> getAll() {
        Query query = entityManager.createQuery("from TypeLit");
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
    public void update(TypeLit typeLit) {

    }

    @Override
    public void update(TypeLit typeLit, int id) {

    }
}