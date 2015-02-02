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

@XmlRootElement(name="dogovor")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Access(AccessType.PROPERTY)
@NamedQuery(name = Dogovor.ALL_DOGOVOR, query = "Select d FROM Dogovor d")
public class Dogovor {
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
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull
	@OneToOne(cascade=CascadeType.MERGE, optional=false)
	@JoinColumn(nullable = false)
	public DocType getType() {
		return type;
	}
	public void setType(DocType type) {
		this.type = type;
	}
	
	@Size(max = 20)
	@Column(nullable = false, length = 20)
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
	public Date getDateStop() {
		return dateStop;
	}
	public void setDateStop(Date dateStop) {
		this.dateStop = dateStop;
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
	@JoinColumn(name="client_jfk", nullable=false)
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	@OneToMany(cascade=CascadeType.MERGE, mappedBy="dogovor")
	public List<BZ> getBzs() {
		return bzs;
	}
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
