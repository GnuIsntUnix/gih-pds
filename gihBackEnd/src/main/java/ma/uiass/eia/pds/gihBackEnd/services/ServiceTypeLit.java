package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.TypeLitDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;
import java.util.List;

public class ServiceTypeLit {
    private Dao<TypeLit> typeLitDaoImp;
    public ServiceTypeLit(){
        typeLitDaoImp= new TypeLitDaoImp();
    }
    public void deleteById(int id){
        typeLitDaoImp.delete(id);
    }
    public void add(TypeLit typeLit){
        typeLitDaoImp.create(typeLit);
    }
    public TypeLit searchById(int id){
        TypeLit typeLit = typeLitDaoImp.getById(id);
        return typeLit;
    }
    public List<TypeLit> getAll(){
        return typeLitDaoImp.getAll();
    }
}
