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

@XmlRootElement(name="client")
@XmlSeeAlso({Bank.class, Podpisant.class, Manager.class})
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@Table( name = "client")
@NamedQuery(name=Client.ALL_CLIENT, query = "SELECT c from Client c")
public class Client {
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
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull @Size(max = 120)
	@Column(nullable = false, length = 120)
	@XmlAttribute
	public String getSmallname() {
		return smallname;
	}

	public void setSmallname(String smallname) {
		this.smallname = smallname;
	}

	@NotNull @Size(max = 255)
	@Column(nullable = false, length = 255)
	@XmlAttribute
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Size(max = 255) @NotNull
	@Column(nullable = false, length = 255)
	@XmlAttribute
	public String getUrid_address() {
		return urid_address;
	}

	public void setUrid_address(String urid_address) {
		this.urid_address = urid_address;
	}

	@Size(max = 255) @NotNull
	@Column(nullable = false, length = 255)
	@XmlAttribute
	public String getPocht_address() {
		return pocht_address;
	}

	public void setPocht_address(String pocht_address) {
		this.pocht_address = pocht_address;
	}
	
	@NotNull
	@Column(nullable = false)
	@XmlAttribute
	public Boolean getIs_phys() {
		return is_phys;
	}

	public void setIs_phys(Boolean is_phys) {
		this.is_phys = is_phys;
	}

	@Size(min=10, max = 12)
	@Column(length = 12)
	@XmlAttribute
	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	@Pattern(regexp="\\d{10}")
	@Column(length=10)
	@XmlAttribute
	public String getKpp() {
		return kpp;
	}

	public void setKpp(String kpp) {
		this.kpp = kpp;
	}

	@XmlAttribute
	public String getOkpo() {
		return okpo;
	}

	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	@Embedded
	@XmlElement
	public Podpisant getPodpisant() {
		return podpisant;
	}

	public void setPodpisant(Podpisant podpisant) {
		this.podpisant = podpisant;
	}

	@Size(max = 90)
	@Column(length = 90)
	@XmlAttribute
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlAttribute
	public Boolean getIs_delivery() {
		return is_delivery;
	}

	public void setIs_delivery(Boolean is_delivery) {
		this.is_delivery = is_delivery;
	}

	@Size(max = 10)
	@Column(length = 10)
	@XmlAttribute
	public String getDelivery_index() {
		return delivery_index;
	}

	public void setDelivery_index(String delivery_index) {
		this.delivery_index = delivery_index;
	}

	@Size(max = 255)
	@Column(length = 255)
	@XmlAttribute
	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	
	@OneToOne(fetch = FetchType.LAZY)
	@XmlElement
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@XmlElement
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Pattern(regexp = "\\d{20}")
	@Column(length = 20)
	@XmlAttribute
	public String getRas_schet() {
		return ras_schet;
	}

	public void setRas_schet(String ras_schet) {
		this.ras_schet = ras_schet;
	}

	@Pattern(regexp = "\\d{20}")
	@Column(length = 20)
	@XmlAttribute
	public String getKor_schet() {
		return kor_schet;
	}

	public void setKor_schet(String kor_schet) {
		this.kor_schet = kor_schet;
	}

	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="client")
	@OneToMany(fetch = FetchType.LAZY, mappedBy="client")
	@XmlElement(name = "dogovor")
	public List<Dogovor> getDogovors() {
		return dogovors;
	}

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
