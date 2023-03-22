package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.EspaceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Espace;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;

import java.util.List;

@Path("/espace")
public class EspaceResource {
    private final Dao<Espace> espaceDao = new EspaceDaoImp();

    @GET
    @Path("/getespaces")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Espace> getEspaces() {
        return espaceDao.getAll();
    }
}
