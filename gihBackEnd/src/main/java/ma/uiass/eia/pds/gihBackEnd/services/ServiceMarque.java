package ma.uiass.eia.pds.gihBackEnd.metier;

import ma.uiass.eia.pds.gihBackEnd.dao.MarqueDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Marque;

import java.util.List;

public class ServiceMarque {
    MarqueDaoImp marqueDaoImp= new MarqueDaoImp();
    public void deleteById(int id){
        Marque marque=marqueDaoImp.getById(id);
        marqueDaoImp.delete(marque);
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
