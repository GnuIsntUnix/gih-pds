package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
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

    @GET
    @Path("/getlits/byservice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lit> getLitsByService(@PathParam("id") int id) {return serviceLits.getLitsByService(id);}

    @GET
    @Path("/getlits/bybatiment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lit> getLitsByBatiment(@PathParam("id") int id) {return serviceLits.getLitsByBatiment(id);}

    @GET
    @Path("/getlits/byespace/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lit> getLitsByEspace(@PathParam("id") int id) {return serviceLits.getLitsByEspace(id);}


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
    public String updateLit(Lit lit){
        serviceLits.update(lit);
        return "Updated !";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteLit(@PathParam("id") int id){
        serviceLits.deleteById(id);
        return "Deleted !";
    }

    @POST
    @Path("/traitercommande")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateStock(@PathParam("id") int id){
        serviceLits.affecter(id);
    }
    @GET
    @Path("/getlits/stock/{idService}/bytype/{idType}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lit> getLitsInStockByType(@PathParam("idService") int idService,
                                          @PathParam("idType") int idType
                                          ){
        return serviceLits.getLitsByTypeInStock(idService, idType);
    }
    @Path("/changeEtatLit/{id}")
    public String updateEtatLit(@PathParam("id") int id){
        serviceLits.switchEtat(id);
        return "done";
    }
    @GET
    @Path("/getlitsparstock/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lit> getLitsInStock(@PathParam("id") int id){
        return serviceLits.getLitsInStock(id);
    }
    @POST
    @Path("/merge")
    @Consumes(MediaType.APPLICATION_JSON)
    public void mergeLit(Lit lit){
        serviceLits.mergeL(lit);
    }
}
