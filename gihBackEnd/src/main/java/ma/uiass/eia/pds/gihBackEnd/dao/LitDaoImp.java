package ma.uiass.eia.pds.gihBackEnd.dao;

import javax.persistence.*;

import ma.uiass.eia.pds.gihBackEnd.model.*;
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
    public void update(Lit lit) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.merge(lit);
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
    public void update(Lit lit, int id) {
        Lit lit1 = getById(id);
        lit1.setEtat(lit.getEtat());
        lit1.setDisponibiliteLit(lit.getDisponibiliteLit());
        lit1.setEspace(lit.getEspace());
        entityManager.unwrap(Session.class).update(lit1);

    }


    @Override
    public List<Lit> getLitbyNumber(int quantite, TypeLit typeLit) {
        Query query = entityManager.createQuery("from Lit where etat = :etat and idType = :id", Lit.class).setMaxResults(quantite);
        query.setParameter("etat", EtatLit.E);
        query.setParameter("id", typeLit.getIdType());
        return query.getResultList();
    }
    public void affecter(int id){
        CommandeDaoImp commandeDaoImp=new CommandeDaoImp();
        ServiceDaoImp serviceDaoImp=new ServiceDaoImp();
        Commande c = commandeDaoImp.getById(id);
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Service serviceStock = serviceDaoImp.getById(1);
        Espace stock = serviceStock.getStock();
        try{
            Query query=entityManager.createQuery("from tlit where idTypeLit ="+c.getTypeLit() +"and idEspace="+stock.getIdEspace()+";");
            query.setMaxResults(c.getQuantite());
            List<Lit> lits=query.getResultList();
            serviceStock.getStock().getLits().forEach(lit -> {
                lit.setEspace(c.getService().getStock());
            });
            for (Lit lit : lits) {
                entityManager.merge(lit);
            }
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
    public void switchEtat(int id){
        LitDaoImp litDaoImp=new LitDaoImp();
        Lit lit=litDaoImp.getById(id);
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        if(lit.getEtat()==EtatLit.D)
            lit.setEtat(EtatLit.O);
        else if (lit.getEtat()==EtatLit.O)
            lit.setEtat(EtatLit.D);

        try {
            entityManager.merge(lit);
            transaction.commit();
        }catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        }
    }

}
