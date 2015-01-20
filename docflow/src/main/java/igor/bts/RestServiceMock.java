package igor.bts;

import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import igor.bts.dao.BankDAO;
import igor.bts.dao.DocTypeDAO;
import igor.bts.dao.EdIzmDAO;
import igor.bts.dao.ManagerDAO;
import igor.bts.dao.TpInternetDAO;
import igor.bts.entity.Bank;
import igor.bts.facade.DocumentFacade;
import igor.bts.facade.VocFacade;
import igor.bts.rs.AdminRestService;
import igor.bts.rs.RestService;

@Path("/admin")
public class RestServiceMock extends AdminRestService {
	private class VocFacadeMock extends VocFacade {
		private final EntityManagerFactory emf = 
					Persistence.createEntityManagerFactory("test1");
		public VocFacadeMock(){
			
			EntityManager em = null;
			try {
				em = emf.createEntityManager();
			}catch(IllegalStateException e){
				System.out.println("IllegalException");
			}
			BankDAO bankDAO = new BankDAO();
			bankDAO.setEm(em);
			this.setBankDAO(bankDAO);
			
			DocTypeDAO docTypeDAO = new DocTypeDAO();
			docTypeDAO.setEm(em);
			this.setDocTypeDAO(docTypeDAO);
			
			EdIzmDAO edIzmDAO = new EdIzmDAO();
			edIzmDAO.setEm(em);
			this.setEdIzmDAO(edIzmDAO);
			
			ManagerDAO managerDAO = new ManagerDAO();
			managerDAO.setEm(em);
			this.setManagerDAO(managerDAO);
			
			TpInternetDAO tpInternetDAO = new TpInternetDAO();
			tpInternetDAO.setEm(em);
			this.setTpInternetDAO(tpInternetDAO);
		}
		
		
	}
	public RestServiceMock(){
		this.facade = new VocFacadeMock();
	}
	
	@Override
	@Path("bank")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createBank(Bank bank){
		if(bank == null)
			throw new BadRequestException();
		bank = facade.createBank(bank);
		if(bank.getId() == null || bank.getId() == 0)
			throw new BadRequestException();
		URI bankUri = null;
		bankUri = UriBuilder.fromUri("http://127.0.0.1/").port(8282).path("/admin/bank/")
					.path(bank.getId().toString()).build();
		return Response.created(bankUri).build();
	}
	
	@Path("status")
	@GET
	public Response getStatus(){
		return Response.ok("Privet").build();
	}
}
