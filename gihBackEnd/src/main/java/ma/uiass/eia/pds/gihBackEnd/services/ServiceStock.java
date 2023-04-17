package ma.uiass.eia.pds.gihBackEnd.services;
import ma.uiass.eia.pds.gihBackEnd.dao.IStockDao;
import ma.uiass.eia.pds.gihBackEnd.dao.StockDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;

import java.util.List;

public class ServiceStock {
    private IStockDao stockDao;
    public ServiceStock(){

        stockDao = new StockDaoImp();
    }

    public List<Stock> getAll(){
        return stockDao.getAll();
    }

    public Stock getStockByService(int id){
        Stock stock = null;
        List<Stock> stocks = stockDao.getAll();
        for (Stock stock1 : stocks) {
            if (stock1.getService().getIdService() == id){
                stock = stock1;
            }
        }
        return stock;
    }
}
