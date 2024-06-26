package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.DmDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.ExemplaireDMDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IDmDao;
import ma.uiass.eia.pds.gihBackEnd.dao.IExemplaireDMDao;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.DMwithExemplaire;
import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;

import java.util.ArrayList;
import java.util.List;

public class ServiceExemplaireDM {
    private IDmDao dmDao;
    private IExemplaireDMDao exemplaireDMDao;

    public ServiceExemplaireDM() {
        dmDao = new DmDaoImp();
        exemplaireDMDao = new ExemplaireDMDaoImp();
    }

    public void add(ExemplaireDm exemplaire) {

        exemplaireDMDao.create(exemplaire);

    }

    public List<ExemplaireDm> getByDm(int idDm){
        DM dm = dmDao.getById(idDm);
        return ((DMwithExemplaire)dm).getExemplaireDmList();
    }

    public List<ExemplaireDm> getBySockAndDm(int id) {
        List<ExemplaireDm> list=new ArrayList<>();
        if (dmDao.getById(id) instanceof DMwithExemplaire){
            for(ExemplaireDm exemplaireDm:((DMwithExemplaire) dmDao.getById(id)).getExemplaireDmList()){
                if (exemplaireDm.getStock().getIdEspace()==1)
                    list.add(exemplaireDm);
            }
        }
        return list;
    }
}
