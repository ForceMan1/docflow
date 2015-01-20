package igor.bts.facade;

import java.util.List;


import igor.bts.dao.BankDAO;
import igor.bts.dao.IBankDAO;
import igor.bts.dao.IDocTypeDAO;
import igor.bts.dao.IEdIzmDAO;
import igor.bts.dao.IManagerDAO;
import igor.bts.dao.ITpInternetDAO;
import igor.bts.dao.TpInternetDAO;
import igor.bts.entity.Bank;
import igor.bts.entity.DocType;
import igor.bts.entity.EdIzm;
import igor.bts.entity.Manager;
import igor.bts.entity.TpInternet;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VocFacade {
	@Inject  
	private IBankDAO<Bank> bankDAO;
	@Inject
	private IDocTypeDAO<DocType> docTypeDAO;
	@Inject
	private IEdIzmDAO<EdIzm> edIzmDAO;
	@Inject
	private IManagerDAO<Manager> managerDAO;
	@Inject
	private ITpInternetDAO<TpInternet> tpInternetDAO;
	
	/* Getters & Setters */
	public IBankDAO<Bank> getBankDAO() {
		return bankDAO;
	}
	public void setBankDAO(IBankDAO<Bank> bankDAO) {
		this.bankDAO = bankDAO;
	}
	public IDocTypeDAO<DocType> getDocTypeDAO() {
		return docTypeDAO;
	}
	public void setDocTypeDAO(IDocTypeDAO<DocType> docTypeDAO) {
		this.docTypeDAO = docTypeDAO;
	}
	public IEdIzmDAO<EdIzm> getEdIzmDAO() {
		return edIzmDAO;
	}
	public void setEdIzmDAO(IEdIzmDAO<EdIzm> edIzmDAO) {
		this.edIzmDAO = edIzmDAO;
	}
	public IManagerDAO<Manager> getManagerDAO() {
		return managerDAO;
	}
	public void setManagerDAO(IManagerDAO<Manager> managerDAO) {
		this.managerDAO = managerDAO;
	}
	public ITpInternetDAO<TpInternet> getTpInternetDAO() {
		return tpInternetDAO;
	}
	public void setTpInternetDAO(ITpInternetDAO<TpInternet> tpInternetDAO) {
		this.tpInternetDAO = tpInternetDAO;
	}
	
	
		
	/* Methods */
	/* Bank */
	public Bank createBank(Bank bank){
		return bankDAO.create(bank);
	}
	
	public void updateBank(Bank bank){
		bankDAO.update(bank);
	}
	
	public void deleteBank(Integer id){
		bankDAO.delete(id);
	}
	
	public Bank getBank(Integer id){
		return bankDAO.getById(id);
	}
	
	public List<Bank> getAllBank(){
		return bankDAO.getAll();
	}
	
	/* DocType */
	public DocType createDocType(DocType docType){
		return docTypeDAO.create(docType);
	}
	
	public void updateDocType(DocType docType){
		docTypeDAO.update(docType);
	}
	
	public void deleteDocType(Integer id){
		docTypeDAO.delete(id);
	}
	
	public DocType getDocType(Integer id){
		return docTypeDAO.getById(id);
	}
	
	public List<DocType> getAllDocType(){
		return docTypeDAO.getAll();
	}
	
	/* EdIzm */
	public EdIzm createEdIzm(EdIzm edIzm){
		return edIzmDAO.create(edIzm);
	}
	
	public void updateEdIzm(EdIzm edIzm){
		edIzmDAO.update(edIzm);
	}
	
	public void deleteEdIzm(Integer id){
		edIzmDAO.delete(id);
	}
	
	public EdIzm getEdIzm(Integer id){
		return edIzmDAO.getById(id);
	}
	
	public List<EdIzm> getAllEdIzm(){
		return edIzmDAO.getAll();
	}
	
	/* Manager */
	public Manager createManager(Manager manager){
		return managerDAO.create(manager);
	}
	
	public void updateManager(Manager manager){
		managerDAO.update(manager);
	}
	
	public void deleteManager(Integer id){
		managerDAO.delete(id);
	}
	
	public Manager getManager(Integer id){
		return managerDAO.getById(id);
	}
	
	public List<Manager> getAllManager(){
		return managerDAO.getAll();
	}
	
	/* TpInternet */
	public TpInternet createTpInternet(TpInternet tpInternet){
		return tpInternetDAO.create(tpInternet);
	}
	
	public void updateTpInternet(TpInternet tpInternet){
		tpInternetDAO.update(tpInternet);
	}
	
	public void deleteTpInternet(Integer id){
		tpInternetDAO.delete(id);
	}
	
	public TpInternet getTpInternet(Integer id){
		return tpInternetDAO.getById(id);
	}
	
	public List<TpInternet> getAllTpInternet(){
		return tpInternetDAO.getAll();
	}
}
