package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.ILitDao;
import ma.uiass.eia.pds.gihBackEnd.dao.IServiceDao;
import ma.uiass.eia.pds.gihBackEnd.dao.LitDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import java.util.ArrayList;
import java.util.List;

public class ServiceLits {
    private ILitDao litDaoImp;
    private IServiceDao serviceDaoImp;

    public ServiceLits() {
        litDaoImp = new LitDaoImp();
        serviceDaoImp = new ServiceDaoImp();
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


}



