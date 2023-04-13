package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
    public List<TypeDM> getServices(){
        return typeDMDao.getAll();
    }
}
