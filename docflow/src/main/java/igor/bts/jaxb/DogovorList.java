package igor.bts.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import igor.bts.entity.*;

@XmlRootElement
@XmlSeeAlso(Dogovor.class)
public class DogovorList {
	private List<Dogovor> dogovors;
	
	@XmlElement(name = "dogovor")
	public List<Dogovor> getDogovors(){
		return dogovors;
	}
	
	public void setDogovors(List<Dogovor> dogovors){
		this.dogovors = dogovors;
	}
}
