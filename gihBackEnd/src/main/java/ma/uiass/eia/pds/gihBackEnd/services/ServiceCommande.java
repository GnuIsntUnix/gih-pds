package ma.uiass.eia.pds.gihBackEnd.services;

import jakarta.ws.rs.PathParam;
import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceCommande {
    private ICommandeDao commandeDao;
    private ILitDao litDao;

    private IServiceDao serviceDao;

    public ServiceCommande(){
        commandeDao = new CommandeDaoImp();
        serviceDao = new ServiceDaoImp();
    }

    public void add(Commande commande){
        commandeDao.create(commande);
    }

    public void validerCommande(Commande commande){
        List<Lit> lits = litDao.getLitbyNumber(commande.getQuantite(), commande.getTypeLit());
        Service service = commande.getService();
    }
    public void save(Commande commande){
        commandeDao.create(commande);
    }
    public void merge(Commande c){
        commandeDao.update(c);
    }
    public List<Commande> getAllCommandes(){
        return commandeDao.getAll();
    }
    public List<Commande> getCommandesbyService(int id){
        List<Commande> commandes = commandeDao.getAll();
        List<Commande> byService = new ArrayList<>();
        commandes.forEach(commande -> {
            if (commande.getService().getIdService() == id)
                byService.add(commande);
        });
        return byService;
    }
}
