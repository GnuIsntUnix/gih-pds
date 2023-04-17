package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.BatimentDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IBatimentDao;
import ma.uiass.eia.pds.gihBackEnd.model.Batiment;

import java.util.List;

@Path("/batiment")
public class BatimentResource {
    private final IBatimentDao batimentDao = new BatimentDaoImp();

    @GET
    @Path("/getbatiments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Batiment> getbatiments() {
        return batimentDao.getAll();
    }


}
