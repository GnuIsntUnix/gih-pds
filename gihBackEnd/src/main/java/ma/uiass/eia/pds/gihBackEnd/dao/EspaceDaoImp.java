package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;

import ma.uiass.eia.pds.gihBackEnd.model.Batiment;
import ma.uiass.eia.pds.gihBackEnd.model.Chambre;
import ma.uiass.eia.pds.gihBackEnd.model.Espace;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class EspaceDaoImp implements IEspaceDao{
    private EntityManager entityManager;
    private BatimentDaoImp batimentDaoImp;

    public EspaceDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
        batimentDaoImp = new BatimentDaoImp();
    }

    public void create(Espace espace) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(espace);
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
    public Espace getById(int id) {
        return entityManager.find(Espace.class, id);
    }

    @Override
    public List<Espace> getAll() {
        Query query = entityManager.createQuery("from Espace", Espace.class);
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
    public void update(Espace espace) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            System.out.println("begin");
            entityManager.merge(espace);
            transaction.commit();
            System.out.println("commited");
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Espace espace, int id) {

    }

    public List<Espace> getAllBatiment(int id) {
        Query query=entityManager.createQuery("from Espace e where e.batiment.idBatiment="+ id,Espace.class);
        return query.getResultList();
    }
    public List<Espace> getSemiAll(int id){
        List<Espace> list = new ArrayList<>();
        for (Espace e:getAll()){
            if(e.getBatiment()!=null && e.getBatiment().getIdBatiment()==id)
                list.add(e);
        }
        return list;
    }
}
