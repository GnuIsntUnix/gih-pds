package ma.uiass.eia.pds.gihBackEnd;


import jakarta.ws.rs.core.UriBuilder;
import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import ma.uiass.eia.pds.gihBackEnd.prediction.DistributionStationnaire;
import ma.uiass.eia.pds.gihBackEnd.prediction.PredictionAmbulance;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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
        Dao<State> stateDao = new StateDaoImp();
        Dao<Revision> revisionDao = new RevisionDaoImp();


//        stateDao.create(new F());
//        stateDao.create(new NFCD());
//        stateDao.create(new NFLD());
//
//        fournisseurDao.create(new Fournisseur("F1","F1@gmail.com","Rabat","0661789534"));
////
////
////
////            //------------------------  Services   -----------------------------
//        serviceDao.create(new Service("LOGI", "Logistique"));
//        serviceDao.create(new Service("DERMATO", "Dermatologie"));
//        serviceDao.create(new Service("RADIO", "Radiologie"));
//        serviceDao.create(new Service("CHR", "Chirurgie"));
//        serviceDao.create(new Service("NEURO", "Neurologie"));
//
//        marqueDao.create(new Marque("MarqueTest"));
//        batimentDao.create(new Batiment("Batiment A", serviceDao.getById(1)));
//        batimentDao.create(new Batiment("Batiment B", serviceDao.getById(2)));
//        batimentDao.create(new Batiment("Batiment C", serviceDao.getById(2)));
//        batimentDao.create(new Batiment("Batiment D", serviceDao.getById(3)));
//        batimentDao.create(new Batiment("Batiment E", serviceDao.getById(4)));
//        batimentDao.create(new Batiment("Batiment F", serviceDao.getById(4)));
//        batimentDao.create(new Batiment("Batiment G", serviceDao.getById(5)));
//        batimentDao.create(new Batiment("Batiment H", serviceDao.getById(5)));
//        batimentDao.create(new Batiment("Batiment I", serviceDao.getById(1)));
//        batimentDao.create(new Batiment("Batiment J", serviceDao.getById(3)));
//        batimentDao.create(new Batiment("Batiment K", serviceDao.getById(3)));
//        batimentDao.create(new Batiment("Batiment L", serviceDao.getById(4)));
//        batimentDao.create(new Batiment("Batiment M", serviceDao.getById(5)));
//
//
//        typeLitDao.create(new TypeLit("Electrique"));
//
//        espaceDao.create(new Chambre(1, batimentDao.getById(1), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(10, batimentDao.getById(2), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(5, batimentDao.getById(3), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(2, batimentDao.getById(4), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(9, batimentDao.getById(5), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(6, batimentDao.getById(6), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(3, batimentDao.getById(7), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(4, batimentDao.getById(8), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(7, batimentDao.getById(9), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(8, batimentDao.getById(10), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(11, batimentDao.getById(11), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(12, batimentDao.getById(12), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(13, batimentDao.getById(13), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(14, batimentDao.getById(1), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(15, batimentDao.getById(2), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(16, batimentDao.getById(3), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(17, batimentDao.getById(4), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(18, batimentDao.getById(5), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(19, batimentDao.getById(6), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(20, batimentDao.getById(7), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(21, batimentDao.getById(8), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(22, batimentDao.getById(9), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(23, batimentDao.getById(10), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(24, batimentDao.getById(11), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(25, batimentDao.getById(12), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(26, batimentDao.getById(13), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(27, batimentDao.getById(1), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(28, batimentDao.getById(2), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(29, batimentDao.getById(3), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(30, batimentDao.getById(4), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(31, batimentDao.getById(5), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(32, batimentDao.getById(6), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(33, batimentDao.getById(7), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(34, batimentDao.getById(8), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(35, batimentDao.getById(9), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(36, batimentDao.getById(10), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(37, batimentDao.getById(11), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(38, batimentDao.getById(12), 2, TypeChambre.S));
//        espaceDao.create(new Chambre(39, batimentDao.getById(13), 2, TypeChambre.S));
//
//        typeLitDao.create(new TypeLit("Mecanique"));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(1)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(2)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(3)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(4)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(5)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(6)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(7)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(8)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(9)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(10)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(11)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(12)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(13)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(14)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(15)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(16)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(17)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(18)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(19)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(20)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(21)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(22)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(23)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(24)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(25)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(26)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(27)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(28)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(29)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(30)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(31)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(32)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(33)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(34)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(35)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(36)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(37)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(38)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(39)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(1)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(2)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(3)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(4)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(5)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(6)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(7)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(8)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(9)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(10)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(11)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(12)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(13)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(14)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(15)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(16)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(17)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(18)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(19)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(20)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(21)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(22)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(23)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(24)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(25)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(26)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(27)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(28)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(29)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(30)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(31)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(32)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(33)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(34)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(35)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(36)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(37)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(38)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(39)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(1)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(2)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(3)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(4)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(5)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(6)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(7)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(8)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(9)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(10)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(11)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(12)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(13)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(14)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(15)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(16)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(17)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(18)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(19)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(20)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(21)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(22)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(23)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(24)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(25)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(26)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(27)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(1), espaceDao.getById(28)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(29)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(30)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(31)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(32)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(33)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(34)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(35)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(36)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(37)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(38)));
//        litDao.create(new Lit(EtatLit.O, DisponibiliteLit.Di, marqueDao.getById(1), typeLitDao.getById(2), espaceDao.getById(39)));
//
//
//
//
//
//        //------------------------- TypeDm ----------------------------------
//       typeDMDao.create(new TypeDM("Fourniture"));
//       typeDMDao.create(new TypeDM("Instrument Leger"));
//      typeDMDao.create(new TypeDM("Outil de Diagnostique"));
//     typeDMDao.create(new TypeDM("Mobilier"));
//       typeDMDao.create(new TypeDM("Equipement Leger"));
//       typeDMDao.create(new TypeDM("Equipement Lourd"));
//        typeDMDao.create(new TypeDM("dm connectés"));
//        typeDMDao.create(new TypeDM("dm lourd"));
//        //-------------------------- Dm -------------------------------------
//    dmDao.create(new DMwithQuantity(40,"BND","Bande", typeDMDao.getById(1),stockDao.getById(1)));
//    dmDao.create(new DMwithQuantity(40,"PN", "Pensement", typeDMDao.getById(2),stockDao.getById(1)));
//    dmDao.create(new DMwithQuantity(40,"SY", "Seringue", typeDMDao.getById(2),stockDao.getById(1)));
//    DM dm = new DMwithExemplaire("SC", "Scanner", typeDMDao.getById(6),stockDao.getById(1));
//    dmDao.create(dm);
//    DM dm2 = new DMwithExemplaire("AA", "Appareil d'anesthésie", typeDMDao.getById(8),stockDao.getById(1));
//    dmDao.create(dm2);
















