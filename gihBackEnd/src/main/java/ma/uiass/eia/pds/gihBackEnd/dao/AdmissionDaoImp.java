package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Admission;
import ma.uiass.eia.pds.gihBackEnd.model.DisponibiliteLit;
import ma.uiass.eia.pds.gihBackEnd.model.EtatLit;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;

import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class AdmissionDaoImp implements IAdmissionDao{
    private EntityManager entityManager;
    private ILitDao litDao = new LitDaoImp();

    public AdmissionDaoImp() {entityManager = HibernateUtil.getEntityManager();}


    @Override
    public void create(Admission admission) {
        EntityTransaction transaction = entityManager.getTransaction();
        Lit lit = admission.getLit();
        lit.setDisponibiliteLit(DisponibiliteLit.O);
        try {
            transaction.begin();
            this.entityManager.merge(lit);
            this.entityManager.merge(admission);
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
    public Admission getById(int numAdmission) {return entityManager.find(Admission.class, numAdmission);}

    @Override
    public List<Admission> getAll() {
        Query query = entityManager.createQuery("from Admission");
        return query.getResultList();

    }

    public Admission getAdmissionByLit(int id){
        Admission admission = null;
        try{
            Query query = entityManager.createQuery("from Admission a where a.lit.n_lit =: id");
            query.setParameter("id", id);
            admission = (Admission) query.getSingleResult();
        }
        catch (NoResultException ignored){

        }
        return admission;
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
    public void update(Admission admission) {
        if (admission != null) {
            admission.setDateFin(LocalDate.now());
            Lit lit = admission.getLit();
            if (lit != null) {
                lit.setDisponibiliteLit(DisponibiliteLit.Di);
                lit.setAdmission(null);
                admission.setLit(null);
            }
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                this.entityManager.merge(admission);
                this.entityManager.merge(lit);
                transaction.commit();
            }
            catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

}}

    @Override
    public void update(Admission admission, int id) {

    }
}
