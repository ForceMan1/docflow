package igor.bts.facade;

import java.util.List;






import igor.bts.CRUDEntityException;
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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/** 
 * 
 * @author Igor
 * EJB Facade class for managing entities
 */
@Stateless
public class VocFacade {
	@Inject  
	//private IBankDAO<Bank> bankDAO;
	private IBankDAO bankDAO;
	@Inject
	private IDocTypeDAO docTypeDAO;
	@Inject
	private IEdIzmDAO edIzmDAO;
	@Inject
	private IManagerDAO managerDAO;
	@Inject
	private ITpInternetDAO tpInternetDAO;
	
	/* Getters & Setters */
	/**
	 * Get {@link igor.bts.dao.IBankDAO<Bank>} DAO object for managing {@link igor.bts.entity.Bank} entity instances.
	 * @return DAO object for managing {@link igor.bts.entity.Bank} entity instances
	 */
	public IBankDAO getBankDAO() {
		return bankDAO;
	}
	
	/**
	 * Set {@link igor.bts.dao.IBankDAO<Bank>} DAO object for managing {@link igor.bts.entity.Bank} entity instances.
	 * @param bankDAO DAO object for managing {@link igor.bts.entity.Bank} entity instances
	 */
	public void setBankDAO(IBankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}
	
	/**
	 * Get {@link igor.bts.dao.IDocTypeDAO<DocType>} DAO object for managing {@link igor.bts.entity.DocType} entity instances.
	 * @return DAO object for managing {@link igor.bts.entity.DocType} entity instances
	 */
	public IDocTypeDAO getDocTypeDAO() {
		return docTypeDAO;
	}
	
	/**
	 * Set {@link igor.bts.dao.IDocTypeDAO<DocType>} DAO object for managing {@link igor.bts.entity.DocType} entity instances.
	 * @param docTypeDAO DAO object for managing {@link igor.bts.entity.DocType} entity instances
	 */
	public void setDocTypeDAO(IDocTypeDAO docTypeDAO) {
		this.docTypeDAO = docTypeDAO;
	}
	
	/**
	 * Get {@link igor.bts.dao.IEdIzmDAO<EdIzm>} DAO object for managing {@link igor.bts.entity.EdIzm} entity instances.
	 * @return DAO object for managing {@link igor.bts.entity.EdIzm} entity instances
	 */
	public IEdIzmDAO getEdIzmDAO() {
		return edIzmDAO;
	}
	
	/**
	 * Set {@link igor.bts.dao.IEdIzmDAO<EdIzm>} DAO object for managing {@link igor.bts.entity.EdIzm} entity instances.
	 * @param edIzmDAO DAO object for managing {@link igor.bts.entity.EdIzm} entity instances
	 */
	public void setEdIzmDAO(IEdIzmDAO edIzmDAO) {
		this.edIzmDAO = edIzmDAO;
	}
	
	/**
	 * Get {@link igor.bts.dao.IManagerDAO<Manager>} DAO object for managing {@link igor.bts.entity.Manager} entity instances.
	 * @return DAO object for managing {@link igor.bts.entity.Manager} entity instances
	 */
	public IManagerDAO getManagerDAO() {
		return managerDAO;
	}
	
	/**
	 * Set {@link igor.bts.dao.IManagerDAO<Manager>} DAO object for managing {@link igor.bts.entity.Manager} entity instances.
	 * @param managerDAO DAO object for managing {@link igor.bts.entity.Manager} entity instances
	 */
	public void setManagerDAO(IManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}
	
	/**
	 * Get {@link igor.bts.dao.ITpInternetDAO<TpInternet>} DAO object for managing {@link igor.bts.entity.TpInternet} entity instances.
	 * @return DAO object for managing {@link igor.bts.entity.TpInternet} entity instances
	 */
	public ITpInternetDAO getTpInternetDAO() {
		return tpInternetDAO;
	}
	
	/**
	 * Set {@link igor.bts.dao.ITpInternetDAO<Bank>} DAO object for managing {@link igor.bts.entity.TpInternet} entity instances.
	 * @param tpInternetDAO DAO object for managing {@link igor.bts.entity.TpInternet} entity instances
	 */
	public void setTpInternetDAO(ITpInternetDAO tpInternetDAO) {
		this.tpInternetDAO = tpInternetDAO;
	}
	
	
		
	/* Methods */
	/* Bank */
	/**
	 * Create {@link igor.bts.entity.Bank} object.
	 * @param bank {@link igor.bts.entity.Bank} object
	 * @return created {@link igor.bts.entity.Bank} object
	 * @throws CRUDEntityException
	 * @throws IllegalArgumentException
	 * 
	 */
	public Bank createBank(Bank bank){
		return bankDAO.create(bank);
	}
	
	/**
	 * Merge state of {igor.bts.entity.Bank} object to a database.
	 * @param bank {igor.bts.entity.Bank} object
 	 * @throws CRUDEntityException
	 * @throws IllegalArgumentException
	 */
	public void updateBank(Bank bank){
		bankDAO.update(bank);
	}
	
	/** 
	 * Merge state of {igor.bts.entity.Bank} object to a database.
	 * @param id
	 * @return true if removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteBank(Integer id){
		return bankDAO.delete(id);
	}
	
	/**
	 * Get Bank entity instance by ID.
	 * @param id ID of entity instance
	 * @return Bank entity instance or null if not exists
	 */
	public Bank getBank(Integer id){
		return bankDAO.getById(id);
	}
	
