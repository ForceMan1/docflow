package igor.bts.jaxb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import igor.bts.entity.Bank;
import igor.bts.entity.TpInternet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/*
@XmlRootElement
@XmlSeeAlso(TpInternet.class)
public class TpInternetList {
	private List<TpInternet> tpInternets;
	
	@XmlElement(name = "tpInternet")
	public List<TpInternet> getTpInternets(){
		return tpInternets;
	}
	
	public void setTpInternets(List<TpInternet> tpInternets){
		this.tpInternets = tpInternets;
	}
}
*/

@XmlRootElement
@XmlSeeAlso(TpInternet.class)
public class TpInternetList extends ArrayList<TpInternet> {
	public TpInternetList() {
		super();
	}

	public TpInternetList(Collection<? extends TpInternet> c) {
		super(c);
	}
	@XmlElement(name = "tpInternet")
	public List<TpInternet> getTpInternets() {
		return this;
	}

	public void setTpInternets(List<TpInternet> tpInternets) {
		this.addAll(tpInternets);
	}
}

