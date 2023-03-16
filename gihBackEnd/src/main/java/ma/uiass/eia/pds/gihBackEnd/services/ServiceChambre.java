package ma.uiass.eia.pds.gihBackEnd.metier;

import ma.uiass.eia.pds.gihBackEnd.dao.BatimentDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.ChambreDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Chambre;

import java.util.List;

public class ServiceChambre {
    ChambreDaoImp chambreDaoImp = new ChambreDaoImp();
    BatimentDaoImp batimentDaoImp = new BatimentDaoImp();
    public void deleteById(int id){
        Chambre chambre = chambreDaoImp.getById(id);
        chambreDaoImp.delete(chambre);
    }
    public void add(Chambre chambre){
        chambreDaoImp.create(chambre);
    }
    public Chambre searchById(int id){
        Chambre chambre = chambreDaoImp.getById(id);
        return chambre;
    }
    public List<Chambre> getAll(){
        return chambreDaoImp.getAll();
    }
}
