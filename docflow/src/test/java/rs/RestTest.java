package rs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import igor.bts.Podpisant;
import igor.bts.entity.Bank;
import igor.bts.entity.Client;
import igor.bts.entity.Manager;
import igor.bts.jaxb.ClientList;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RestTest extends JerseyTest{
	private static ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	private static Validator validator;
	private static final String urlBank = "http://127.0.0.1:8080/docflow/rs/admin/bank"; 
	private static final String urlManager = "http://127.0.0.1:8080/docflow/rs/admin/manager";
	private static final String urlDocType = "http://127.0.0.1:8080/docflow/rs/admin/doctype";
	private static final String urlEdIzm = "http://127.0.0.1:8080/docflow/rs/admin/edizm";
	private static final String urlTpInternet = "http://127.0.0.1:8080/docflow/rs/admin/tp_internet";
	private static final String urlClient = "http://127.0.0.1:8080/docflow/rs/client";
	private static final String urlDogovor = "http://127.0.0.1:8080/docflow/rs/dogovor";
	private static final String urlBZ = "http://127.0.0.1:8080/docflow/rs/bz";
	private static final String urlService = "http://127.0.0.1:8080/docflow/rs/service";
	
	@Override
	protected Application configure(){
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		ResourceConfig config = new ResourceConfig();
		config.register(igor.bts.rs.AdminRestService.class);
		config.register(igor.bts.rs.RestService.class);
		return config;
	}
	
	@BeforeClass
	public static void createValidator(){
		validator = vf.getValidator();
		
	}
	
	@AfterClass
	public static void closeValidator(){
		vf.close();
	}
	
	//@Test
	public void testClient(){
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio11", "dolzhnost11", "", "", null, "");
			
		//Create manager
		Response resp = target(urlManager).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(manager, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		String url = (String)resp.getMetadata().getFirst("Location");
		resp = target(url).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		manager = resp.readEntity(Manager.class);
		resp = target(urlManager).path(manager.getId().toString()).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		manager = resp.readEntity(Manager.class);
		assertTrue(manager.getId() != null);
		assertTrue(manager != null && manager.getId() != null);
		//Create Bank
		resp = target(urlBank).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bank, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		url = (String)resp.getMetadata().getFirst("Location");
		resp = target(url).request(MediaType.APPLICATION_XML).get();
		bank = resp.readEntity(Bank.class);
		assertTrue(bank!= null && bank.getId() != null);
		resp = target(urlBank).path(bank.getId().toString())
				.request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		bank = resp.readEntity(Bank.class);
		assertTrue(bank.getId() != null);
		
		assertEquals(validator.validate(manager).size(), 0);
		assertEquals(validator.validate(bank).size(), 0);
		assertEquals(validator.validate(podpisant).size(), 0);
		//Create Client
		Client cl = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		assertEquals(validator.validate(cl).size(), 0);
		resp = target(urlClient).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(cl, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Update client
		cl.setFullname("Жуков");
		resp = target(urlClient).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(cl, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		cl = resp.readEntity(Client.class);
		assertTrue(cl.getFullname().equals("Жуков"));
		
		// Get List
		resp = target(urlClient).request(MediaType.APPLICATION_XML)
				.get();
		assertEquals(resp.getStatus(), 200);
		ClientList list = resp.readEntity(ClientList.class);
		assertEquals(list.getClients().size(), 1);

		// Delete clients
		for(Client c : list.getClients()){
			resp = target(urlClient).path(c.getId().toString()).request().delete();
			assertEquals(resp.getStatus(), 204);
			resp = target(urlClient).path(c.getId().toString()).request().get();
			assertEquals(resp.getStatus(), 404);
		}
		
		//Delete Alls
		resp = target(urlManager).path(manager.getId().toString()).request().delete();
		resp = target(urlBank).path(bank.getId().toString()).request().delete();
	}
}
