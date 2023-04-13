package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.DmDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceDM;

import java.util.List;

@Path("/dm")
public class DMResource {

    private final ServiceDM serviceDM = new ServiceDM();

    @GET
    @Path("/getdms/service/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DM> getServices(@PathParam("id") int id){
        return serviceDM.getDMsByService(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLit(DM dm){
        serviceDM.add(dm);
        return "Saved !";
    }
}
