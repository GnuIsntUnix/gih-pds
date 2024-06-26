package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.ExemplaireDMDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IExemplaireDMDao;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.ExemplaireDm;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceExemplaireDM;

import java.util.List;

@Path("/exemplairedm")
public class ExemplaireDMResource {

    private final ServiceExemplaireDM serviceExemplaireDM = new ServiceExemplaireDM();

    @GET
    @Path("/getexemplaires/{idDm}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ExemplaireDm> getExemplairesbyIdDm(@PathParam("idDm") int idDm){
        return serviceExemplaireDM.getByDm(idDm);
    }
    @GET
    @Path("/getexbystockdm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ExemplaireDm> getExemplairesbyStockAndDm(@PathParam("id") int id){
        return serviceExemplaireDM.getBySockAndDm(id);
    }
}
