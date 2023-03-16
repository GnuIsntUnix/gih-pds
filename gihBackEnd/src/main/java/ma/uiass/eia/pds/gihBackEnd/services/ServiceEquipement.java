package ma.uiass.eia.pds.gihBackEnd.metier;

import ma.uiass.eia.pds.gihBackEnd.dao.EquipementDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Equipement;

import java.util.List;

public class ServiceEquipement {
    EquipementDaoImp equipementDaoImp = new EquipementDaoImp();

    public void add(Equipement equipement){
        equipementDaoImp.create(equipement);
    }
    public void deleteById(int id){
        Equipement equipement = equipementDaoImp.getById(id);
        equipementDaoImp.delete(equipement);
    }
    public Equipement searchById(int id){
        Equipement equipement = equipementDaoImp.getById(id);
        return equipement;
    }
    public List<Equipement> getAll(){
        return equipementDaoImp.getAll();
    }
}
