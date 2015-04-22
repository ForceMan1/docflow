package igor.bts.rs;

import java.net.URI;
import java.util.List;

import igor.bts.entity.BZ;
import igor.bts.entity.Bank;
import igor.bts.entity.Client;
import igor.bts.entity.DocType;
import igor.bts.entity.Dogovor;
import igor.bts.entity.EdIzm;
import igor.bts.entity.Manager;
import igor.bts.entity.Service;
import igor.bts.entity.TpInternet;
import igor.bts.facade.DocumentFacade;
import igor.bts.jaxb.BZList;
import igor.bts.jaxb.BankList;
import igor.bts.jaxb.ClientList;
import igor.bts.jaxb.DocTypeList;
import igor.bts.jaxb.DogovorList;
import igor.bts.jaxb.EdIzmList;
import igor.bts.jaxb.ManagerList;
import igor.bts.jaxb.ServiceList;
import igor.bts.jaxb.TpInternetList;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;



@Path("/")
public class RestService {
	@Inject
	protected DocumentFacade facade;
	@Context
	UriInfo uriInfo;
	
	@Path("bank")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllBank(){
		List<Bank> banks = facade.getAllBank();
		if(banks == null || banks.size() ==0)
			throw new NotFoundException();
		BankList list = new BankList();
		list.setBanks(banks);
		return Response.ok(list).build();
	}
	
	@Path("manager")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllManager(){
		List<Manager> managers = facade.getAllManager();
		if(managers == null || managers.size() == 0 )
			throw new NotFoundException();
		ManagerList list = new ManagerList();
		list.setManagers(managers);
		return Response.ok(list).build();
	}
	
	@Path("edizm")
	@GET
	public Response getAllEdIzm(){
		List<EdIzm> edIzms = facade.getAllEdIzm();
		if(edIzms == null || edIzms.size() == 0)
			throw new NotFoundException();
		EdIzmList list = new EdIzmList();
		list.setEdIzms(edIzms);
 		return Response.ok(list).build();
	}
	
	@Path("doctype")
	@GET
	public Response getAllDocType(){
		List<DocType> docTypes = facade.getAllDocType();
		if(docTypes == null || docTypes.size() ==0)
			throw new NotFoundException();
		DocTypeList list = new DocTypeList();
		list.setDocTypes(docTypes);
		return Response.ok(list).build();
	}
	
	@Path("tp_internet")
	@GET
	public Response getAllTpInternet(){
		List<TpInternet> tpInternets = facade.getAllTpInternet();
		if(tpInternets == null || tpInternets.size() == 0)
			throw new NotFoundException();
		TpInternetList list = new TpInternetList();
		list.setTpInternets(tpInternets);
		return Response.ok(list).build();
	}
	
	/* Client */
	@Path("client/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getClient(@PathParam("id") String sid){
		Integer id;
		try {
			id = Integer.valueOf(sid);
		}catch(Exception e){
			throw new BadRequestException();
		}
		if(id == null)
			throw new BadRequestException();
		Client client = facade.getClient(id);
		if(client == null)
			throw new NotFoundException();
		return Response.ok(client).build();
	}
	
	@Path("client")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllClient(){
		List<Client> clients = facade.getAllClient();
		if(clients == null || clients.size() == 0)
			throw new NotFoundException();
		ClientList list = new ClientList();
		list.setClients(clients);
		return Response.ok(list).build();
	}
	
	@Path("client")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createClient(Client client){
		if(client == null)
			throw new BadRequestException();
		client = facade.createClient(client);
		if(client == null || client.getId() == null)
			throw new BadRequestException();
		URI uri = uriInfo.getAbsolutePathBuilder().path(client.getId().toString()).build();
		return Response.created(uri).build();
	}
	
	@Path("client/{id}")
	@DELETE
	public Response deleteClient(@PathParam("id") String sid){
		Integer id;
		try {
			id = Integer.valueOf(sid);
		}catch(Exception e){
			throw new BadRequestException();
		}
		if(id == null)
			throw new BadRequestException();
		Client client = facade.getClient(id);
		if(client == null || client.getId() == null)
			throw new BadRequestException();
		facade.deleteClient(client.getId());
		return Response.noContent().build();
	}
	
	@Path("client")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateClient(Client client){
		if(client == null || client.getId() == null)
			throw new BadRequestException();
		facade.updateClient(client);
		return Response.ok(client).build();
		
	}
	
	/* Dogovor */
	@Path("dogovor/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getDogovor(@PathParam("id") String sid){
		Integer id;
		try {
			id = Integer.valueOf(sid);
		}catch(Exception e) {
			throw new BadRequestException();
		}	
		Dogovor dogovor = facade.getDogovor(id);
		if(dogovor == null)
			throw new NotFoundException();
		return Response.ok(dogovor).build();
	}
	
