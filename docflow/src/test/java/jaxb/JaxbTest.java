package jaxb;

import java.io.StringWriter;

import igor.bts.Podpisant;
import igor.bts.entity.Bank;
import igor.bts.entity.Client;
import igor.bts.entity.Manager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

public class JaxbTest {
	@Test
	public void testMarshallingClient() throws JAXBException{
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio11", "dolzhnost11", "", "", null, "");
		//Create Client
		Client cl = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
			true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
			false, "", "", manager, bank, null, null, null);
		
		
		JAXBContext context = JAXBContext.newInstance(igor.bts.entity.Client.class);
		StringWriter writer = new StringWriter();
		Marshaller m = context.createMarshaller();
		m.marshal(cl, writer);
		System.out.println(writer);
	}
}
