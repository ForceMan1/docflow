package igor.bts.jaxb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import igor.bts.entity.Bank;
import igor.bts.entity.DocType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/*
@XmlRootElement
@XmlSeeAlso(DocType.class)
public class DocTypeList {
	private List<DocType> docTypes;
	
	@XmlElement(name = "doctype")
	public List<DocType> getDocTypes(){
		return docTypes;
	}
	
	public void setDocTypes(List<DocType> docTypes){
		this.docTypes = docTypes;
	}
}
*/


@XmlRootElement(name = "doctypes")
@XmlSeeAlso(DocType.class)
public class DocTypeList extends ArrayList<DocType> {
	public DocTypeList() {
		super();
	}

	public DocTypeList(Collection<? extends DocType> c) {
		super(c);
	}
	@XmlElement(name = "doctype")
	public List<DocType> getDocTypes() {
		return this;
	}

	public void setDocTypes(List<DocType> docTypes) {
		this.addAll(docTypes);
	}
}
