package igor.bts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import igor.bts.entity.Bank;
import igor.bts.entity.DocType;
import igor.bts.jaxb.BankList;
import igor.bts.jaxb.DocTypeList;

import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

public class Main {
	//private static URI uriBank = UriBuilder.fromUri("http://localhost/docflow/rs/bank").port(80),
		private static com.sun.net.httpserver.HttpServer httpServer;
		private static javax.ws.rs.client.Client client;
		private static final String urlBank = "http://127.0.0.1:8282/admin/bank";
		private static final String urlDocType = "http://127.0.0.1:8282/admin/doctype";
		private static final String urlStatus = "http://127.0.0.1:8282/admin/status";
		private static final String urlTest = "http://127.0.0.1:8282/test";
		
	public static void main(String[] args) {
		URI uri = UriBuilder.fromUri("http://127.0.0.1/").port(8282).build();
		ResourceConfig resourceConfig=new ResourceConfig();
		resourceConfig.register(RestServiceMock.class);
		resourceConfig.register(RestMock.class);
		httpServer = JdkHttpServerFactory.createHttpServer(uri, resourceConfig);
		client = ClientBuilder.newClient();
		try {
			RestServiceMock service = new RestServiceMock();
			Bank bank3 = new Bank("TestBank3", "333333333", null);
			service.createBank(bank3);
			testStatus();
			//testBank();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			httpServer.stop(0);
			
		}	
		
	}
	
	public static void testStatus(){
		Response resp = client.target(urlStatus).request().get();
		System.out.println(resp.getStatus());
		System.out.println("ggg");
		
	}

	public static void testTest(){
		Response resp = client.target(urlTest).request().get();
		System.out.println(resp.readEntity(String.class));
		System.out.println(resp.getMediaType().toString());
	}
	
	public static void testBank(){
			/* *********** Bank ********** */
			/* Create */
			Bank bank1 = new Bank("TestBank1", "111111111", null);
			Bank bank2 = new Bank("TestBank2", "222222222", null);
			Bank bank3 = new Bank("TestBank3", "333333333", null);
			Response resp = client.target(urlBank)
								.request(MediaType.APPLICATION_XML).post(Entity.entity(bank1, MediaType.APPLICATION_XML));
			System.out.println(resp.getStatus());
	
			String urlBank1 = (String) resp.getMetadata().getFirst("Location");
			//assertTrue(urlBank1 != null);
					
			resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
					.post(Entity.entity(bank2,  MediaType.APPLICATION_XML));
			resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
					.post(Entity.entity(bank3,  MediaType.APPLICATION_XML));
					
			/* Get list */
			resp = client.target("http://127.0.0.1:8080/docflow/rs/admin/bank")
					.request(MediaType.APPLICATION_XML).get();
			BankList listBanks = resp.readEntity(BankList.class);
			
			/* Get bank1 */
			System.out.println(urlBank1);
			resp = client.target(urlBank1).request(MediaType.APPLICATION_XML).get();
			Bank b1 = resp.readEntity(Bank.class);
			
			/* Update b1 */
			b1.setName("NewName");
			resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
					.put(Entity.entity(b1, MediaType.APPLICATION_XML));
			b1 = resp.readEntity(Bank.class);

			/* Get list */
			resp = client.target("http://127.0.0.1:8080/docflow/rs/admin/bank")
					.request(MediaType.APPLICATION_XML).get();
			listBanks = resp.readEntity(BankList.class);
			
			
			/* Delete all entities */
			for(Bank b: listBanks.getBanks()){
				System.out.println(b.getName() + "" + b.getId());
			
				WebTarget target = client.target(urlBank).path(b.getId().toString());
				resp = target.request(MediaType.APPLICATION_XML).delete();
				// Check for correct deleting
				resp = client.target(urlBank).path(b.getId().toString())
						.request(MediaType.APPLICATION_XML).get();
			}
		}
	
	
	public static void testDocType(){
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
