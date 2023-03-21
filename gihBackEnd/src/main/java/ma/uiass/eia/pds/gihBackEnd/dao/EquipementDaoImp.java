package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Batiment;
import ma.uiass.eia.pds.gihBackEnd.model.Equipement;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class EquipementDaoImp implements Dao<Equipement>{
        private EntityManager entityManager;

        public EquipementDaoImp() {
            entityManager = HibernateUtil.getEntityManager();
        }

        public void create(Equipement equipement) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                this.entityManager.persist(equipement);
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
        public Equipement getById(int id) {
            return entityManager.find(Equipement.class, id);
        }

        @Override
        public List<Equipement> getAll() {
            Query query = entityManager.createQuery("from Equipement");
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
    public void update(Equipement equipement, int id) {

    }
}
