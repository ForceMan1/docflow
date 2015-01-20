package igor.bts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
@Access(AccessType.PROPERTY)
@XmlRootElement(name = "podpisant")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Podpisant implements Serializable{
	private String fio;
	private String dolzhnost;
	private String pass_seria;
	private String pass_nomer;
	private Date pass_date;
	private String pass_vydan;
	
	/************* Constructors **************/
	public Podpisant(){}
	public Podpisant(String fio, String dolzhnost, String pass_seria, 
			String pass_nomer, Date pass_date, String pass_vydan){
		this.fio = fio;
		this.dolzhnost = dolzhnost;
		this.pass_seria = pass_seria;
		this.pass_nomer = pass_nomer;
		this.pass_date = pass_date;
		this.pass_vydan = pass_vydan;
	}
	
	/****** Setters & Getters ***************/
	@Basic(optional = false)
	@Column(length = 120, nullable = false)
	@XmlAttribute
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	
	@Column(length = 255)
	@XmlAttribute
	public String getDolzhnost() {
		return dolzhnost;
	}
	public void setDolzhnost(String dolzhnost) {
		this.dolzhnost = dolzhnost;
	}
	
	@Column(length = 4)
	@XmlAttribute
	public String getPass_seria() {
		return pass_seria;
	}
	public void setPass_seria(String pass_seria) {
		this.pass_seria = pass_seria;
	}
	
	@Column(length = 6)
	@XmlAttribute
	public String getPass_nomer() {
		return pass_nomer;
	}
	public void setPass_nomer(String pass_nomer) {
		this.pass_nomer = pass_nomer;
	}
	
	@Temporal(TemporalType.DATE)
	@XmlAttribute
	public Date getPass_date() {
		return pass_date;
	}
	public void setPass_date(Date pass_date) {
		this.pass_date = pass_date;
	}
	
	@Column(length = 255)
	@XmlAttribute
	public String getPass_vydan() {
		return pass_vydan;
	}
	public void setPass_vydan(String pass_vydan) {
		this.pass_vydan = pass_vydan;
	}
}
