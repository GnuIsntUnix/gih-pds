package ma.uiass.eia.pds.gihBackEnd.metier;

import ma.uiass.eia.pds.gihBackEnd.dao.BatimentDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Batiment;

import java.util.List;

public class ServiceBatiment {
    BatimentDaoImp batimentDaoImp=new BatimentDaoImp();
    public ServiceDaoImp serviceDaoImp = new ServiceDaoImp();
    public void deleteById(int id){
        Batiment batiment = batimentDaoImp.getById(id);
        batimentDaoImp.delete(batiment);
    }
    public void add(Batiment batiment){
        batimentDaoImp.create(batiment);
    }
    public Batiment searchById(int id){
        Batiment batiment = batimentDaoImp.getById(id);
        return batiment;
    }
    public List<Batiment> getAll(){
        return batimentDaoImp.getAll();
    }
}
