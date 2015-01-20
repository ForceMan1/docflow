package igor.bts.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="ed_izm")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "ed_izm")
@NamedQuery(name=EdIzm.ALL_ED_IZM, query = "SELECT e from EdIzm e")
public class EdIzm implements Serializable{
	@XmlTransient
	public static final String ALL_ED_IZM = "ALL_ED_IZM";
	private Integer id;
	private String name;
	private String info;
	
	/***** Constructors ***************/
	public EdIzm(){}
	public EdIzm(String name, String info){
		this.name = name;
		this.info = info;
	}
	
	/****** Getters & Setters **********/
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Size(max = 128)
	@Column(length = 128)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof EdIzm){
			EdIzm edizm = (EdIzm)o;
			if(edizm.getName() != name)
				return false;
			return true;
		}else
			return false;
	}
}
