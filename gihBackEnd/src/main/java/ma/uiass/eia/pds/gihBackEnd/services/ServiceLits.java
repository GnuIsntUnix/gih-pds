package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ServiceLits {
    private ILitDao litDaoImp = new LitDaoImp();
    private IServiceDao serviceDaoImp = new ServiceDaoImp();
    private IStockDao stockDaoImp = new StockDaoImp();
    private ITypeLitDao typeLitDao = new TypeLitDaoImp();
    private IEspaceDao espaceDao = new EspaceDaoImp();


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
    public List<Lit> getLitsByEspace(int id){
        Espace espace = espaceDao.getById(id);
        List<Lit> lits = new ArrayList<>();;
        return espace.getLits();
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


}



