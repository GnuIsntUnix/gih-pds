package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.CommandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.DemandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.ICommandeDao;
import ma.uiass.eia.pds.gihBackEnd.dao.IDemandeDao;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;

import java.util.List;
@Path("/demande")
public class DemandeResource {

    private final IDemandeDao demandeDao = new DemandeDaoImp();


    @GET
    @Path("/getdemandes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DemandeDm> getDemandes() {
        return demandeDao.getAll();
    }


    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createDM(DemandeDm d){
        demandeDao.create(d);
        return "Saved !";
    }
}
