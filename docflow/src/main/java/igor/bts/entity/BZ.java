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

@XmlRootElement(name="bz")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Access(AccessType.PROPERTY)
@Table(name="bz")
@NamedQuery(name=BZ.ALL_BZ, query = "SELECT b from BZ b")
public class BZ {
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
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull
	@OneToOne(cascade = CascadeType.MERGE, optional=false)
	@JoinColumn(nullable=false)
	public DocType getType() {
		return type;
	}
	public void setType(DocType type) {
		this.type = type;
	}
	
	@NotNull @Size(max =10)
	@Column(nullable = false, length = 10)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDate_start() {
		return date_start;
	}
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDate_stop() {
		return date_stop;
	}
	public void setDate_stop(Date date_stop) {
		this.date_stop = date_stop;
	}
	
	@Size(max = 30)
	@Column(length = 30)
	public String getSpeed_access() {
		return speed_access;
	}
	public void setSpeed_access(String speed_access) {
		this.speed_access = speed_access;
	}
	
	@Size(max = 128)
	@Column(length = 128)
	public String getPhone_list() {
		return phone_list;
	}
	public void setPhone_list(String phone_list) {
		this.phone_list = phone_list;
	}
	
	@NotNull
	@Column(nullable = false)
	public Boolean getUnlimit() {
		return unlimit;
	}
	public void setUnlimit(Boolean unlimit) {
		this.unlimit = unlimit;
	}
	
	@Size(max = 150)
	@Column(length = 150)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Size(max = 255)
	@Column(length = 255)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@NotNull
	@Column(nullable = false)
	public Boolean getIs_original() {
		return is_original;
	}
	public void setIs_original(Boolean is_original) {
		this.is_original = is_original;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getOriginal_date() {
		return original_date;
	}
	public void setOriginal_date(Date original_date) {
		this.original_date = original_date;
	}
	
	@ManyToOne(cascade = CascadeType.MERGE, optional=false)
	@JoinColumn(name="dogovor_id")
	public Dogovor getDogovor() {
		return dogovor;
	}
	public void setDogovor(Dogovor dogovor) {
		this.dogovor = dogovor;
	}
	
	@OneToMany(cascade=CascadeType.MERGE, mappedBy="bz")
	public List<Service> getServices() {
		return services;
	}
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
