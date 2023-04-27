package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import ma.uiass.eia.pds.gihBackEnd.util.HibernateUtil;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ServiceDM {
    private IDemandeDao demandeDao;
    private DmDaoImp dmDao=new DmDaoImp();
    private IServiceDao serviceDao=new ServiceDaoImp();
    private ITypeDmDao typeDmDao=new TypeDmDaoImp();
    private ExemplaireDMDaoImp exemplaireDMDaoImp=new ExemplaireDMDaoImp();
    private IStockDao stockDao=new StockDaoImp();


    public ServiceDM(){
        dmDao = new DmDaoImp();
        serviceDao = new ServiceDaoImp();
        demandeDao = new DemandeDaoImp();
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
    public void affecterExemplaire(int id,int idStock){
        Stock stock=stockDao.getById(idStock);
        ExemplaireDm exemplaireDm=exemplaireDMDaoImp.getById(id);
        exemplaireDm.setStock(stock);
        exemplaireDMDaoImp.update(exemplaireDm);
        System.out.println("i have been used");
    }
    public int number(int stockId, int dmId){
        List<ExemplaireDm> list=stockDao.getById(1).getDms();
        System.out.println(list.size());
        List<ExemplaireDm> target=new ArrayList<>();
        for(ExemplaireDm exemplaireDm:list){
            if(exemplaireDm.getDm().getId()==dmId)
                target.add(exemplaireDm);
        }
        return target.size();
    }
}
