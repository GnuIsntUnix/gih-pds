package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ServiceLits {
    private ILitDao litDaoImp;
    private IServiceDao serviceDaoImp;
    private ICommandeDao commandeDaoImp;

    private IStockDao stockDaoImp = new StockDaoImp();
    private ITypeLitDao typeLitDao = new TypeLitDaoImp();
    private IEspaceDao espaceDao = new EspaceDaoImp();

    public ServiceLits() {
        litDaoImp = new LitDaoImp();
        serviceDaoImp = new ServiceDaoImp();
        commandeDaoImp = new CommandeDaoImp();
    }

    public void deleteById(int id){

        litDaoImp.delete(id);

    }

    public Lit searchById(int id){

        return litDaoImp.getById(id);

    }

    public void add(Lit lit){

        litDaoImp.create(lit);

    }

    public List<Lit> getAll(){

        return litDaoImp.getAll();

    }
    public void update(Lit lit, int id){
        litDaoImp.update(lit, id);
    }

    public List<Lit> getLitsByService(int id){
        Service service = serviceDaoImp.getById(id);
        List<Lit> lits = new ArrayList<>();
        service.getBatiments().forEach(batiment -> {
            batiment.getEspaces().forEach(espace -> lits.addAll(espace.getLits()));
        });
        return lits;
    }
    public void affecter(int id){
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
    public List<Lit> getLitsByEspace(int id){
        Espace espace = espaceDao.getById(id);
        List<Lit> lits = new ArrayList<>();;
        return espace.getLits();
    }

    public void switchEtat(int id){
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
    public void changelit(int id){
        Lit lit = litDaoImp.getById(id);
        if(lit.getEtat()==EtatLit.O)
            throw new RuntimeException("working properly");
    }
    public List<Lit> getOp(int idService){
        Service service=serviceDaoImp.getById(idService);
        List<Lit> lits = getLitsInStock(service.getStock().getIdEspace());
        List<Lit> litsOp = new ArrayList<>();
        for (Lit lit : lits) {
            if (lit.getTypeLit().equals(EtatLit.O))
                litsOp.add(lit);
        }
        return litsOp;
    }
    public List<Lit> getDef(int idService){
        Service service=serviceDaoImp.getById(idService);
        List<Lit> lits = getLitsInStock(service.getStock().getIdEspace());
        List<Lit> litsDef = new ArrayList<>();
        for (Lit lit : lits) {
            if (lit.getTypeLit().equals(EtatLit.D))
                litsDef.add(lit);
        }
        return litsDef;
    }
    public void addLitstoStock(Service service){
        ///'
    }

    public List<Lit> getLitsInStock(int idStock){
        List<Lit> lits = litDaoImp.getAll();
        List<Lit> stock = new ArrayList<>();
        for (Lit lit : lits) {
            if (lit.getEspace().getIdEspace() == idStock){
                stock.add(lit);
            }
        }
        return stock;
    }

    public List<Lit> getLitsByTypeInStock(int idService, int idTypeLit){
        TypeLit typeLit = typeLitDao.getById(idTypeLit);
        List<Lit> lits = getLitsInStock(serviceDaoImp.getById(idService).getStock().getIdEspace());
        List<Lit> litList = new ArrayList<>();
        for (Lit lit : lits) {
            if (lit.getTypeLit().equals(typeLit))
                litList.add(lit);
        }
        return litList;
    }


    public void mergeL(Lit lit) {
        EntityManager entityManager=HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(lit);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}



