package ma.uiass.eia.pds.gihBackEnd.dao;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class StockDaoImp implements IStockDao {
    private final EntityManager entityManager;

    public StockDaoImp() {
        entityManager = HibernateUtil.getEntityManager();
    }

    public void create(Stock stock) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(stock);
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
    public Stock getById(int id) {
        return entityManager.find(Stock.class, id);
    }

    @Override
    public List<Stock> getAll() {
        Query query = entityManager.createQuery("from Stock");
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
    public void update(Stock stock) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            System.out.println("begin");
            entityManager.merge(stock);
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

    public void affecterRessource(int idService, int n_lit) {

        Service service = entityManager.find(Service.class, idService);
        Lit lit = entityManager.find(Lit.class, n_lit);

        if (lit.getN_lit() > 0) {
            service.setIdService(idService);
            lit.setN_lit(lit.getN_lit() - 1);

            entityManager.persist(service);
            entityManager.persist(lit);
        } else {
            throw new RuntimeException("La ressource est épuisée.");
        }
    }

    @Override
    public void update(Stock stock, int id) {
        ServiceDaoImp serviceDaoImp=new ServiceDaoImp();
        EntityTransaction transaction=entityManager.getTransaction();
        try {
            transaction.begin();
            stock.setService(serviceDaoImp.getById(id));
            entityManager.merge(stock);
            transaction.commit();
        }catch(Exception e){
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
