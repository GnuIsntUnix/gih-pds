package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.CommandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.EspaceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.ICommandeDao;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.Espace;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceCommande;

import java.util.List;

@Path("/commande")
public class CommandeResource {

    private final ServiceCommande commande = new ServiceCommande();

    @GET
    @Path("/getcommandes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getCommandes() {
        return commande.getAllCommandes();
    }

    @GET
    @Path("/getcommandes/byservice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getCommandesbyService(@PathParam("id") int id){
        return commande.getCommandesbyService(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLit(Commande c){
        commande.save(c);
        return "Saved !";
    }

    @POST
    @Path("/merge")
    @Consumes(MediaType.APPLICATION_JSON)
    public void mergeCommande(Commande c){
        commande.merge(c);
    }
}
