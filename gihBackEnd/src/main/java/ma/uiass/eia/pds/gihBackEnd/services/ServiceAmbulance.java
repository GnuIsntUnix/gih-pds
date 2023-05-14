package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.EtatAmbulance;
import ma.uiass.eia.pds.gihBackEnd.model.Historique;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ServiceAmbulance {
    private IAmbulanceDao ambulanceDao = new AmbulanceDaoImp();
    private IHistoriqueDao historiqueDao = new HistoriqueDaoImp();
    private IRevisionDao revisionDao = new RevisionDaoImp();
    private IEtatAmbulanceDao etatAmbulanceDao = new EtatAmbulanceDaoImp();

    public ServiceAmbulance() {

    }
    public void add(Ambulance ambulance){
        EtatAmbulance etatAmbulance=new EtatAmbulance("Trés Bon");
        ambulanceDao.create(ambulance);
        etatAmbulanceDao.create(etatAmbulance);
        Historique historique=new Historique(LocalDate.now(),LocalDate.now().plusDays(30),ambulance,etatAmbulance);
        List<Historique> list=ambulance.getHistoriques();
        list.add(historique);
        ambulance.setHistoriques(list);
        List<Historique> liste=etatAmbulance.getHistoriques();
        liste.add(historique);
        etatAmbulance.setHistoriques(liste);

        historiqueDao.create(historique);
        etatAmbulanceDao.update(etatAmbulance);
        ambulanceDao.update(ambulance);

    }

    public void deleteById(int id) {
        ambulanceDao.delete(id);
    }
}
