package ma.uiass.eia.pds.gihBackEnd;


import jakarta.ws.rs.core.UriBuilder;
import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceDM;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Hello world!
 *
 */


public class App 
{
    public static void main( String[] args ) throws IOException {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig().packages("ma.uiass.eia.pds.gihBackEnd","com.fasterxml.jackson.jaxrs.json.provider").register(JacksonFeature.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        //------------------------------------------------------------------------------------------------------------//
        Dao<Service> serviceDao = new ServiceDaoImp();
        Dao<Espace> espaceDao = new EspaceDaoImp();
        Dao<Marque> marqueDao = new MarqueDaoImp();
        Dao<TypeLit> typeLitDao = new TypeLitDaoImp();
        Dao<Batiment> batimentDao = new BatimentDaoImp();
        Dao<ExemplaireDm> exemplaireDmDao=new ExemplaireDMDaoImp();
        Dao<Lit> litDao = new LitDaoImp();
        UtilisateurDaoImp utilisateurDaoImp = new UtilisateurDaoImp();
        Dao<Stock> stockDao = new StockDaoImp();
        Dao<DM> dmDao = new DmDaoImp();
        Dao<DemandeDm> demandeDmDao= new DemandeDaoImp();
        Dao<DetailDemandeDm> detailDemandeDmDao=new DetailDemandeDaoImp();
        Dao<TypeDM> typeDMDao = new TypeDmDaoImp();
        Dao<Livraison> livraisonDao = new LivraisonDaoImp();
        Dao<DetailLivraison> detailLivraisonDao = new DetailLivraisonDaoImp();
        Dao<Fournisseur> fournisseurDao = new FournisseurDaoImp();
        Dao<Ambulance> ambulanceDao = new AmbulanceDaoImp();


//        fournisseurDao.create(new Fournisseur("F1","F1@gmail.com","Rabat","0661789534"));
//
//
//
//            //------------------------  Services   -----------------------------
//        serviceDao.create(new Service("LOGI", "Logistique"));
//        serviceDao.create(new Service("DERMATO", "Dermatologie"));
//        serviceDao.create(new Service("RADIO", "Radiologie"));
//        serviceDao.create(new Service("CHR", "Chirurgie"));
//        serviceDao.create(new Service("NEURO", "Neurologie"));
////
//        marqueDao.create(new Marque("MarqueTest"));
//        batimentDao.create(new Batiment("Batiment A", serviceDao.getById(1)));
//        batimentDao.create(new Batiment("Batiment B", serviceDao.getById(2)));
//        batimentDao.create(new Batiment("Batiment C", serviceDao.getById(2)));
//        batimentDao.create(new Batiment("Batiment D", serviceDao.getById(3)));
//        typeLitDao.create(new TypeLit("Electrique"));
//        espaceDao.create(new Chambre(1, batimentDao.getById(1), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(10, batimentDao.getById(2), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(5, batimentDao.getById(3), 2, TypeChambre.S));
//
//        typeLitDao.create(new TypeLit("Mecanique"));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(1)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(1)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(6)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(6)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(6)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(6)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.O, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(7)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.O, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(8)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.O, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(8)));
//
//

//        //------------------------- TypeDm ----------------------------------
//       typeDMDao.create(new TypeDM("Fourniture"));
//       typeDMDao.create(new TypeDM("Instrument Leger"));
//      typeDMDao.create(new TypeDM("Outil de Diagnostique"));
//      typeDMDao.create(new TypeDM("Mobilier"));
//       typeDMDao.create(new TypeDM("Equipement Leger"));
//        //-------------------------- Dm -------------------------------------
//     dmDao.create(new DM("BND","Bande", typeDMDao.getById(1)));
//     dmDao.create(new DM("PN", "Pensement", typeDMDao.getById(2)));

        //---------------------------------------------------------------------
//        System.out.println(detailDemandeDmDao.getAll().size());
//        Stock stock=stockDao.getById(1);
//        List<ExemplaireDm> list=new ArrayList<>();
//        for (ExemplaireDm exemplaireDm: exemplaireDmDao.getAll()){
//            if(exemplaireDm.getId()==1)
//                list.add(exemplaireDm);
//        }
//        System.out.println(list);

//        exemplaireDm.setStock(stock);
//        exemplaireDmDao.update(exemplaireDm);
        //---------------------------------------------------------------------
        //System.out.println(demandeDmDao.getAll().get(0).getDetailDemandeDms().get(0).getDm().getDetailDemandeDm());


    }

}
