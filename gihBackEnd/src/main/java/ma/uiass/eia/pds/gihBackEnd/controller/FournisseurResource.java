package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.FournisseurDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IFournisseurDao;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.TypeDM;

import java.util.List;

@Path("/fournisseur")
public class FournisseurResource {
    private IFournisseurDao fournisseurDao = new FournisseurDaoImp();

    @GET
    @Path("/getfournisseurs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fournisseur> getServices(){
        return fournisseurDao.getAll();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveFournisseur(Fournisseur fournisseur){
        fournisseurDao.create(fournisseur);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateFournisseur(Fournisseur fournisseur){
        fournisseurDao.update(fournisseur);
        return "Updated !";
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteFournisseur(@PathParam("id") int id){
        fournisseurDao.delete(id);
        return "Deleted !";
    }
}


