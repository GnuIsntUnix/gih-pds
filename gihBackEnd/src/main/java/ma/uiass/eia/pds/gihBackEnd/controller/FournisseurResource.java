package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.FournisseurDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.IFournisseurDao;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
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
}


