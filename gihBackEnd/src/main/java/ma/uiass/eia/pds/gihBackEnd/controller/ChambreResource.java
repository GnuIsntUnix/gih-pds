package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.ChambreDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.LitDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Chambre;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;

import java.util.List;

@Path("/chambre")
public class ChambreResource {
    private final Dao<Chambre> chambreDao = new ChambreDaoImp();
    private final LitDaoImp litDao = new LitDaoImp();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lits/{id}")
    public List<Lit> getLits(@PathParam("id") int id) {
        return litDao.getAllByChambre(id);
    }
}