//        ---------------------------------------------------------------------
//        System.out.println(detailDemandeDmDao.getAll().size());
//        Stock stock=stockDao.getById(1);
//        List<ExemplaireDm> list=new ArrayList<>();
//        for (ExemplaireDm exemplaireDm: exemplaireDmDao.getAll()){
//            if(exemplaireDm.getId()==1)
//                list.add(exemplaireDm);
//        }
//        System.out.println(list);
//
//        exemplaireDm.setStock(stock);
//        exemplaireDmDao.update(exemplaireDm);
//        ---------------------------------------------------------------------
//        System.out.println(demandeDmDao.getAll().get(0).getDetailDemandeDms().get(0).getDm().getDetailDemandeDm());
//        State f = new F();
//        State nfc = new NFCD();
//        stateDao.create(f);
//        stateDao.create(nfc);
//        Revision rev = new Revision(LocalDate.now(), null, f, nfc);
//        revisionDao.create(rev);
//        f.setRevision(rev);
//        nfc.setRevision(rev);
//        stateDao.update(f);
//        stateDao.update(nfc);


            //Tests predictions
//        Ambulance a1 = new Ambulance("m1", LocalDate.parse("2023-03-29"), "100", TypeAmbulance.I);
//        double x = ChronoUnit.DAYS.between(a1.getDateMiseEnCirculation(), LocalDate.now());
//        System.out.println(x);
//        double [][] mat = PredictionAmbulance.getMat(x, 0.7);
////        double [][] mat = new double[][] {{0.95, 0.04, 0.01}, {0.9 , 0.1 , 0   }, {0.6 , 0   , 0.4}};
////        double[] m = DistributionStationnaire.getMat(new double[]{1, 0, 0}, mat);
//        double m01 = (PredictionAmbulance.getM(mat, 0, 1));
////        System.out.println(Arrays.toString(m));
//        System.out.println(Math.round(m01));
        //System.out.println(batimentDao.getById(serviceDao.getById(2).getBatiments().get(0).getIdBatiment()).getEspaces());
    }

}
