package igor.bts.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Igor
 * Blank Zakaza Entity 
 */
@XmlRootElement(name="bz")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Access(AccessType.PROPERTY)
@Table(name="bz")
@NamedQuery(name=BZ.ALL_BZ, query = "SELECT b from BZ b")
public class BZ {
	/**
	 * String const for a Named Query.
	 */
	@XmlTransient
	public static final String ALL_BZ = "ALL_BZ";
	private Integer id;
	private DocType type;
	private String number;  
	private Date date;
	private Date date_start;	
	private Date date_stop;
	private String speed_access;
	private String phone_list;
	private Boolean unlimit = true;
	private String address;
	private String info;
	private Boolean is_original = false;
	private Date original_date;
	private Dogovor dogovor;
	private List<Service> services;
	
	/***** Constructors ***********************/
	public BZ(){}
	public BZ(DocType type, String number, Date date, Date date_start, Date date_stop,
			String speed_access, String phone_list, Boolean unlimit, String address, String info,
			Boolean is_original, Date original_date, Dogovor dogovor, List<Service> services){
		this.type = type;
		this.number = number;
		this.date = date;
		this.date_start = date_start;
		this.date_stop = date_stop;
		this.speed_access = speed_access;
		this.phone_list = phone_list;
		this.unlimit = unlimit;
		this.address = address;
		this.info = info;
		this.is_original = is_original;
		this.original_date = original_date;
		this.dogovor = dogovor;
		this.services = services;
	}
	
	/****** Getters & Setters **********************/
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
	 * Get type of Blank Zakaza.
	 * @return type of blank zakaza
	 */
	@NotNull
	@OneToOne(cascade = CascadeType.MERGE, optional=false)
	@JoinColumn(nullable=false)
	public DocType getType() {
		return type;
	}
	
	/**
	 * Set type of blank zakaza. Not nullable.
	 * @param type type of blank zakaza. {@link igor.bts.entity.DocType}}
	*/
	public void setType(DocType type) {
		this.type = type;
	}
	
	/**
	 * Get number of blank zakaza
	 * @return number of BZ
	 */
	@NotNull @Size(max =10)
	@Column(nullable = false, length = 10)
	public String getNumber() {
		return number;
	}
	
	/**
	 * Set number of BZ. Max length = 10, not nullable.
	 * @param number number of BZ
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * Get date of BZ.
	 * @return date of BZ
	 */
	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	
	/**
	 * Set date of BZ. Not null. 
	 * @param date Date of BZ
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/** 
	 * Get date of a begining action BZ
	 * @return date of a begining action BZ
	 */
	@Temporal(TemporalType.DATE)
	public Date getDate_start() {
		return date_start;
	}
	
	/**
	 * Set date of a begining action BZ. Can be null.
	 * @param date_start date of a begining action BZ
	 */
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	
	/**
	 * Get a date of a ending action BZ (not include this date)
	 * @return date of a ending action BZ
	 */
	@Temporal(TemporalType.DATE)
	public Date getDate_stop() {
		return date_stop;
	}
	
	/**
	 * Set date of a ending action BZ. Can be null.
	 * @param date_stop
	 */
	public void setDate_stop(Date date_stop) {
		this.date_stop = date_stop;
	}
	
	/**
	 * Get speed of a canal
	 * @return speed of a canal
	 */
	@Size(max = 30)
	@Column(length = 30)
	public String getSpeed_access() {
		return speed_access;
	}
	
	/**
	 * Set a speed of a canal. Max size = 30.
	 * @param speed_access speed of a canal
	 */
	public void setSpeed_access(String speed_access) {
		this.speed_access = speed_access;
	}
	
	/**
	 * Get list of phones of this bz
	 * @return list of phones of this bz
	 */
	@Size(max = 128)
	@Column(length = 128)
	public String getPhone_list() {
		return phone_list;
	}
	
	/**
	 * Set a list of phones of this bz. Max size = 128.
	 * @param phone_list
	 */
	public void setPhone_list(String phone_list) {
		this.phone_list = phone_list;
	}
	
	/**
	 * Get type of tarification (limit, unlimit)
	 * @return type of tarification (limit, unlimit)
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getUnlimit() {
		return unlimit;
	}
	
	/**
	 * Set a type of tarification (limit, unlimit). Not nullable.
	 * @param unlimit type of tarification (limit, unlimit)
	 */
	public void setUnlimit(Boolean unlimit) {
		this.unlimit = unlimit;
	}
	
	/**
	 * Get a address of services for this BZ
	 * @return address of services for this BZ
	 */
	@Size(max = 150)
	@Column(length = 150)
	public String getAddress() {
		return address;
	}
	
	/**
	 * Set a address of services for this BZ. Max size = 150.
	 * @param address address of services for this BZ
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Get an additional info for this BZ.
	 * @return additional info for this BZ
	 */
	@Size(max = 255)
	@Column(length = 255)
	public String getInfo() {
		return info;
	}
	
	/**
	 * Set an additional info for this BZ.
	 * @param info additional info for this BZ
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	/**
	 * Set a state of document (original or draft copy).
	 * @return state of document (original or draft copy)
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getIs_original() {
		return is_original;
	}
	
	/**
	 * Set a state of document (original or draft copy)
	 * @param is_original state of document (original or draft copy)
	 */
	public void setIs_original(Boolean is_original) {
		this.is_original = is_original;
	}
	
	/**
	 * Get a date of the getting an original
	 * @return Date of the getting an original
	 */
	@Temporal(TemporalType.DATE)
	public Date getOriginal_date() {
		return original_date;
	}
	
	/**
	 * Set a date of the getting an original
	 * @param original_date Date of the getting an original
	 */
	public void setOriginal_date(Date original_date) {
		this.original_date = original_date;
	}
	
	/**
	 * Get {@link igor.bts.entity.Dogovor} of this BZ)
	 * @return dogovor ({@link igor.bts.entity.Dogovor})
	 */
	@ManyToOne(cascade = CascadeType.MERGE, optional=false)
	@JoinColumn(name="dogovor_id")
	public Dogovor getDogovor() {
		return dogovor;
	}
	
	/**
	 * Set {@link igor.bts.entity.Dogovor} of this BZ.
	 * @param dogovor {@link igor.bts.entity.Dogovor} of this BZ
	 */
	public void setDogovor(Dogovor dogovor) {
		this.dogovor = dogovor;
	}
	
	/**
	 * Get a list of {@link igor.bts.entity.Service} of this BZ.
	 * @return list of {@link igor.bts.entity.Service}
	 */
	@OneToMany(cascade=CascadeType.MERGE, mappedBy="bz")
	public List<Service> getServices() {
		return services;
	}
	
	/**
	 * Set a list of {@link igor.bts.entity.Service} of this BZ.
	 * @param services list of {@link igor.bts.entity.Service}
	 */
	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if( o instanceof BZ ){
			BZ bz = (BZ)o;
			if(bz.getDogovor() != dogovor || bz.getNumber() != number || bz.getType() != type) 
					return false;
			return true;
				
		}else
			return false;
	}
}
