package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.IMarqueDao;
import ma.uiass.eia.pds.gihBackEnd.dao.MarqueDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Marque;

import java.util.List;

public class ServiceMarque {
    private IMarqueDao marqueDaoImp;
    public ServiceMarque(){
        marqueDaoImp= new MarqueDaoImp();
    }
    public void deleteById(int id){
        marqueDaoImp.delete(id);
    }
    public void add(Marque marque){
        marqueDaoImp.create(marque);
    }
    public Marque searchById(int id){
        Marque marque = marqueDaoImp.getById(id);
        return marque;
    }
    public List<Marque> getAll(){
        return marqueDaoImp.getAll();
    }
}
