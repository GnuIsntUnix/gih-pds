package ma.uiass.eia.pds.gihBackEnd.util;

import javax.persistence.*;



public class HibernateUtil {
    private static EntityManager entityManager;
    public static EntityManager getEntityManager(){
        if (entityManager == null) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_SC");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
}
