package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.CommandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.EspaceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.ICommandeDao;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.Espace;

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
}
