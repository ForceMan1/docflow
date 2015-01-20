package rs;

import static org.junit.Assert.*;
import igor.bts.RestServiceMock;
import igor.bts.entity.Bank;
import igor.bts.entity.DocType;
import igor.bts.jaxb.BankList;
import igor.bts.jaxb.DocTypeList;
import igor.bts.rs.ApplicationConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.net.httpserver.*;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;













import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
//import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

public class RestServiceIT {
	//private static URI uriBank = UriBuilder.fromUri("http://localhost/docflow/rs/bank").port(80),
	private static com.sun.net.httpserver.HttpServer httpServer;
	private static javax.ws.rs.client.Client client;
	private static final String urlBank = "http://127.0.0.1:8282/admin/bank";
	private static final String urlDocType = "http://127.0.0.1:8282/admin/doctype";
		
	@BeforeClass
	public static void createHttpServer(){
		URI uri = UriBuilder.fromUri("http://127.0.0.1/").port(8282).build();
		ResourceConfig resourceConfig=new ResourceConfig(RestServiceMock.class);
		httpServer = JdkHttpServerFactory.createHttpServer(uri, resourceConfig);
		
		//httpServer.start();
		//
		//resourceConfig.register(igor.bts.rs.ApplicationConfig)
		//httpServer =JdkHttpServerFactory.createHttpServer(uri, resourceConfig);
		//httpServer = GrizzlyHttpServerFactory.createHttpServer(uri, new MyApplicationConfig());
		client = ClientBuilder.newClient();
		//httpServer.start();
	}
	
	@AfterClass
	public static void stopHttpServer(){
		httpServer.stop(0);
	}
	
	@Test
	public void testBank(){
			/* *********** Bank ********** */
			/* Create */
					Bank bank1 = new Bank("TestBank1", "111111111", null);
			Bank bank2 = new Bank("TestBank2", "222222222", null);
			Bank bank3 = new Bank("TestBank3", "333333333", null);
			Response resp = client.target(urlBank)
								.request(MediaType.APPLICATION_XML).post(Entity.entity(bank1, MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
	
			String urlBank1 = (String) resp.getMetadata().getFirst("Location");
			assertTrue(urlBank1 != null);
					
			resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
					.post(Entity.entity(bank2,  MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
			resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
					.post(Entity.entity(bank3,  MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
					
			/* Get list */
			resp = client.target("http://127.0.0.1:8080/docflow/rs/admin/bank")
					.request(MediaType.APPLICATION_XML).get();
			assertEquals(resp.getStatus(), 200);
			BankList listBanks = resp.readEntity(BankList.class);
			
			/* Get bank1 */
			resp = client.target(urlBank1).request(MediaType.APPLICATION_XML).get();
			assertEquals(resp.getStatus(), 200);
			Bank b1 = resp.readEntity(Bank.class);
			assertTrue(b1 != null);
			assertTrue(b1.equals(bank1));
			
			/* Update b1 */
			b1.setName("NewName");
			resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
					.put(Entity.entity(b1, MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 200);
			b1 = resp.readEntity(Bank.class);
			assertTrue(b1.getName().equals("NewName"));

			/* Get list */
			resp = client.target("http://127.0.0.1:8080/docflow/rs/admin/bank")
					.request(MediaType.APPLICATION_XML).get();
			assertEquals(resp.getStatus(), 200);
			listBanks = resp.readEntity(BankList.class);
			
			
			/* Delete all entities */
			for(Bank b: listBanks.getBanks()){
				System.out.println(b.getName() + "" + b.getId());
			
				WebTarget target = client.target(urlBank).path(b.getId().toString());
				resp = target.request(MediaType.APPLICATION_XML).delete();
				assertEquals(resp.getStatus(), 204);
				// Check for correct deleting
				resp = client.target(urlBank).path(b.getId().toString())
						.request(MediaType.APPLICATION_XML).get();
				assertEquals(resp.getStatus(), 404);
				assertTrue(b != null);
			}
		}
	
	//@Test
	public void testDocType(){
			DocType docType1 = new DocType("name1", "info", 0, (short)0);
			DocType docType2 = new DocType("name2", "info", 0, (short)0);
			DocType docType3 = new DocType("name3", "info", 0, (short)0);
			
			Response resp = client.target(urlDocType).request(MediaType.APPLICATION_XML_TYPE)
					.post(Entity.entity(docType1, MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
			String urlDocType1 = (String)resp.getMetadata().getFirst("Location");
			resp = client.target(urlDocType1).request(MediaType.APPLICATION_XML)
					.get();
			assertEquals(resp.getStatus(), 200);
			
			resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
					.post(Entity.entity(docType2, MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
			
			resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
					.post(Entity.entity(docType3, MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
			
			DocType docType = resp.readEntity(DocType.class);
			
			// Update
			docType1.setName("newName");
			resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
					.put(Entity.entity(docType1, MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 200);
			docType1 = resp.readEntity(DocType.class);
			assertEquals(docType1.getName(), "newName");
			
			// Get list DocTypes
			resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
					.get();
			assertEquals(resp.getStatus(), 200);
			DocTypeList dts = resp.readEntity(DocTypeList.class); 
			assertTrue(dts != null);
					
			for(DocType dt : dts.getDocTypes()){
				System.out.println(dt.getName() + " " + dt.getId());
				resp = client.target(urlDocType).path(dt.getId().toString()).request().delete();
				assertEquals(resp.getStatus(), 204);
				resp = client.target(urlDocType).path(dt.getId().toString()).request().get();
				assertEquals(resp.getStatus(), 404);
			}
		}	
			
			
}