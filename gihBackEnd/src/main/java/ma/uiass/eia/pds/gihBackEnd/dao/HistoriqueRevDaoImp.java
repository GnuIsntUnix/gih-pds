package ma.uiass.eia.pds.gihBackEnd.dao;

import ma.uiass.eia.pds.gihBackEnd.model.Historique;

import javax.persistence.EntityManager;
import java.util.List;

public class HistoriqueRevDaoImp implements IHistoriqueRevDao {

    private EntityManager entityManager;

    @Override
    public void create(Historique historique) {

    }

    @Override
    public Historique getById(int id) {
        return null;
    }

    @Override
    public List<Historique> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Historique historique) {

    }

    @Override
    public void update(Historique historique, int id) {

    }
}
