package igor.bts;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/test")
@Produces(MediaType.TEXT_PLAIN)
public class RestMock {
	@GET
	public Response testGet(){
		return Response.ok(new String("String")).build();
	}
}
