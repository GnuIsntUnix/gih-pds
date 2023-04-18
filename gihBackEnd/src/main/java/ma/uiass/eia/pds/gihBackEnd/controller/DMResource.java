package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceDM;

import java.util.List;

@Path("/dm")
public class DMResource {

    private final ServiceDM serviceDM = new ServiceDM();

    @GET
    @Path("/getdms")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DM> getDMs(){ return serviceDM.getAll();}

    @GET
    @Path("/getdms/service/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ExemplaireDm> getServices(@PathParam("id") int id){
        return serviceDM.getDMsByService(id);
    }


    @GET
    @Path("getdms/bytype/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DM> getDMByType(@PathParam("id") int id){return serviceDM.getDMbyIdType(id);}
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLit(DM dm){
        serviceDM.add(dm);
        return "Saved !";
    }
    @POST
    @Path("/traiterdemande")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateStock(@PathParam("id") int id){
        serviceDM.affecterDM(id);
    }
}
