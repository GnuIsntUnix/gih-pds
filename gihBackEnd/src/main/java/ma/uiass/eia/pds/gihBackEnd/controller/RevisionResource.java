package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceRevision;

import java.util.List;

@Path("/revision")
public class RevisionResource {

    private final ServiceRevision serviceRevision = new ServiceRevision();

    @Path("/getrevisionsonambulance/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Revision> getOnAmbulance(@PathParam("id") int id){
        return serviceRevision.getOnAmbulance(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String saveRevision(Revision revision){
        serviceRevision.add(revision);
        return "Saved !";
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateRevision(Revision revision){
        serviceRevision.update(revision);
        return "Updated !";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteRevision(@PathParam("id") int id){
        serviceRevision.deleteById(id);
        return "Deleted !";
    }
}
