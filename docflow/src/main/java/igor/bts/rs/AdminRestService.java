package igor.bts.rs;

import java.net.URI;
import java.util.List;

import igor.bts.entity.Bank;
import igor.bts.entity.EdIzm;
import igor.bts.entity.Manager;
import igor.bts.entity.TpInternet;
import igor.bts.facade.VocFacade;
import igor.bts.jaxb.BankList;
import igor.bts.jaxb.DocTypeList;
import igor.bts.jaxb.EdIzmList;
import igor.bts.jaxb.ManagerList;
import igor.bts.jaxb.TpInternetList;
import igor.bts.entity.DocType;

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

@Path("/admin")
public class AdminRestService {
	@Inject
	protected VocFacade facade;
	
	@Context
	private UriInfo uriInfo;
	
	// Bank
	@Path("bank/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getBank(@PathParam("id") Integer id){
		Bank bank = facade.getBank(id);
		if(bank == null)
			throw new NotFoundException();
		return Response.ok(bank).build();
	}
	
	
	@Path("bank")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllBank(){
		List<Bank> banks = facade.getAllBank();
		if(banks == null || banks.size() == 0)
			throw new NotFoundException();
		BankList listBanks = new BankList();
		listBanks.setBanks(banks);
		return Response.ok(listBanks).build();
	}
	
	@Path("bank")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createBank(Bank bank){
		if(bank == null)
			throw new BadRequestException();
		bank = facade.createBank(bank);
		if(bank.getId() == null || bank.getId() == 0)
			throw new BadRequestException();
		URI bankUri = uriInfo.getAbsolutePathBuilder().path(bank.getId().toString()).build();
		return Response.created(bankUri).build();
	}
	
	@Path("bank/{id}")
	@DELETE
	public Response deleteBank(@PathParam("id") Integer id){
		if(id == null || id == 0)
			throw new BadRequestException();
		Bank bank = facade.getBank(id);
		if(bank == null)
			throw new NotFoundException();
		facade.deleteBank(bank.getId());
		return Response.noContent().build();
	}
	
	@Path("bank")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateBank(Bank bank){
		if(bank == null)
			throw new NotFoundException();
		facade.updateBank(bank);
		return Response.ok(bank).build();
	}
	
	// DocType
	@Path("doctype/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getDocType(@PathParam("id") Integer id){
		DocType docType = facade.getDocType(id);
		if(docType == null)
			throw new NotFoundException();
		return Response.ok(docType).build();
	}
	
	
	@Path("doctype")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllDocType(){
		DocTypeList docTypes = new DocTypeList();
		docTypes.setDocType(facade.getAllDocType());
		if(docTypes == null || docTypes.getDocTypes().size() == 0)
			throw new NotFoundException();
		return Response.ok(docTypes).build();
	}
	
	@Path("doctype")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createDocType(DocType docType){
		if(docType == null)
			throw new BadRequestException();
		docType = facade.createDocType(docType);
		if(docType.getId() == null || docType.getId() == 0)
			throw new BadRequestException();
		URI docTypeUri = uriInfo.getAbsolutePathBuilder().path(docType.getId().toString())
				.build();
		return Response.created(docTypeUri).build();
	}
	
	@Path("doctype/{id}")
	@DELETE
	public Response deleteDocType(@PathParam("id") Integer id){
		if(id == null || id == 0)
			throw new BadRequestException();
		DocType docType = facade.getDocType(id);
		if(docType == null)
			throw new NotFoundException();
		facade.deleteDocType(docType.getId());
		return Response.noContent().build();
	}
	
	@Path("doctype")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateDocType(DocType docType){
		if(docType == null)
			throw new NotFoundException();
		facade.updateDocType(docType);
		return Response.ok(docType).build();
	}

	// EdIzm
	@Path("edizm/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getEdIzm(@PathParam("id") Integer id){
		EdIzm edIzm = facade.getEdIzm(id);
		if(edIzm == null)
			throw new NotFoundException();
		return Response.ok(edIzm).build();
	}
	
	
	@Path("edizm")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllEdIzm(){
		List<EdIzm> edIzms = facade.getAllEdIzm();
		if(edIzms == null || edIzms.size() == 0)
			throw new NotFoundException();
		EdIzmList list = new EdIzmList();
		list.setEdIzms(edIzms);
		return Response.ok(list).build();
	}
	
