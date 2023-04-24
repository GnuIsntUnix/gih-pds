package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.AdmissionDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IAdmissionDao;
import ma.uiass.eia.pds.gihBackEnd.model.Admission;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceAdmission;


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
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateAdmission(Admission admission, @PathParam("id") int id){
        serviceAdmission.update(admission, id);
        return "Updated !";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAdmission(@PathParam("id") int id){
        serviceAdmission.deleteAdmissionById(id);
        return "Deleted !";
    }

    private final ServiceAdmission serviceAdmission = new ServiceAdmission();
}
