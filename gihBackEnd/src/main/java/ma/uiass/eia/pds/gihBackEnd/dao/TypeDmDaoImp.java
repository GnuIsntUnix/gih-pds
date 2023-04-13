package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.TypeDM;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class TypeDmDaoImp implements ITypeDmDao{
    private EntityManager entityManager;

    public TypeDmDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(TypeDM typeDM) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(typeDM);
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
    public TypeDM getById(int id) {
        return entityManager.find(TypeDM.class, id);
    }

    @Override
    public List<TypeDM> getAll() {
        Query query = entityManager.createQuery("from TypeDM");
        return query.getResultList();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(TypeDM typeDM, int id) {

    }
}
