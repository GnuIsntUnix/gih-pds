package ma.uiass.eia.pds.gihBackEnd.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.uiass.eia.pds.gihBackEnd.dao.StateDaoImp;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import ma.uiass.eia.pds.gihBackEnd.model.State;
@Path("/state")
public class StateResource {
    StateDaoImp stateDaoImp = new StateDaoImp();
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateState(State state){
        stateDaoImp.update(state);
        return "Updated !";
    }
}
