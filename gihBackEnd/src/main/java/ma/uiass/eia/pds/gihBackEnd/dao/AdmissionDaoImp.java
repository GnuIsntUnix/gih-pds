package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Admission;
import ma.uiass.eia.pds.gihBackEnd.model.DisponibiliteLit;
import ma.uiass.eia.pds.gihBackEnd.model.EtatLit;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;

import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class AdmissionDaoImp implements IAdmissionDao{
    private EntityManager entityManager;

    public AdmissionDaoImp() {entityManager = HibernateUtil.getEntityManager();}


    @Override
    public void create(Admission admission) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            this.entityManager.persist(admission);
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
        Query query = entityManager.createQuery("from Utilisateur");
        return query.getResultList();

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Admission admission, int numAdmission) {
        if (admission != null) {
            admission.setDateFin(LocalDate.now());
            Lit lit = admission.getLit();
            if (lit != null) {
                lit.setDisponibiliteLit(DisponibiliteLit.Di);
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

}}}
