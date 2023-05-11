package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.model.Historique;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceHistorique;

import java.util.List;

@Path("/historique")
public class HistoriqueResource {

    private final ServiceHistorique serviceHistorique = new ServiceHistorique();

    @Path("/gethistoriquesforambulance/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Historique> getForAmbulance(@PathParam("id") int id){
        return serviceHistorique.getForAmbulance(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLit(Historique historique){
        serviceHistorique.add(historique);
        return "Saved !";
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateLit(Historique historique){
        serviceHistorique.update(historique);
        return "Updated !";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteLit(@PathParam("id") int id){
        serviceHistorique.deleteById(id);
        return "Deleted !";
    }

}
