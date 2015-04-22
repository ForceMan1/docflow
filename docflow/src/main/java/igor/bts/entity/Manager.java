package igor.bts.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Igor
 * Entity Type Manager. Using in the {@link igor.bts.entity.Client} entity type. 
 */
@XmlRootElement(name="manager")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@NamedQuery(name=Manager.ALL_MANAGER, query = "SELECT m from Manager m")
public class Manager implements Serializable{
	/**
	 * String const for a Named Query.
	 */
	@XmlTransient
	public static final String ALL_MANAGER = "ALL_MANAGER";
	private Integer id;
	private String fullname;
	private String smallname;
	
	/********** Constructors ********/
	public Manager(){}
	public Manager(String fullname, String smallname){
		this.fullname = fullname;
		this.smallname = smallname;
	}
	
	/********* Setters & Getters *********/
	/**
	 * Get a primary key
	 * @return primary key
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	/** 
	 * Set a primary key.
	 * @param id primary key
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/** 
	 * Get a fullname of the manager
	 * @return fullname of the manager 
	 */
	@NotNull @Size(max = 150)
	@Column(nullable = false, length = 150)
	public String getFullname() {
		return fullname;
	}
	
	/**
	 * Set a fullanme of the manager. Max size = 150.
	 * @param fullname fullname of the manager
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	/**
	 * Get a smallname of the manager.
	 * @return smallname of the manager
	 */
	@NotNull @Size(max = 100)
	@Column(nullable = false, length = 100)
	public String getSmallname() {
		return smallname;
	}
	
	/**
	 * Get a smallname of the manager. Max size = 100.
	 * @param smallname smallname of the manager
	 */
	public void setSmallname(String smallname) {
		this.smallname = smallname;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof Manager){
			Manager m = (Manager)o;
			if(m.getFullname() != fullname)
				return false;
			return true;
		}else
			return false;
	}
}
