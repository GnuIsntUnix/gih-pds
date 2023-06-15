package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.StockDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceStock;

import java.util.List;

@Path("/stock")
public class StockResource {
    private final ServiceStock stockDao = new ServiceStock();

    @GET
    @Path("/getstock")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> getStock() {
        return stockDao.getAll();
    }

    @GET
    @Path("/getstock/byservice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Stock getStockbyService(@PathParam("id") int id){
        return stockDao.getStockByService(id);
    }

    @GET
    @Path("/getstockLog")
    @Produces(MediaType.APPLICATION_JSON)
    public Stock getStockLog() {
        return stockDao.getStockLog();
    }
}
