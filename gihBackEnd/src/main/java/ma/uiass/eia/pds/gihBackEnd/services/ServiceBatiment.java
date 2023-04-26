package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.Batiment;
import ma.uiass.eia.pds.gihBackEnd.model.Service;

import java.util.List;

public class ServiceBatiment {
    private IBatimentDao batimentDaoImp;
    private IServiceDao serviceDaoImp;
    public ServiceBatiment(){
        batimentDaoImp=new BatimentDaoImp();
        serviceDaoImp=new ServiceDaoImp();
    }
    public void deleteById(int id){
        batimentDaoImp.delete(id);
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

    public List<Batiment> getByService(int id){
        Service service = serviceDaoImp.getById(id);
        return service.getBatiments();
    }
}
