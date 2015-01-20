package igor.bts.jaxb;

import java.util.List;

import igor.bts.entity.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Client.class)
public class ClientList {
	private List<Client> clients = null;
	
	@XmlElement(name = "client")
	public List<Client> getClients(){
		return clients;
	}
	
	public void setClients(List<Client> clients){
		this.clients = clients;
	}
}
