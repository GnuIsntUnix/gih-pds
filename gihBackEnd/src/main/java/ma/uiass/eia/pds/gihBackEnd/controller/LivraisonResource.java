package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.ILivraisonDao;
import ma.uiass.eia.pds.gihBackEnd.dao.LivraisonDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import ma.uiass.eia.pds.gihBackEnd.model.Livraison;

import java.util.List;


@Path("/livraison")
public class LivraisonResource {

    private final ILivraisonDao livraisonDao = new LivraisonDaoImp();

    @GET
    @Path("/getlivraisons")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livraison> getServices(){
        return livraisonDao.getAll();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLit(Livraison livraison){
        livraisonDao.create(livraison);
        return "Saved !";
    }
}
