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

import java.util.List;

@Path("/commande")
public class CommandeResource {

    private final ICommandeDao commandeDao = new CommandeDaoImp();

    @GET
    @Path("/getcommandes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getCommandes() {
        return commandeDao.getAll();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLit(Commande c){
        commandeDao.create(c);
        return "Saved !";
    }
}
