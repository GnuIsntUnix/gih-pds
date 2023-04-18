package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import java.util.ArrayList;
import java.util.List;

public class ServiceDM {
    private DmDaoImp dmDao;
    private IServiceDao serviceDao;
    private ITypeDmDao typeDmDao = new TypeDmDaoImp();

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

    public void addv2(DM dm){

        dmDao.createv2(dm);

    }
    public List<DM> getAll(){

        return dmDao.getAll();

    }

    public DM searchById(int id){

        return dmDao.getById(id);

    }

    public List<DM> getDMbyIdType(int idType){
        List<TypeDM> typeDMs = typeDmDao.getAll();
        List<DM> dms = new ArrayList<>();

        for (TypeDM tdm: typeDMs){
            if (tdm.getIdType()==idType){
                dms = tdm.getDms();
            }
        }

        return dms;
    }
}
