package igor.bts.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Igor
 * Entity Type TpInternet. Using in the {@link igor.bts.entity.Service} entity
 */
@XmlRootElement(name="tp_internet")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "tpInternet")
@NamedQuery(name=TpInternet.ALL_TP, query = "SELECT t from TpInternet t")
public class TpInternet implements Serializable{
	/**
	 * String constant for a Named Query.
	 */
	@XmlTransient
	public static final String ALL_TP = "ALL_TP";
	private Integer id;
	private BigDecimal abon_plata;
	private BigDecimal includ_traf;
	private Byte ed_izm_incl;
	private BigDecimal ext_cost;
	private Boolean local = false;
	private BigDecimal local_cost;
	
	/******* Constructors *********/
	public TpInternet(){}
	public TpInternet(BigDecimal abon_plata, BigDecimal includ_traf, Byte ed_izm_incl, 
			BigDecimal ext_cost,
			Boolean local, BigDecimal local_cost){
		this.abon_plata = abon_plata;
		this.includ_traf = includ_traf;
		this.ed_izm_incl = ed_izm_incl;
		this.ext_cost = ext_cost;
		this.local = local;
		this.local_cost = local_cost;
		
	}
	
	/**** Setters  & Getters ********/
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
	 * @param id primary key
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/** 
	 * Get a periodic payment
	 * @return periodic payment
	 */
	@NotNull @Digits(integer = 12, fraction = 2)
	@Column(precision = 12, scale = 2)
	public BigDecimal getAbon_plata() {
		return abon_plata;
	}
	
	/**
	 * Set a periodic payment. Not nullable. precision = 12, scale = 2 
	 * @param abon_plata periodic payment
	 */
	public void setAbon_plata(BigDecimal abon_plata) {
		this.abon_plata = abon_plata;
	}
	
	/** 
	 * Get an volume of the included traffic.
	 * @return volume of included traffic
	 */
	@NotNull @Digits(integer = 12, fraction = 2)
	@Column(precision = 12, scale = 2)
	public BigDecimal getInclud_traf() {
		return includ_traf;
	}
	
	/**
	 * Set a volume of included traffic. Not nullable. precision = 12, scale = 2.
	 * @param includ_traf volume of included traffic
	 */
	public void setInclud_traf(BigDecimal includ_traf) {
		this.includ_traf = includ_traf;
	}
	
	/**
	 * Get an unit of traffic
	 * @return unit of traffic
	 */
	@NotNull @Max(127) @Min(0)
	@Column(nullable = false)
	public Byte getEd_izm_incl() {
		return ed_izm_incl;
	}
	
	/**
	 * Set an unit of traffic. Not nullable. Vslue = 0 - 127.
	 * @param ed_izm_incl unit of traffic
	 */
	public void setEd_izm_incl(Byte ed_izm_incl) {
		this.ed_izm_incl = ed_izm_incl;
	}
	
	/**
	 * Get an cost of external traffic.
	 * @return cost of external traffic
	 */
	@NotNull @Digits(integer = 12, fraction = 2)
	@Column(nullable = false, precision = 12, scale = 2)
	public BigDecimal getExt_cost() {
		return ext_cost;
	}
	
	/**
	 * Set an cost of external traffic. Not nullable. precision = 12, scale = 2.
	 * @param ext_cost cost of external traffic
	 */
	public void setExt_cost(BigDecimal ext_cost) {
		this.ext_cost = ext_cost;
	}
	
	/**
	 * Get flag of using local traffic.
	 * @return flag of using local traffic
	 */
	@NotNull
	@Column(nullable = false)
	public Boolean getLocal() {
		return local;
	}
	
	/**
	 * Set an flag of using local traffic. Not nullable. 
	 * @param local flag of using local traffic
	 */
	public void setLocal(Boolean local) {
		this.local = local;
	}
	
	/**
	 * Get an cost of local traffic.
	 * @return cost of locat traffic
	 */
	@Digits(integer = 12, fraction = 2)
	@Column(precision = 12, scale = 2)
	public BigDecimal getLocal_cost() {
		return local_cost;
	}
	
	/**
	 * Set an cost of local traffic. precision = 12, scale = 2.
	 * @param local_cost cost of local traffic.
	 */
	public void setLocal_cost(BigDecimal local_cost) {
		this.local_cost = local_cost;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof TpInternet){
			TpInternet tp = (TpInternet)o;
			if(tp.getLocal() != local || tp.getAbon_plata() != abon_plata 
					|| tp.getEd_izm_incl() != ed_izm_incl || tp.getExt_cost() != ext_cost
					|| tp.includ_traf != includ_traf || tp.getLocal_cost() != local_cost)
				return false;
			return true;
		}else
			return false;
	}
	
	
}
