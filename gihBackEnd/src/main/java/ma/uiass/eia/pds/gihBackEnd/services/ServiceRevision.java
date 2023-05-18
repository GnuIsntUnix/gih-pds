package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ServiceRevision {

    private IRevisionDao revisionDao = new RevisionDaoImp();

    private IAmbulanceDao ambulanceDao = new AmbulanceDaoImp();
    private EntityManager entityManager= HibernateUtil.getEntityManager();


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
        Query query = entityManager.createQuery("from Revision r where r.ambulance.id ="+idAmbulance);
        return query.getResultList();
    }

}
