package ma.uiass.eia.pds.gihBackEnd.services;

import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;

import java.util.List;

public class ServiceCommande {
    private ICommandeDao commandeDao;
    private ILitDao litDao;

    private IServiceDao serviceDao;

    public ServiceCommande(){
        commandeDao = new CommandeDaoImp();
    }

    public void add(Commande commande){
        commandeDao.create(commande);
    }

    public void validerCommande(Commande commande){
        List<Lit> lits = litDao.getLitbyNumber(commande.getQuantite(), commande.getTypeLit());
        Service service = commande.getService();
        service.setLitsNonAffectes(lits);
    }
}
