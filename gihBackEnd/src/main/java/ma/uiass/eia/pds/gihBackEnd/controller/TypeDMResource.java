package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.TypeDmDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.TypeDM;

import java.util.List;

@Path("/typedm")
public class TypeDMResource {

    private final Dao<TypeDM> typeDMDao = new TypeDmDaoImp();

    @GET
    @Path("/gettypes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TypeDM> getTypesDM(){
        return typeDMDao.getAll();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveType(TypeDM typeDM){
        typeDMDao.create(typeDM);
    }
}
