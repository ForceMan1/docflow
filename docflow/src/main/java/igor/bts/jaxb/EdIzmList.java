package igor.bts.jaxb;



import igor.bts.entity.EdIzm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(EdIzm.class)
public class EdIzmList {
	private List<EdIzm> edIzms;
	
	@XmlElement(name = "edizm")
	public List<EdIzm> getEdIzms(){
		return edIzms;
	}
	
	public void setEdIzms(List<EdIzm> edIzms){
		this.edIzms = edIzms;
	}

}


/*
@XmlRootElement
@XmlSeeAlso(EdIzm.class)
public class EdIzmList extends ArrayList<EdIzm> {
	public EdIzmList() {
		super();
	}

	public EdIzmList(Collection<? extends EdIzm> c) {
		super(c);
	}
	@XmlElement(name = "edizm")
	public List<EdIzm> getEdIzms() {
		return this;
	}

	public void setEdIzms(List<EdIzm> edIzms) {
		this.addAll(edIzms);
	}
}
*/