package igor.bts.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Igor
 * Entity Type Dogovor
 */
@XmlRootElement(name="dogovor")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Access(AccessType.PROPERTY)
@NamedQuery(name = Dogovor.ALL_DOGOVOR, query = "Select d FROM Dogovor d")
public class Dogovor {
	/**
	 * String const for a Named Query
	 */
	@XmlTransient
	public static final String ALL_DOGOVOR = "ALL_DOGOVOR";
	private Integer id;
	private DocType type;
	private String number;
	private Date date;
	private Date dateStop;
	private String info;
	private Boolean is_original;
	private Date original_date;
	private Client client;
	private List<BZ> bzs;
	
	/****** Constructors ******************/
	public Dogovor(){}
	public Dogovor(DocType type, String number, Date date, Date dateStop, String info, 
			Boolean is_original, Date original_date, Client client, List<BZ> bzs){
		this.type = type;
		this.number = number;
		this.date = date;
		this.dateStop = dateStop;
		this.info = info;
		this.is_original = is_original;
		this.original_date = original_date;
		this.client = client;
		this.bzs = bzs;
	}
	
	/** Setters & Getters   ***************/
	/**
	 * Get a primary key.
	 * @return primary key
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set a primary key.
	 * @param id primary key.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/** 
	 * Get {@link igor.bts.entity,DocType} document's type of this Dogovor.
	 * @return {@link igor.bts.entity,DocType} document's type of this Dogovor.
	 */
	@NotNull
	@OneToOne(cascade=CascadeType.MERGE, optional=false)
	@JoinColumn(nullable = false)
	public DocType getType() {
		return type;
	}
	
	/**
	 * Set {@link igor.bts.entity,DocType} document's type of this Dogovor. Not nullable.
	 * @param type {@link igor.bts.entity,DocType} document's type of this Dogovor.
	 */
	public void setType(DocType type) {
		this.type = type;
	}
	
	/**
	 * Get a number of a dogovor.
	 * @return number of a dogovor
	 */
	@Size(max = 20)
	@Column(nullable = false, length = 20)
	public String getNumber() {
		return number;
	}
	
	/**
	 * Set a number of a dogovor. Max size = 20.
	 * @param number number of a dogovor
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * Get a date of a dogovor.
	 * @return date of a dogovor
	 */
	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	
	/**
	 * Set a date of a dogovor. Not nullable.
	 * @param date date of a dogovor.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Get a ending date of a dogovor 
	 * @return a ending date of a dogovor
	 */
	@Temporal(TemporalType.DATE)
	public Date getDateStop() {
		return dateStop;
	}
	
	/**
	 * Set a ending date of a dogovor
	 * @param dateStop ending date of a dogovor
	 */
	public void setDateStop(Date dateStop) {
		this.dateStop = dateStop;
	}
	
	/**
	 * Get an additional info
	 * @return additional info
	 */
	@Size(max = 255)
	@Column(length = 255)
	public String getInfo() {
		return info;
	}
	
	/**
	 * Set an additional info
	 * @param info additional info
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	/**
	 * Get a status of a document: original or draft copy.
	 * @return status of a document
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIs_original() {
		return is_original;
	}
	
	/**
	 * Set a status of a document: original or draft copy. Not nullable.
	 * @param is_original status of a document: original or draft copy.
	 */
	public void setIs_original(Boolean is_original) {
		this.is_original = is_original;
	}
	
	/**
	 * Get a getting date of a document
	 * @return getting date of a document
	 */
	@Temporal(TemporalType.DATE)
	public Date getOriginal_date() {
		return original_date;
	}
	
	/**
	 * Set a getting date of a document
	 * @param original_date getting date of a document
	 */
	public void setOriginal_date(Date original_date) {
		this.original_date = original_date;
	}
	
	/**
	 * Get {@link igor.bts.entity.Client} client of this dogovor.
	 * @return {@link igor.bts.entity.Client} client of this dogovor
	 */
	@ManyToOne(cascade = CascadeType.MERGE, optional=false)
	@JoinColumn(name="client_jfk", nullable=false)
	public Client getClient() {
		return client;
	}
	
	/**
	 * Set {@link igor.bts.entity.Client} client of this dogovor.
	 * @param client {@link igor.bts.entity.Client} client of this dogovor
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	
	/**
	 * Get list {@link igor.bts.entity.BZ} blank zakaza 
	 * @return list {@link igor.bts.entity.BZ} blank zakaza
	 */
	@OneToMany(cascade=CascadeType.MERGE, mappedBy="dogovor")
	public List<BZ> getBzs() {
		return bzs;
	}
	
	/**
	 * Set list {@link igor.bts.entity.BZ} blank zakaza
	 * @param bzs list {@link igor.bts.entity.BZ} blank zakaza
	 */
	public void setBzs(List<BZ> bzs) {
		this.bzs = bzs;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof Dogovor){
			Dogovor dogovor = (Dogovor)o;
			if(dogovor.getClient() != client || dogovor.getNumber() != number ||
					dogovor.getType() != type)
				return false;
			return true;
		}else
			return false;
	}
	
	
}
