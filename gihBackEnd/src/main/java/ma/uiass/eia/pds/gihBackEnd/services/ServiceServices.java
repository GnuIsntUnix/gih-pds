package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Service;

import java.util.List;

public class ServiceServices {
    private Dao<Service> serviceDaoImp;
    public ServiceServices(){
        serviceDaoImp=new ServiceDaoImp();
    }
    public void add(Service s){
        serviceDaoImp.create(s);
    }
    public void deleteById(int id){
        serviceDaoImp.delete(id);
    }
    public  Service searchById(int id){
        return serviceDaoImp.getById(id);
    }
    public List<Service> getAll(){
        return serviceDaoImp.getAll();
    }
}
