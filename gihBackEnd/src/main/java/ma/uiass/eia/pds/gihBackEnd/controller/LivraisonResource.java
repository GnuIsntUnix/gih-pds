package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.ILivraisonDao;
import ma.uiass.eia.pds.gihBackEnd.dao.LivraisonDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.Livraison;


@Path("/livraison")
public class LivraisonResource {

    private final ILivraisonDao livraisonDao = new LivraisonDaoImp();

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLit(Livraison livraison){
        livraisonDao.create(livraison);
        return "Saved !";
    }
}