	/**
	 * Get list of all Bank entity instances.
	 * @return list of all Entity instances
	 * @throws CRUDEntityException
	 */
	public List<Bank> getAllBank(){
		return bankDAO.getAll();
	}
	
	/* DocType */
	/**
	 * Create {@link igor.bts.entity.DocType} object.
	 * @param docType {@link igor.bts.entity.DocType} object
	 * @return created {@link igor.bts.entity.DocType} object
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public DocType createDocType(DocType docType){
		return docTypeDAO.create(docType);
	}
	
	/**
	 * Merge state of {igor.bts.entity.DocType} object to a database.
	 * @param docType {igor.bts.entity.Bank} object
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public DocType updateDocType(DocType docType){
		return docTypeDAO.update(docType);
	}
	
	/**
	 * Delete DocType instance by ID
	 * @param id ID of entity instance
	 * @return true if removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteDocType(Integer id){
		return docTypeDAO.delete(id);
	}
	
	/**
	 * Get DocType entity instance by ID.
	 * @param id ID of entity instance
	 * @return entity instance or null if not exists
	 */
	public DocType getDocType(Integer id){
		return docTypeDAO.getById(id);
	}
	
	/**
	 * Get all DocType entity instances.
	 * @return list of all DocType entity instances
	 * @throws CRUDEntityException
	 */
	public List<DocType> getAllDocType(){
		return docTypeDAO.getAll();
	}
	
	/* EdIzm */
	/**
	 * Create {@link igor.bts.entity.EdIzm} object.
	 * @param edIzm {@link igor.bts.entity.EdIzm} object
	 * @return created {@link igor.bts.entity.EdIzm} object
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public EdIzm createEdIzm(EdIzm edIzm){
		return edIzmDAO.create(edIzm);
	}
	
	/**
	 * Merge state of {igor.bts.entity.EdIzm} object to a database.
	 * @param edIzm {igor.bts.entity.Bank} object
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public void updateEdIzm(EdIzm edIzm){
		edIzmDAO.update(edIzm);
	}
	
	/** 
	 * Remove DocType entity instance by ID
	 * @param id ID of entity instance
	 * @return true if the removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteEdIzm(Integer id){
		return edIzmDAO.delete(id);
	}
	
	/**
	 * Get EdIzm entity instance by ID.
	 * @param id ID of entity instance
	 * @return DocTypeEntity entity instance or null if not exists
	 */
	public EdIzm getEdIzm(Integer id){
		return edIzmDAO.getById(id);
	}
	
	/**
	 * Get all EdIzm entity instances.
	 * @return list of all EdIzm entity instances
	 * @throws CRUDEntityException 
	 */
	public List<EdIzm> getAllEdIzm(){
		return edIzmDAO.getAll();
	}
	
	/* Manager */
	/**
	 * Create {@link igor.bts.entity.Manager} object.
	 * @param manager {@link igor.bts.entity.Manager} object
	 * @return created {@link igor.bts.entity.Manager} object
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public Manager createManager(Manager manager){
		return managerDAO.create(manager);
	}
	
	/**
	 * Merge state of {igor.bts.entity.manager} object to a database.
	 * @param manager {@link igor.bts.entity.Manager} object
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public void updateManager(Manager manager){
		managerDAO.update(manager);
	}
	
	/**
	 * Remove Manager entity instance by ID. 
	 * @param id ID of entity instance
	 * @return true if the removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteManager(Integer id){
		return managerDAO.delete(id);
	}
	
	/**
	 * Get Manager entity instance by ID.
	 * @param id ID of entity instance
	 * @return Manager entity instance
	 */
	public Manager getManager(Integer id){
		return managerDAO.getById(id);
	}
	
	/**
	 * Get all {@link igor.bts.entity.Manager} entity instances by ID 
	 * @return list of all {@link igor.bts.entity.Manager} entity instances
	 * @throws CRUDEntityException
	 */
	public List<Manager> getAllManager(){
		return managerDAO.getAll();
	}
	
	/* TpInternet */
	/**
	 * Create {@link igor.bts.entity.TpInternet} object.
	 * @param tpInternet {@link igor.bts.entity.TpInternet} object.
	 * @return created{@link igor.bts.entity.TpInternet} object.
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public TpInternet createTpInternet(TpInternet tpInternet){
		return tpInternetDAO.create(tpInternet);
	}
	
	/**
	 * Merge state of {igor.bts.entity.TpInternet} object to a database.
	 * @param tpInternet {@link igor.bts.entity.TpInternet} object
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public void updateTpInternet(TpInternet tpInternet){
		tpInternetDAO.update(tpInternet);
	}
	
	/**
	 * Remove a TpInternet entity instance by Id.
	 * @param id Id of entity instance
	 * @return true if the removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteTpInternet(Integer id){
		return tpInternetDAO.delete(id);
	}
	
	/**
	 * Get a {@link igor.bts.entity.TpInternet} entity instance by ID.
	 * @param id Id of entity instance
	 * @return {@link igor.bts.entity.TpInternet} entity instance or null if not exists
	 */
	public TpInternet getTpInternet(Integer id){
		return tpInternetDAO.getById(id);
	}

	/**
	 * Get all {@link igor.bts.entity.TpInternet} entity instances.
	 * @return list of all {@link igor.bts.entity.TpInternet} entity instances
	 * @throws CRUDEntityException 
	 */
	public List<TpInternet> getAllTpInternet(){
		return tpInternetDAO.getAll();
	}
}
