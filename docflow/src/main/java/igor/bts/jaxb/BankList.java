package igor.bts.jaxb;

import igor.bts.entity.Bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/*
@XmlRootElement(name="banks")
@XmlSeeAlso({igor.bts.entity.Bank.class})
public class BankList {
	private List<Bank> banks;
	
	@XmlElementWrapper(name="bank")
	@XmlElementRef()
	public List<Bank> getBanks(){
		return banks;
	}
	
	public void setBanks(List<Bank> banks){
		this.banks = banks;
	}
	
	public BankList(List<Bank> banks){
		this.banks = banks;
	}
}
*/

@XmlRootElement
@XmlSeeAlso(Bank.class)
public class BankList extends ArrayList<Bank> {
	public BankList() {
		super();
	}

	public BankList(Collection<? extends Bank> c) {
		super(c);
	}
	@XmlElement(name = "bank")
	public List<Bank> getBanks() {
		return this;
	}

	public void setBanks(List<Bank> banks) {
		this.addAll(banks);
	}
}

