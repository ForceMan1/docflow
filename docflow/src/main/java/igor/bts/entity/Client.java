package igor.bts.entity;
import java.util.List;

import igor.bts.Podpisant;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Igor
 * Entity type Client 
 */
@XmlRootElement(name="client")
@XmlSeeAlso({Bank.class, Podpisant.class, Manager.class})
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@Table( name = "client")
@NamedQuery(name=Client.ALL_CLIENT, query = "SELECT c from Client c")
public class Client {
	/**
	 * String const for a Named Query.
	 */
	@XmlTransient
	public static final String ALL_CLIENT = "ALL_CLIENT"; 
	private Integer id;
	private String smallname;
	private String fullname;
	private String urid_address;
	private String pocht_address;
	private Boolean is_phys;
	private String inn;
	private String kpp;
	private String okpo;
	private Podpisant podpisant;
	private String phone;
	private Boolean is_delivery;
	private String delivery_index;
	private String delivery_address; 
	private Manager manager;
	private Bank bank;
	private String ras_schet;
	private String kor_schet;
	private List<Dogovor> dogovors;

	/***  Constructors ******************************/
	public Client(){}
	public Client(String smallname, String fullname, String urid_address, String pocht_address,
			Boolean is_phys, String inn, String kpp, String okpo, Podpisant podpisant, String phone,
			Boolean is_delivery, String delivery_index, String delivery_address, Manager manager,
			Bank bank, String ras_schet, String kor_schet, List<Dogovor> dogovors){
		this.smallname = smallname;
		this.fullname = fullname;
		this.urid_address = urid_address;
		this.pocht_address = pocht_address;
		this.is_phys = is_phys;
		this.inn = inn;
		this.kpp = kpp;
		this.okpo = okpo;
		this.podpisant = podpisant;
		this.phone = phone;
		this.is_delivery = is_delivery;
		this.delivery_index = delivery_index;
		this.delivery_address = delivery_address;
		this.manager = manager;
		this.bank = bank;
		this.ras_schet = ras_schet;
		this.kor_schet = kor_schet;
		this.dogovors = dogovors;
	}
	
	/**** Getters & Setters ************************/
	/**
	 * Get primary key.
	 * @return primary key.
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
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
	 * Get small name 
	 * @return small name
	 */
	@Size(max = 120) @NotNull 
	@Column(nullable = false, length = 120)
	@XmlAttribute
	public String getSmallname() {
		return smallname;
	}

	/**
	 * Set small name. Max size = 120. Cannot be nullable.
	 * @param smallname small name
	 */
	public void setSmallname(String smallname) {
		this.smallname = smallname;
	}

	/**
	 * Get fullname of a client
	 * @return fullname
	 */
	@Size(max = 255) @NotNull
	@Column(nullable = false, length = 255)
	@XmlAttribute
	public String getFullname() {
		return fullname;
	}

	/**
	 * Set fullname of client. Max size = 255.
	 * @param fullname fullname of client
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * Get urid address of a client
	 * @return urid address
	 */
	@Size(max = 255) @NotNull
	@Column(nullable = false, length = 255)
	@XmlAttribute
	public String getUrid_address() {
		return urid_address;
	}

	/**
	 * Set urid address of a client. Max size = 255. Cannot be nullable.
	 * @param urid_address urid address
	 */
	public void setUrid_address(String urid_address) {
		this.urid_address = urid_address;
	}

	/**
	 * Get pocht address.
	 * @return pocht address
	 */
	@Size(max = 255) @NotNull
	@Column(nullable = false, length = 255)
	@XmlAttribute
	public String getPocht_address() {
		return pocht_address;
	}

	/** 
	 * Set pocht address. Max size = 255. Nullable.
	 * @param pocht_address pocht address.
	 */
	public void setPocht_address(String pocht_address) {
		this.pocht_address = pocht_address;
	}
	
	/** 
	 * Get type of client (urid or physics)
	 * @return type of client
	 */
	@NotNull
	@Column(nullable = false)
	@XmlAttribute
	public Boolean getIs_phys() {
		return is_phys;
	}

	/**
	 * Set type of client (urid or physics). Cannot be null.
	 * @param is_phys type of client
	 */
	public void setIs_phys(Boolean is_phys) {
		this.is_phys = is_phys;
	}

	/**
	 * Get INN of a client.
	 * @return INN
	 */
	@Size(min=10, max = 12)
	@Column(length = 12)
	@XmlAttribute
	public String getInn() {
		return inn;
	}

	/**
	 * Set INN of a client. Size = 10 - 12. Nullable.
	 * @param inn INN
	 */
	public void setInn(String inn) {
		this.inn = inn;
	}

	/**
	 * Get KPP of a client.
	 * @return kpp
	 */
	@Pattern(regexp="\\d{10}")
	@Column(length=10)
	@XmlAttribute
	public String getKpp() {
		return kpp;
	}

