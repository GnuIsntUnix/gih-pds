package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.AdmissionDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.CommandeDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IAdmissionDao;
import ma.uiass.eia.pds.gihBackEnd.dao.ICommandeDao;
import ma.uiass.eia.pds.gihBackEnd.model.Admission;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;

import java.util.List;

@Path("/admission")
public class AdmissionResource {
    private final IAdmissionDao admissionDao = new AdmissionDaoImp();

    @GET
    @Path("/getadmissions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Admission> getadmissions() {
        return admissionDao.getAll();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createAdmission(Admission a){
        admissionDao.create(a);
        return "Saved !";
    }

}
