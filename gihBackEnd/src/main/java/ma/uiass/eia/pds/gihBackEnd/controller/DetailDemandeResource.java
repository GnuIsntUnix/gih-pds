package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.DemandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.DetailDemandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IDemandeDao;
import ma.uiass.eia.pds.gihBackEnd.dao.IDetailDemandeDao;
import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.DetailDemandeDm;

import java.util.List;

@Path("/detaildemandedm")
public class DetailDemandeResource {
    private final IDetailDemandeDao detailDemandeDao = new DetailDemandeDaoImp();


    @GET
    @Path("/getdemandes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DetailDemandeDm> getDetailDemandes() {
        return detailDemandeDao.getAll();
    }


    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createDetailDM(DetailDemandeDm dt){
        detailDemandeDao.create(dt);
        return "Saved !";
    }

}
