package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.BatimentDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IBatimentDao;
import ma.uiass.eia.pds.gihBackEnd.model.Batiment;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceBatiment;

import java.util.List;

@Path("/batiment")
public class BatimentResource {
    private final ServiceBatiment batimentDao = new ServiceBatiment();

    @GET
    @Path("/getbatiments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Batiment> getbatiments() {
        return batimentDao.getAll();
    }

    @GET
    @Path("/getbatiments/byservice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Batiment> getbatimentsbyService(@PathParam("id") int id) {
        return batimentDao.getByService(id);
    }


}
