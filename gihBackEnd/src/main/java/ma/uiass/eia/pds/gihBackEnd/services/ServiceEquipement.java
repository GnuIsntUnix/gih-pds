package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.EquipementDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IEquipementDao;
import ma.uiass.eia.pds.gihBackEnd.model.Equipement;

import java.util.List;

public class ServiceEquipement {
    private IEquipementDao equipementDaoImp;
    public ServiceEquipement(){
        equipementDaoImp = new EquipementDaoImp();
    }

    public void add(Equipement equipement){
        equipementDaoImp.create(equipement);
    }
    public void deleteById(int id){
        equipementDaoImp.delete(id);
    }
    public Equipement searchById(int id){
        Equipement equipement = equipementDaoImp.getById(id);
        return equipement;
    }
    public List<Equipement> getAll(){
        return equipementDaoImp.getAll();
    }
}
