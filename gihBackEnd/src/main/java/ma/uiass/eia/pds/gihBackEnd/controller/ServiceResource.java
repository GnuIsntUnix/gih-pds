package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Service;

import java.util.List;

@Path("/service")
public class ServiceResource {
    private final Dao<Service> serviceDaoImp = new ServiceDaoImp();


    @GET
    @Path("/getservices")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Service> getServices(){
        return serviceDaoImp.getAll();
    }

    @GET
    @Path("/getservices/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Service getService(@PathParam("id") int id) {
        return serviceDaoImp.getById(id);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createService(Service service) {
        serviceDaoImp.create(service);
        return "Success";
    }

}
