package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.HistoriqueDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IHistoriqueDao;
import ma.uiass.eia.pds.gihBackEnd.model.Historique;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;

import java.util.ArrayList;
import java.util.List;

public class ServiceHistorique {

    private IHistoriqueDao historiqueDao = new HistoriqueDaoImp();

    public void deleteById(int id){

        historiqueDao.delete(id);

    }

    public Historique searchById(int id){

        return historiqueDao.getById(id);

    }

    public void add(Historique historique){

        historiqueDao.create(historique);

    }

    public List<Historique> getAll(){

        return historiqueDao.getAll();

    }

    public void update(Historique historique){
        historiqueDao.update(historique);
    }

    public List<Historique> getForAmbulance(int idAmbulance){
        List<Historique> historiques = historiqueDao.getAll();
        List<Historique> his = new ArrayList<>();
        historiques.forEach(historique -> {
            if (historique.getAmbulance().getId() == idAmbulance){
                his.add(historique);
            }
        });
        return his;
    }
}
