package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;
import ma.uiass.eia.pds.gihBackEnd.model.Historique;
import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AmbulanceDaoImp implements IAambulanceDao{
    private EntityManager entityManager= HibernateUtil.getEntityManager();

    public AmbulanceDaoImp() {

    }

    @Override
    public void create(Ambulance ambulance) {

    }

    @Override
    public Ambulance getById(int id) {
        return null;
    }

    @Override
    public List<Ambulance> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Ambulance ambulance) {

    }

    @Override
    public void update(Ambulance ambulance, int id) {

    }
}
