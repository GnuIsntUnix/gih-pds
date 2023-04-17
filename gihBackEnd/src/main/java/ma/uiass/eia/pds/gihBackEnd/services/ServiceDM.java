package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import java.util.ArrayList;
import java.util.List;

public class ServiceDM {
    private IDmDao dmDao;
    private IServiceDao serviceDao;
    private ITypeDmDao typeDmDao;

    public ServiceDM(){
        dmDao = new DmDaoImp();
        serviceDao = new ServiceDaoImp();
    }

    public List<ExemplaireDm> getDMsByService(int idService){
        Service s = serviceDao.getById(idService);
        List<ExemplaireDm> dms = s.getStock().getDms();
        return dms;
    }

    public void add(DM dm){

        dmDao.create(dm);

    }
    public List<DM> getAll(){

        return dmDao.getAll();

    }

    public DM searchById(int id){

        return dmDao.getById(id);

    }

    public List<DM> getDMbyIdType(int idType){
        TypeDM typeDM = typeDmDao.getById(idType);
        List<DM> dms = new ArrayList<>();

        return typeDM.getDms();
    }
}
