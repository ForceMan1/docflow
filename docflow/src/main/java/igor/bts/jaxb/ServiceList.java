package igor.bts.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import igor.bts.entity.Service;

@XmlRootElement
@XmlSeeAlso(Service.class)
public class ServiceList {
	private List<Service> services = null;
	
	@XmlElement(name = "service")
	public List<Service> getServices(){
		return services;
	}
	
	public void setServices(List<Service> services){
		this.services = services;
	}
}
