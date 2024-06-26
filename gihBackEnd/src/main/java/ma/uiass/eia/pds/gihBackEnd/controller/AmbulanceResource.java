package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.AmbulanceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceAmbulance;

import java.util.List;

@Path("/ambulance")
public class AmbulanceResource {
    private final AmbulanceDaoImp ambulanceDaoImp = new AmbulanceDaoImp();
    private final ServiceAmbulance serviceAmbulance = new ServiceAmbulance();
    @GET
    @Path("/getambulances")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ambulance> getAllAmbulances(){return ambulanceDaoImp.getAll();}

    @POST
    @Path("/ambulancecreation")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createAmbulance(Ambulance ambulance){
        serviceAmbulance.add(ambulance);
        return "created";
    }
    @PUT
    @Path("/merge")
    @Consumes(MediaType.APPLICATION_JSON)
    public void mergeAmbulance(Ambulance ambulance){
        ambulanceDaoImp.update(ambulance);
    }
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDemande(@PathParam("id") int id){
        serviceAmbulance.deleteById(id);
        return "Deleted !";
    }
    @GET
    @Path("/getambulances/byrevision/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ambulance getByService(@PathParam("id") int id){
        return ambulanceDaoImp.getByRevision(id);
    }

    @GET
    @Path("/getambulances/bystate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ambulance> getByState(@PathParam("id") int id){
        return ambulanceDaoImp.getByState(id);
    }
}
