package ma.uiass.eia.pds.gihBackEnd.services;
import ma.uiass.eia.pds.gihBackEnd.dao.IStockDao;
import ma.uiass.eia.pds.gihBackEnd.dao.StockDaoImp;

public class ServiceStock {
    private IStockDao stockDao;
    public ServiceStock(){

        StockDaoImp stockDaoImp = new StockDaoImp();
    }
}
