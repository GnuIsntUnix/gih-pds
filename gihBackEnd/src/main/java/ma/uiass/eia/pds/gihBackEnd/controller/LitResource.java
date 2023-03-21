package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceLits;

import java.util.List;

@Path("/lit")
public class LitResource {
    private final ServiceLits serviceLits = new ServiceLits();


    @GET
    @Path("/getlits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lit> getLits() {
        return serviceLits.getAll();
    }

    @GET
    @Path("/getlits/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lit getLitById(@PathParam("id") int id) {return serviceLits.searchById(id);}

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLit(Lit lit){
        serviceLits.add(lit);
        return "Saved !";
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateLit(Lit lit){return "Updated !";}

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteLit(@PathParam("id") int id){
        serviceLits.deleteById(id);
        return "Deleted !";
    }


}
