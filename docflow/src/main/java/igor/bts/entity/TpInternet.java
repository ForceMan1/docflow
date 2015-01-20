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

@XmlRootElement(name="tp_internet")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "tpInternet")
@NamedQuery(name=TpInternet.ALL_TP, query = "SELECT t from TpInternet t")
public class TpInternet implements Serializable{
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
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull @Digits(integer = 12, fraction = 2)
	@Column(precision = 12, scale = 2)
	public BigDecimal getAbon_plata() {
		return abon_plata;
	}
	public void setAbon_plata(BigDecimal abon_plata) {
		this.abon_plata = abon_plata;
	}
	
	@NotNull @Digits(integer = 12, fraction = 2)
	@Column(precision = 12, scale = 2)
	public BigDecimal getInclud_traf() {
		return includ_traf;
	}
	public void setInclud_traf(BigDecimal includ_traf) {
		this.includ_traf = includ_traf;
	}
	
	@NotNull @Max(127) @Min(0)
	@Column(nullable = false)
	public Byte getEd_izm_incl() {
		return ed_izm_incl;
	}
	public void setEd_izm_incl(Byte ed_izm_incl) {
		this.ed_izm_incl = ed_izm_incl;
	}
	
	@NotNull @Digits(integer = 12, fraction = 2)
	@Column(nullable = false, precision = 12, scale = 2)
	public BigDecimal getExt_cost() {
		return ext_cost;
	}
	public void setExt_cost(BigDecimal ext_cost) {
		this.ext_cost = ext_cost;
	}
	
	@NotNull
	@Column(nullable = false)
	public Boolean getLocal() {
		return local;
	}
	public void setLocal(Boolean local) {
		this.local = local;
	}
	
	@Digits(integer = 12, fraction = 2)
	@Column(precision = 12, scale = 2)
	public BigDecimal getLocal_cost() {
		return local_cost;
	}
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
