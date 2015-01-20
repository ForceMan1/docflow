package igor.bts.web;

import igor.bts.entity.Bank;
import igor.bts.entity.DocType;
import igor.bts.entity.EdIzm;
import igor.bts.entity.Manager;
import igor.bts.facade.VocFacade;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named("manager")
@RequestScoped
public class ManagerController {
	private DocType docType;
	private Bank bank;
	private EdIzm edIzm;
	private Manager manager;
	
	@EJB
	private VocFacade vocFacade;
	
	/* Constructors */
	public ManagerController(){
		docType = new DocType();
		bank = new Bank();
		edIzm = new EdIzm();
		manager = new Manager();
	}

	/* Getters & Setters */
	public DocType getDocType() {
		return docType;
	}

	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public EdIzm getEdIzm() {
		return edIzm;
	}

	public void setEdIzm(EdIzm edIzm) {
		this.edIzm = edIzm;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	/* Methods */
	
}
