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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Igor
 *   Entity Type Bank
 */
@XmlRootElement(name="bank")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Access(AccessType.PROPERTY)
@NamedQuery(name = Bank.ALL_BANK, query = "Select b from Bank b")
public class Bank implements Serializable {
	/**
	 * Const string for a Named Query
	 */
	@XmlTransient
	public static final String ALL_BANK = "ALL_BANK";
	private Integer id;
	private String name;
	private String bik;
	private String okpo;
	
	/***** Constructors ***************/
	public Bank(){}
	public Bank(String name, String bik, String okpo){
		this.name = name;
		this.bik = bik;
		this.okpo = okpo;
	}
	
	/****** Setters & Getters ***************/
	/**
	 * Get primary key
	 * @return primary key
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	/**
	 * Set primary key
	 * @param id primary key
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get name
	 * @return name
	 */
	@NotNull @Size(max=255)
	@Column(nullable = false, length = 255)
	public String getName() {
		return name;
	}
	
	/**
	 * Set name. MaxSize=255, not nullable.
	 * @param name name. 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get bank's BIK.
	 * @return banks's BIK
	 */
	@NotNull @Pattern(regexp = "\\d{9}")
	@Column(nullable = false, length = 9)
	public String getBik() {
		return bik;
	}
	/**
	 * Set bank's BIK. Consist of 9 digits, not nullable.
	 * @param bik bank's BIK
	 */
	public void setBik(String bik) {
		this.bik = bik;
	}
	
	/**
	 * Get bank's OKPO
	 * @return OKPO
	 */
	@Pattern(regexp="\\d{10}") 
	@Column(length = 10)
	public String getOkpo() {
		return okpo;
	}
	
	/**
	 * Set bank's OKPO. Consist of 10 digits, can be nullable.
	 * @param okpo bank's OKPO
	 */
	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}
	
	/* Methods */
	/**
	 * Ovirride equals method for a comparing.
	 */
	@Override
	public boolean equals(Object o){
		Bank b = (Bank)o;
		if( !b.getBik().equals(bik) )
			return false;
		return true;
	}
	
}
