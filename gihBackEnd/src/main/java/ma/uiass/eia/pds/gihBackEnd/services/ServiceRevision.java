package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.Historique;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;

import java.util.ArrayList;
import java.util.List;

public class ServiceRevision {

    private IRevisionDao revisionDao = new RevisionDaoImp();

    private IAmbulanceDao ambulanceDao = new AmbulanceDaoImp();

    public void deleteById(int id){

        revisionDao.delete(id);

    }

    public Revision searchById(int id){

        return revisionDao.getById(id);

    }

    public void add(Revision revision){

        revisionDao.create(revision);

    }

    public List<Revision> getAll(){

        return revisionDao.getAll();

    }

    public void update(Revision revision){
        revisionDao.update(revision);
    }

    public List<Revision> getOnAmbulance(int idAmbulance){
        List<Revision> revisions = revisionDao.getAll();
        List<Revision> rev = new ArrayList<>();
        revisions.forEach(revision -> {
            if (revision.getAmbulance().getId() == idAmbulance){
                rev.add(revision);
            }
        });
        return rev;
    }

}