	@Path("dogovor")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllDogovor(){
		List<Dogovor> dogovors = facade.getAllDogovor();
		if(dogovors == null || dogovors.size() == 0)
			throw new NotFoundException();
		DogovorList list = new DogovorList();
		list.setDogovors(dogovors);
		return Response.ok(list).build();
	}
	
	@Path("dogovor")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createDogovor(Dogovor dogovor){
		if(dogovor == null)
			throw new BadRequestException();
		dogovor = facade.createDogovor(dogovor);
		if(dogovor == null || dogovor.getId() == null)
			throw new BadRequestException();
		URI uri = uriInfo.getAbsolutePathBuilder().path(dogovor.getId().toString()).build();
		return Response.created(uri).build();
	}
	
	@Path("dogovor/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_XML)
	public Response deleteDogovor(@PathParam("id") String sid){
		Integer id;
		try {
			id = Integer.valueOf(sid);
		}catch(NumberFormatException exc){
			throw new BadRequestException();
		}
		Dogovor dogovor = facade.getDogovor(id);
		if(dogovor == null || dogovor.getId() == null)
			throw new BadRequestException();
		facade.deleteDogovor(dogovor.getId());
		return Response.noContent().build();
	}
	
	@Path("dogovor")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateDogovor(Dogovor dogovor){
		if(dogovor == null || dogovor.getId() == null)
			throw new BadRequestException();
		facade.updateDogovor(dogovor);
		return Response.ok(dogovor).build();
	}
		
	
	/* BZ */
	@Path("bz/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getBZ(@PathParam("id") String sid){
		Integer id;
		try {
			id = Integer.valueOf(sid);
		}catch(Exception e){
			throw new BadRequestException();
		}
		BZ bz = facade.getBZ(id);
		if(bz == null)
			throw new NotFoundException();
		return Response.ok(bz).build();
	}
	
	@Path("bz")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllBZ(){
		List<BZ> bzs = facade.getAllBZ();
		if(bzs == null || bzs.size() == 0)
			throw new NotFoundException();
		BZList list = new BZList();
		list.setBzs(bzs);
 		return Response.ok(list).build();
	}
	
	@Path("bz")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createBZ(BZ bz){
		if(bz == null)
			throw new BadRequestException();
		bz = facade.createBZ(bz);
		if(bz == null || bz.getId() == null)
			throw new BadRequestException();
	    URI uri = uriInfo.getAbsolutePathBuilder().path(bz.getId().toString()).build();
	    return Response.created(uri).build();
	}
	
	@Path("bz/{id}")
	@DELETE
	public Response deleteBZ(@PathParam("id") String sid){
		Integer id;
		try{
			id = Integer.valueOf(sid);
		}catch(NumberFormatException exc){
			throw new BadRequestException();
		}
		BZ bz = facade.getBZ(id);
		if(bz == null || bz.getId() == null )
			throw new BadRequestException();
		bz = facade.getBZ(bz.getId());
		if(bz == null)
			throw new BadRequestException();
		facade.deleteBZ(bz.getId());
		return Response.noContent().build();
	}
	
	@Path("bz")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateBZ(BZ bz){
		if(bz == null || bz.getId() == null)
			throw new BadRequestException();
		facade.updateBZ(bz);
		return Response.ok(bz).build();
	}
	
	/* Service */
	@Path("service/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getService(@PathParam("id") String sid){
		Integer id;
		try {
			id = Integer.valueOf(sid);
		}catch(Exception e){
			throw new BadRequestException();
		}
		Service service = facade.getService(id);
		if(service == null)
			throw new NotFoundException();
		return Response.ok(service).build();
	}	
	
	@Path("service")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllService(){
		List<Service> services = facade.getAllService();
		if(services == null || services.size() == 0)
			throw new NotFoundException();
		ServiceList list = new ServiceList();
		list.setServices(services);
		return Response.ok(list).build();
	}
	
	@Path("service")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createService(Service service){
		if(service == null)
			throw new BadRequestException();
		service = facade.createService(service);
		if(service == null || service.getId() == null)
			throw new BadRequestException();
		URI uri = uriInfo.getAbsolutePathBuilder().path(service.getId().toString()).build();
		return Response.created(uri).build();
	}
	
	@Path("service/{id}")
	@DELETE
	public Response deleteService(@PathParam("id") String sid){
		Integer id;
		try{
			id = Integer.valueOf(sid);
		}catch(NumberFormatException exc){
			throw new BadRequestException();
		}
		Service service = facade.getService(id);
		if(service == null ||service.getId() == null )
			throw new BadRequestException();
		facade.deleteService(service.getId());
		return Response.noContent().build();
	}
	
	@Path("service")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateService(Service service){
		if(service == null || service.getId() == null)
			throw new BadRequestException();
		facade.updateService(service);
		return Response.ok(service).build();
	}
}
