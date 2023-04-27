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


//               fournisseurDao.create(new Fournisseur("F1"," "," "," "));
//
//
//
//            //------------------------  Services   -----------------------------
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
//
//        //------------------------- TypeDm ----------------------------------
//       typeDMDao.create(new TypeDM("Fourniture"));
//       typeDMDao.create(new TypeDM("Instrument Leger"));
//      typeDMDao.create(new TypeDM("Outil de Diagnostique"));
//      typeDMDao.create(new TypeDM("Mobilier"));
//       typeDMDao.create(new TypeDM("Equipement Leger"));
//        //-------------------------- Dm -------------------------------------
//     dmDao.create(new DM("BND","Bande", typeDMDao.getById(1)));
//        dmDao.create(new DM("PN", "Pensement", typeDMDao.getById(2)));
//

//----------------------------------Hadchi dyal hamza kan kaytester bih yamkan liktmas7o ila bghiti-------------------------------------------

       // System.out.println(demandeDmDao.getById(4).getDetailDemandeDms().get(0).getDm().getExemplaireDmList());
     //       List<DetailDemandeDm> list = new ArrayList<>();
//            DemandeDm demandeDm = new DemandeDm(serviceDao.getById(2), LocalDate.of(2023, 1, 29));
//            DetailDemandeDm detailDemandeDm = new DetailDemandeDm(dmDao.getById(6), 3, demandeDmDao.getById(6));
////            list.add(detailDemandeDm);
////            demandeDm.setDetailDemandeDms(list);
////            demandeDmDao.create(demandeDm);
////            detailDemandeDmDao.create(detailDemandeDm);
//        demandeDmDao.getById(6).getDetailDemandeDms().add(detailDemandeDm);
//        for(int i=1;i<=5;i++){
//            ExemplaireDm exemplaireDm=new ExemplaireDm(demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm(),stockDao.getById(1));
//            exemplaireDm.setId(demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm().getId());
//            exemplaireDmDao.create(exemplaireDm);
//            stockDao.getById(1).getDms().add(exemplaireDm);
//        }
//        ExemplaireDm exemplaireDm=demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm().getExemplaireDmList().get(0);
//        demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm().getExemplaireDmList().add(exemplaireDm);
//        stockDao.getById(1).setDms(new ArrayList<>());
//        stockDao.update(stockDao.getById(1));

//        Stock stock= (Stock) espaceDao.getById(1);
//        List<ExemplaireDm> list=stock.getDms();
//
//        System.out.println(list.size());
//        stock.setDms(stock.getDms().stream()
//                .distinct()
//                .collect(Collectors.toList()));
//        stockDao.update(stock);
//        espaceDao.update(stock);
//        System.out.println(stock.getDms());
        //ServiceDM serviceDM=new ServiceDM();
        //System.out.println(serviceDM.number(1,demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm().getId()));
//        System.out.println(demandeDmDao.getById(3).getDetailDemandeDms().get(0).getQte());
//        if(serviceDM.number(1,demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm().getId())>=demandeDmDao.getById(3).getDetailDemandeDms().get(0).getQte()) {
//            System.out.println("yes");
//        }
        //System.out.println(demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm().getExemplaireDmList());
//        dmDao.update(demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm());
//        System.out.println(demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm().getExemplaireDmList().size());
        //System.out.println(stockDao.getById(1).getDms().size());

//        demandeDmDao.getById(5).getDetailDemandeDms().get(0).getDm().getExemplaireDmList().add(exemplaireDmDao.getById(1));
//        System.out.println(demandeDmDao.getById(6).getDetailDemandeDms().get(0).getDm().getExemplaireDmList());
        //System.out.println(serviceDao.getById(1).getStock().getIdEspace());
        //dmDao.getById(3).getExemplaireDmList().get(0).getStock().setIdEspace(1);

        //System.out.println(demandeDmDao.getById(6).getDetailDemandeDms().get(0).getDm().getExemplaireDmList().get(0).getStock().getIdEspace());
        //for(ExemplaireDm exemplaireDm:demandeDmDao.getById(6).getDetailDemandeDms().get(0).getDm().getExemplaireDmList()) {
          //  exemplaireDmDao.update(exemplaireDm, demandeDmDao.getById(6).getService().getStock().getIdEspace()+1);
        //}
//        System.out.println(demandeDmDao.getById(6).getDetailDemandeDms().get(0).getDm().getExemplaireDmList().get(0).getStock().getIdEspace());
//        serviceDao.update(serviceDao.getById(1),1);
//        stockDao.update(stockDao.getById(1),1);
        //System.out.println(serviceDao.getById(1).getStock());
        //System.out.println(demandeDmDao.getById(3).getDetailDemandeDms().get(0).getDm().getExemplaireDmList());

    }

}
