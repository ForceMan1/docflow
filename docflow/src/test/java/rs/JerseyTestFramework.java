package rs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import igor.bts.RestServiceMock;
import igor.bts.entity.Bank;
import igor.bts.jaxb.BankList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class JerseyTestFramework extends JerseyTest{
	@Override
	protected Application configure(){
		return new ResourceConfig(RestServiceMock.class);
	}
	private static final String urlBank = "/admin/bank";
	private static final String urlDocType = "/admin/doctype";
	
	@Test
	public void testBank(){
			/* *********** Bank ********** */
			/* Create */
			Bank bank1 = new Bank("TestBank1", "111111111", null);
			Bank bank2 = new Bank("TestBank2", "222222222", null);
			Bank bank3 = new Bank("TestBank3", "333333333", null);
			Response resp = target(urlBank)
								.request(MediaType.APPLICATION_XML).post(Entity.entity(bank1, MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
	
			String urlBank1 = (String) resp.getMetadata().getFirst("Location");
			assertTrue(urlBank1 != null);
					
			resp = target(urlBank).request(MediaType.APPLICATION_XML)
					.post(Entity.entity(bank2,  MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
			resp = target(urlBank).request(MediaType.APPLICATION_XML)
					.post(Entity.entity(bank3,  MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 201);
					
			/* Get list */
			resp = target("/admin/bank")
					.request(MediaType.APPLICATION_XML).get();
			assertEquals(resp.getStatus(), 200);
			BankList listBanks = resp.readEntity(BankList.class);
			
			/* Get bank1 */
			resp = target(urlBank1).request(MediaType.APPLICATION_XML).get();
			assertEquals(resp.getStatus(), 200);
			Bank b1 = resp.readEntity(Bank.class);
			assertTrue(b1 != null);
			assertTrue(b1.equals(bank1));
			
			/* Update b1 */
			b1.setName("NewName");
			resp = target(urlBank).request(MediaType.APPLICATION_XML)
					.put(Entity.entity(b1, MediaType.APPLICATION_XML));
			assertEquals(resp.getStatus(), 200);
			b1 = resp.readEntity(Bank.class);
			assertTrue(b1.getName().equals("NewName"));

			/* Get list */
			resp = target("/admin/bank")
					.request(MediaType.APPLICATION_XML).get();
			assertEquals(resp.getStatus(), 200);
			listBanks = resp.readEntity(BankList.class);
			
			
			/* Delete all entities */
			for(Bank b: listBanks.getBanks()){
				System.out.println(b.getName() + "" + b.getId());
			
				WebTarget target = target(urlBank).path(b.getId().toString());
				resp = target.request(MediaType.APPLICATION_XML).delete();
				assertEquals(resp.getStatus(), 204);
				// Check for correct deleting
				resp = target(urlBank).path(b.getId().toString())
						.request(MediaType.APPLICATION_XML).get();
				assertEquals(resp.getStatus(), 404);
				assertTrue(b != null);
			}
		} 
}
