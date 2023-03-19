package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.MarqueDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.TypeLitDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Marque;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;

import java.util.List;


@Path("/typeslits")
public class TypeLitResource {

    private final Dao<TypeLit> typeLitDao = new TypeLitDaoImp();

    @GET
    @Path("/gettypeslits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TypeLit> getMarques(){
        return typeLitDao.getAll();
    }
}
