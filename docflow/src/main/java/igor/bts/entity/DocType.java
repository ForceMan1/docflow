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

/**
 * @author Igor
 * Entity Type DocType
 */
@XmlRootElement(name="doc_type")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "doc_type")
@NamedQuery(name=DocType.ALL_TYPE, query = "SELECT d from DocType d")
public class DocType implements Serializable{
	/**
	 * String const of a Named Query
	 */
	@XmlTransient
	public static final String ALL_TYPE = "ALL_TYPE";
	private Integer id;
	private String name;
	private String info;
	private Integer parent;
	private Short weight;
	
	/****** Constructors ***********/
	public DocType(){}
	public DocType(String name, String info, Integer parent, Short weight){
		this.name = name;
		this.info = info;
		this.parent = parent;
		this.weight = weight;
	}
	
	/***** Setters & Getters *********/
	/**
	 * Get primary key.
	 * @return primary key
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set primary key.
	 * @param id primary key
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get a name of document's type
	 * @return name of document's type
	 */
	@NotNull @Size(max = 255)
	@Column(nullable = false, length = 255)
	public String getName() {
		return name;
	}
	
	/**
	 * Set a name of document's type. Not nullable. Max Size = 255.
	 * @param name name of document's type
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get additional info
	 * @return additional info
	 */
	@Size(max = 255)
	@Column(length = 255)
	public String getInfo() {
		return info;
	}
	
	/**
	 * Set additional info. Max size = 255.
	 * @param info additional info
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	/** 
	 * Get a parent documents'type for this type.
	 * @return parent documents'type for this type.
	 */
	public Integer getParent() {
		return parent;
	}
	
	/**
	 * Set a parent documents'type for this type.
	 * @param parent parent documents'type for this type.
	 */
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
	/**
	 * Get weight of this type for ordering.
	 * @return weight 
	 */
	public Short getWeight() {
		return weight;
	}
	
	/**
	 * Set weight of this type for ordering.
	 * @param weight
	 */
	public void setWeight(Short weight) {
		this.weight = weight;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof DocType){
			DocType type = (DocType)o;
			if(type.getName() != name || type.getParent() != parent)
				return false;
			return true;
		}else
			return false;
	}
}
