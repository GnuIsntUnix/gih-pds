package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ServiceLits {
    private ILitDao litDaoImp;
    private IServiceDao serviceDaoImp;
    private ICommandeDao commandeDaoImp;

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


}



