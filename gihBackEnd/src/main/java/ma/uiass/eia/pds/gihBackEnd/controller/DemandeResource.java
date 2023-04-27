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
import ma.uiass.eia.pds.gihBackEnd.services.ServiceDemandeDM;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceLits;

import java.util.List;
@Path("/demande")
public class DemandeResource {

    private final IDemandeDao demandeDao = new DemandeDaoImp();
    private final ServiceDemandeDM serviceDemandeDM = new ServiceDemandeDM();


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

    @GET
    @Path("/getdemandes/byservice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DemandeDm> getDemandesByService(@PathParam("id") int id) {return serviceDemandeDM.getDemandeDMByService(id);}
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDemande(@PathParam("id") int id){
        serviceDemandeDM.deleteById(id);
        return "Deleted !";
    }

    @POST
    @Path("/traiter")
    @Consumes(MediaType.APPLICATION_JSON)
    public String traiterDemande(DemandeDm demandeDm){
        serviceDemandeDM.traiter(demandeDm);
        return "Traité";
    }
}

