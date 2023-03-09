package ma.uiass.eia.pds.gihBackEnd;


import jakarta.ws.rs.core.UriBuilder;
import ma.uiass.eia.pds.gihBackEnd.dao.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;


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
//        Dao<Service> serviceDao = new ServiceDaoImp();
//        Dao<Chambre> chambreDao = new ChambreDaoImp();
//        Dao<Marque> marqueDao = new MarqueDaoImp();
//        Dao<TypeLit> typeLitDao = new TypeLitDaoImp();
//        Dao<Batiment> batimentDao = new BatimentDaoImp();
//        Dao<Lit> litDao = new LitDaoImp();
//        serviceDao.create(new Service("CARDIO", "Cardiologie"));
//        batimentDao.create(new Batiment("Batiment A", serviceDao.getById(1)));
//        chambreDao.create(new Chambre(10, batimentDao.getById(1), "single", 2));
//        marqueDao.create(new Marque("Hamid"));
//        typeLitDao.create(new TypeLit("Urgence"));
//        litDao.create(new Lit(EtatLit.D, marqueDao.getById(1), typeLitDao.getById(1), chambreDao.getById(1)));

    }
}
