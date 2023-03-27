package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.StockDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;

import java.util.List;

@Path("/stock")
public class StockResource {
    private final Dao<Stock> stockDao = new StockDaoImp();

    @GET
    @Path("/getStock")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> getStock() {
        return stockDao.getAll();
    }

}
