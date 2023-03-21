package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.MarqueDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Marque;

import java.util.List;

@Path("/marque")
public class MarqueResource {
    private final Dao<Marque> marqueDao = new MarqueDaoImp();

    @GET
    @Path("/getmarques")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Marque> getMarques(){
        return marqueDao.getAll();
    }
}
