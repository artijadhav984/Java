package scheduler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/RootService")
public class Root {

	public Root() {
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		return "Hello Arti !";
	}
	
//	@POST
//	@Consumes("text/plain")
//	@Produces("text/plain")
//	public String post(String s) {
//		return "Success";
//	}
}