	/**
	 * Set KPP of a client. Size = 10. Nullable.
	 * @param kpp kpp
	 */
	public void setKpp(String kpp) {
		this.kpp = kpp;
	}

	/**
	 * Get OKPO of client 
	 * @return okpo
	 */
	@XmlAttribute
	public String getOkpo() {
		return okpo;
	}

	/**
	 * Set OKPO of a client.
	 * @param okpo
	 */
	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	/**
	 * Get {@link igor.bts.Podpisant} podpisant
	 * @return {@link igor.bts.Podpisant} 
	 */
	@Embedded
	@XmlElement
	public Podpisant getPodpisant() {
		return podpisant;
	}

	/**
	 * Set {@link igor.bts.Podpisant} podpisant.
	 * @param podpisant {@link igor.bts.Podpisant} podpisant
	 */
	public void setPodpisant(Podpisant podpisant) {
		this.podpisant = podpisant;
	}

	/**
	 * Get contact's phones of the this client.
	 * @return cntact's phones
	 */
	@Size(max = 90)
	@Column(length = 90)
	@XmlAttribute
	public String getPhone() {
		return phone;
	}

	/**
	 * Set contact's phones of the this client
	 * @param phone contact's phones
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get delivery flag.
	 * @return deliver flag
	 */
	@XmlAttribute
	public Boolean getIs_delivery() {
		return is_delivery;
	}

	/**
	 * Set deliver flag.
	 * @param is_delivery delivery flag
	 */
	public void setIs_delivery(Boolean is_delivery) {
		this.is_delivery = is_delivery;
	}

	/**
	 * Get delivery pocht index.
	 * @return deliver pocht index
	 */
	@Size(max = 10)
	@Column(length = 10)
	@XmlAttribute
	public String getDelivery_index() {
		return delivery_index;
	}

	/**
	 * Set delivery pocht index.
	 * @param delivery_index delivery index
	 */
	public void setDelivery_index(String delivery_index) {
		this.delivery_index = delivery_index;
	}

	/**
	 * Get delivery address.
	 * @return delivery address
	 */
	@Size(max = 255)
	@Column(length = 255)
	@XmlAttribute
	public String getDelivery_address() {
		return delivery_address;
	}

	/**
	 * Set delivery index.
	 * @param delivery_address delivery address
	 */
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	/**
	 * Get {@link igor.bts.entity.Manager} manager
	 * @return manager
	 */
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@XmlElement
	public Manager getManager() {
		return manager;
	}

	/**
	 * Set {@link igor.bts.entity.Manager} manager.
	 * @param manager manager
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Get {@link igor.bts.entity.Bank} bank of a client.
	 * @return {@link igor.bts.entity.Bank} bank
	 */
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@XmlElement
	public Bank getBank() {
		return bank;
	}

	/**
	 * Set {@link igor.bts.entity.Bank} bank of a client. 
	 * @param bank {@link igor.bts.entity.Bank} bank
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * Get ras_chet of a client.
	 * @return ras_schet
	 */
	@Pattern(regexp = "\\d{20}")
	@Column(length = 20)
	@XmlAttribute
	public String getRas_schet() {
		return ras_schet;
	}

	/**
	 * Set ras_schet of a client. Size = 20.
	 * @param ras_schet ras_schet of a client
	 */
	public void setRas_schet(String ras_schet) {
		this.ras_schet = ras_schet;
	}

	/**
	 * Get kor_schet of a client.
	 * @return kor_schet of a client
	 */
	@Pattern(regexp = "\\d{20}")
	@Column(length = 20)
	@XmlAttribute
	public String getKor_schet() {
		return kor_schet;
	}

	/**
	 * Set kor_schet of a client. Size = 20.
	 * @param kor_schet kor_schet of a client
	 */
	public void setKor_schet(String kor_schet) {
		this.kor_schet = kor_schet;
	}

	/** 
	 * Get list of {@link igor.bts.entity.Dogovor} dogovors of this client. 
	 * @return list of dogovors
	 */
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy="client")
	@XmlElement(name = "dogovor")
	public List<Dogovor> getDogovors() {
		return dogovors;
	}

	/**
	 * Set list of {@link igor.bts.entity.Dogovor} dogovors of this client.
	 * @param dogovors list of dogovors
	 */
	public void setDogovors(List<Dogovor> dogovors) {
		this.dogovors = dogovors;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof Client){
			Client client = (Client)o;
			if(client.getInn() != inn)
				return false;
			if(client.getPodpisant() != null)
				if(client.getPodpisant().getPass_nomer().equals(podpisant.getPass_nomer())
					|| client.getPodpisant().getPass_seria().equals(podpisant.getPass_seria()))
					return false;
			return true;
		}else
			return false;
	}
}
