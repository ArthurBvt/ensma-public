package fr.ensma.a3.ia.mymultichat.rest.services.list;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.ensma.a3.ia.mymultichat.business.canalchat.GestionCanaux;

/**
 * ChatServ
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Path("")
public class ListServ {

    @GET
    @Path("list")
    public Response getCanalsList() {
    	GestionCanaux gestion = new GestionCanaux();
    	return Response.ok(gestion.getAll()).build();
    }

}
