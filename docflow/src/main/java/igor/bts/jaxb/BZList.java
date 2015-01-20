package igor.bts.jaxb;

import igor.bts.entity.BZ;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(BZ.class)
public class BZList {
	private List<BZ> bzs;
	
	@XmlElement(name = "bz")
	public List<BZ> getBzs(){
		return bzs;
	}
	
	public void setBzs(List<BZ> bzs){
		this.bzs = bzs;
	}
}
