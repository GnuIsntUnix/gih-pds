package ma.uiass.eia.pds.gihBackEnd.controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.Dao;
import ma.uiass.eia.pds.gihBackEnd.dao.ServiceDaoImp;
import ma.uiass.eia.pds.gihBackEnd.dao.UtilisateurDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.Utilisateur;

import java.util.List;

@Path("/utilisateur")
public class UtilisateurResource {
    private final UtilisateurDaoImp utilisateurDao = new UtilisateurDaoImp();


    @GET
    @Path("/{user}/{pass}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur getUser(@PathParam("user") String user, @PathParam("pass") String mdp){
        return utilisateurDao.verifierUser(user, mdp);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addUser(Utilisateur utilisateur) {
        utilisateurDao.create(utilisateur);
        return "Success";
    }

}