	@Path("edizm")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createEdIzm(EdIzm edIzm){
		if(edIzm == null)
			throw new BadRequestException();
		edIzm = facade.createEdIzm(edIzm);
		if(edIzm.getId() == null || edIzm.getId() == 0)
			throw new BadRequestException();
		URI edIzmUri = uriInfo.getAbsolutePathBuilder().path(edIzm.getId().toString()).build();
		return Response.created(edIzmUri).build();
	}
	
	@Path("edizm/{id}")
	@DELETE
	public Response deleteEdIzm(@PathParam("id") Integer id){
		if(id == null || id == 0)
			throw new BadRequestException();
		EdIzm edIzm = facade.getEdIzm(id);
		if(edIzm == null)
			throw new NotFoundException();
		facade.deleteEdIzm(edIzm.getId());
		return Response.noContent().build();
	}
	
	@Path("edizm")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateEdIzm(EdIzm edIzm){
		if(edIzm == null)
			throw new NotFoundException();
		facade.updateEdIzm(edIzm);
		return Response.ok(edIzm).build();
	}
	
	// Manager
	@Path("manager/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getManager(@PathParam("id") Integer id){
		Manager manager = facade.getManager(id);
		if(manager == null)
			throw new NotFoundException();
		return Response.ok(manager).build();
	}
	
	
	@Path("manager")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllManager(){
		List<Manager> managers = facade.getAllManager();
		if(managers == null || managers.size() == 0)
			throw new NotFoundException();
		ManagerList list = new ManagerList();
		list.setManagers(managers);
		return Response.ok(list).build();
	}
	
	@Path("manager")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createManager(Manager manager){
		if(manager == null)
			throw new BadRequestException();
		manager = facade.createManager(manager);
		if(manager.getId() == null || manager.getId() == 0)
			throw new BadRequestException();
		URI managerUri = uriInfo.getAbsolutePathBuilder().path(manager.getId().toString()).build();
		return Response.created(managerUri).build();
	}
	
	@Path("manager/{id}")
	@DELETE
	public Response deleteManager(@PathParam("id") Integer id){
		if(id == null || id == 0)
			throw new BadRequestException();
		Manager manager = facade.getManager(id);
		if(manager == null)
			throw new NotFoundException();
		facade.deleteManager(manager.getId());
		return Response.noContent().build();
	}
	
	@Path("manager")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateManager(Manager manager){
		if(manager == null)
			throw new NotFoundException();
		facade.updateManager(manager);
		return Response.ok(manager).build();
	}
	
	// TpInternet
	@Path("tp_internet/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getTpInternet(@PathParam("id") Integer id){
		TpInternet tpInternet = facade.getTpInternet(id);
		if(tpInternet == null)
			throw new NotFoundException();
		return Response.ok(tpInternet).build();
	}
	
	
	@Path("tp_internet")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllTpInternet(){
		List<TpInternet> tpInternets = facade.getAllTpInternet();
		if(tpInternets == null || tpInternets.size() == 0)
			throw new NotFoundException();
		TpInternetList list = new TpInternetList();
		list.setTpInternets(tpInternets);
		return Response.ok(list).build();
	}
	
	@Path("tp_internet")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createTpInternet(TpInternet tpInternet){
		if(tpInternet == null)
			throw new BadRequestException();
		tpInternet = facade.createTpInternet(tpInternet);
		if(tpInternet.getId() == null || tpInternet.getId() == 0)
			throw new BadRequestException();
		URI tpInternetUri = uriInfo.getAbsolutePathBuilder().path(tpInternet.getId().toString()).build();
		return Response.created(tpInternetUri).build();
	}
	
	@Path("tp_internet/{id}")
	@DELETE
	public Response deleteTpInternet(@PathParam("id") Integer id){
		if(id == null || id == 0)
			throw new BadRequestException();
		TpInternet tpInternet = facade.getTpInternet(id);
		if(tpInternet == null)
			throw new NotFoundException();
		facade.deleteTpInternet(tpInternet.getId());
		return Response.noContent().build();
	}
	
	@Path("tp_internet")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateTpInternet(TpInternet tpInternet){
		if(tpInternet == null)
			throw new NotFoundException();
		facade.updateTpInternet(tpInternet);
		return Response.ok(tpInternet).build();
	}
	
}

