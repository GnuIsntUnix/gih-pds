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

    public List<DM> getDMsByService(int idService){
        Service s = serviceDao.getById(idService);
        List<DM> dms = s.getStock().getDms();
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
        List<DM> dms = dmDao.getAll();
        List<DM> byType = new ArrayList<>();
        dms.forEach(dm -> {
            if(dm.getTypeDM().getIdType() == idType)
                byType.add(dm);
        });
        return byType;
    }
    public List<DM> getDmByStock(int idStock){
        List<DM> list = new ArrayList<>();
        for (DM dm : dmDao.getAll()){
            if (dm.getStock().getIdEspace()==idStock)
                list.add(dm);
        }
        return list;
    }
    public void affecterExemplaire(int id,int idStock){
        Stock stock=stockDao.getById(idStock);
        ExemplaireDm exemplaireDm=exemplaireDMDaoImp.getById(id);
        exemplaireDm.setStock(stock);
        exemplaireDMDaoImp.update(exemplaireDm);
        System.out.println("exec");
    }

    public int number(int stockId, int dmId){
        DMwithExemplaire dm= (DMwithExemplaire) dmDao.getById(dmId);
        List<ExemplaireDm> list=dm.getExemplaireDmList();
        System.out.println(list.size());
        return list.size();
    }
    public void affecterDm(int id,int idStock){
        Stock stock=stockDao.getById(idStock);
        DMwithQuantity dm= (DMwithQuantity) dmDao.getById(id);
        ((DMwithQuantity)dm).setStock(stock);
        dmDao.update(dm);
        System.out.println("Affectation de DM complete xdd");
    }
    public void up(DM dm){
        dmDao.update(dm);
    }
}
