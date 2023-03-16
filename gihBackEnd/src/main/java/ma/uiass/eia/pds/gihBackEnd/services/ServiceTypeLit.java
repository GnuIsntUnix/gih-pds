package ma.uiass.eia.pds.gihBackEnd.metier;

import ma.uiass.eia.pds.gihBackEnd.dao.TypeLitDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;
import java.util.List;

public class MetierTypeLit {
    TypeLitDaoImp typeLitDaoImp = new TypeLitDaoImp();
    public void deleteById(int id){
        TypeLit typeLit = typeLitDaoImp.getById(id);
        typeLitDaoImp.delete(typeLit);
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
