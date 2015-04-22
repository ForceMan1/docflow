package igor.bts.facade;

import java.util.List;

import igor.bts.dao.BZDAO;
import igor.bts.dao.ClientDAO;
import igor.bts.dao.DocTypeDAO;
import igor.bts.dao.DogovorDAO;
import igor.bts.dao.EdIzmDAO;
import igor.bts.dao.IBZDAO;
import igor.bts.dao.IClientDAO;
import igor.bts.dao.IDocTypeDAO;
import igor.bts.dao.IDogovorDAO;
import igor.bts.dao.IEdIzmDAO;
import igor.bts.dao.IManagerDAO;
import igor.bts.dao.IServiceDAO;
import igor.bts.dao.ITpInternetDAO;
import igor.bts.dao.ManagerDAO;
import igor.bts.dao.ServiceDAO;
import igor.bts.dao.TpInternetDAO;
import igor.bts.dao.IBankDAO;
import igor.bts.dao.BankDAO;
import igor.bts.entity.BZ;
import igor.bts.entity.Bank;
import igor.bts.entity.Client;
import igor.bts.entity.DocType;
import igor.bts.entity.Dogovor;
import igor.bts.entity.EdIzm;
import igor.bts.entity.Manager;
import igor.bts.entity.Service;
import igor.bts.entity.TpInternet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author Igor
 * Facade class for a managing entities using user privileges.
 */
@Stateless
public class DocumentFacade {
	/** 
	 * DAO object for a managing {@link igor.bts.entity.Client} entities.
	 */
	@Inject
	//private IClientDAO<Client> clientDAO;
	private IClientDAO clientDAO;
	/** 
	 * DAO object for a managing {@link igor.bts.entity.Dogovor} entities.
	 */
	@Inject
	private IDogovorDAO dogovorDAO;
	/**
	 * DAO object for a managing {@link igor.bts.entity.BZ} entities.
	 */
	@Inject
	private IBZDAO bzDAO;
	/**
	 * DAO object for a managing {@link igor.bts.entity.Service} entities.
	 */
	@Inject
	private IServiceDAO serviceDAO;
	/**
	 * DAO object for a managing {@link igor.bts.entity.DocType} entities. 
	 */
	@Inject
	private IDocTypeDAO docTypeDAO;
	/**
	 * DAO object for a managing {@link igor.bts.entity.EdIzm} entities.
	 */
	@Inject
	private IEdIzmDAO edIzmDAO;
	/**
	 * DAO object for a managing {@link igor.bts.entity.Manager} entities.
	 */
	@Inject
	private IManagerDAO managerDAO;
	/** 
	 * DAO object for a managing {@link igor.bts.entity.TpInternet} entities.
	 */
	@Inject
	private ITpInternetDAO tpInternetDAO;
	/**
	 * DAO object for a managing {@link igor.bts.entity.Bank} entities.
	 */
	@Inject
	private IBankDAO bankDAO;
	
	private Client client = new Client();
	private Dogovor dogovor = new Dogovor();
	private BZ bz = new BZ();
	private Service service = new Service();
	private DocType docType = new DocType();
	private EdIzm edIzm = new EdIzm();
	private Manager manager = new Manager();
	private Bank bank = new Bank();
	private TpInternet tpInternet = new TpInternet();
	
	/* Getters & Setters */
	/**
	 * Get {@link igor.bts.entity.Client} DAO object.
	 * @return {@link igor.bts.entity.Client} DAO object
	 */
	public IClientDAO getClientDAO() {
		return clientDAO;
	}
	
	/**
	 * Set {@link igor.bts.entity.Client} DAO object
	 * @param clientDAO {@link igor.bts.entity.Client} DAO objects
	 */
	public void setClientDAO(IClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	
	/**
	 * {@link igor.bts.entity.Dogovor} DAO object
	 * @return {@link igor.bts.entity.Dogovor} DAO object
	 */
	public IDogovorDAO getDogovorDAO() {
		return dogovorDAO;
	}
	
	/**
	 * Set {@link igor.bts.entity.Dogovor} DAO object
	 * @param dogovorDAO {@link igor.bts.entity.Dogovor} DAO object
	 */
	public void setDogovorDAO(IDogovorDAO dogovorDAO) {
		this.dogovorDAO = dogovorDAO;
	}
	
	/**
	 * Get {@link igor.bts.entity.BZ} DAO object
	 * @return {@link igor.bts.entity.BZ} DAO object
	 */
	public IBZDAO getBzDAO() {
		return bzDAO;
	}
	
	/**
	 * Set {@link igor.bts.entity.BZ} DAO object
	 * @param bzDAO {@link igor.bts.entity.BZ} DAO object
	 */
	public void setBzDAO(IBZDAO bzDAO) {
		this.bzDAO = bzDAO;
	}
	
	/**
	 * Get {@link igor.bts.entity.Service} DAO object
	 * @return {@link igor.bts.entity.Service} DAO object
	 */
	public IServiceDAO getServiceDAO() {
		return serviceDAO;
	}
	
	/**
	 * Set {@link igor.bts.entity.Service} DAO object
	 * @param serviceDAO {@link igor.bts.entity.Service} DAO object
	 */
	public void setServiceDAO(IServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}
	
	/**
	 * Get {@link igor.bts.entity.DocType} DAO object
	 * @return {@link igor.bts.entity.DocType} DAO object
	 */
	public IDocTypeDAO getDocTypeDAO() {
		return docTypeDAO;
	}
	
	/**
	 * Set {@link igor.bts.entity.DocType} DAO object
	 * @param docTypeDAO {@link igor.bts.entity.DocType} DAO object
	 */
	public void setDocTypeDAO(IDocTypeDAO docTypeDAO) {
		this.docTypeDAO = docTypeDAO;
	}
	
	/**
	 * Get {@link igor.bts.entity.EdIzm} DAO object
	 * @return {@link igor.bts.entity.EdIzm} DAO object
	 */
	public IEdIzmDAO getEdIzmDAO() {
		return edIzmDAO;
	}
	
	/**
	 * Set {@link igor.bts.entity.EdIzm} DAO object
	 * @param edIzmDAO {@link igor.bts.entity.EdIzm} DAO object
	 */
	public void setEdIzmDAO(IEdIzmDAO edIzmDAO) {
		this.edIzmDAO = edIzmDAO;
	}
	
	/**
	 * Get {@link igor.bts.entity.Manager} DAO object
	 * @return {@link igor.bts.entity.Manager} DAO object
	 */
	public IManagerDAO getManagerDAO() {
		return managerDAO;
	}
	
	/**
	 * Set {@link igor.bts.entity.Manager} DAO object
	 * @param managerDAO {@link igor.bts.entity.Manager} DAO object
	 */
	public void setManagerDAO(IManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}
	
	/**
	 * Get {@link igor.bts.entity.TpInternet} DAO object
	 * @return {@link igor.bts.entity.TpInternet} DAO object
	 */
	public ITpInternetDAO getTpInternetDAO() {
		return tpInternetDAO;
	}
	
	/**
	 * Set {@link igor.bts.entity.TpInternet} DAO object
	 * @param tpInternetDAO
	 */
	public void setTpInternetDAO(ITpInternetDAO tpInternetDAO) {
		this.tpInternetDAO = tpInternetDAO;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Dogovor getDogovor() {
		return dogovor;
	}
	public void setDogovor(Dogovor dogovor) {
		this.dogovor = dogovor;
	}
	public BZ getBz() {
		return bz;
	}
	public void setBz(BZ bz) {
		this.bz = bz;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public DocType getDocType() {
		return docType;
	}
	public void setDocType(DocType docType) {
		this.docType = docType;
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
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public TpInternet getTpInternet() {
		return tpInternet;
	}
	public void setTpInternet(TpInternet tpInternet) {
		this.tpInternet = tpInternet;
	}
	
	/* Methods */
	/* Bank */
	/**
	 * Create {@link igor.bts.entity.Bank} entity instance.
	 * @param bank {@link igor.bts.entity.Bank} POJO object
	 * @return created {@link igor.bts.entity.Bank} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException 
	 */
	public Bank createBank(Bank bank){
		return bankDAO.create(bank);
	}
	
	/** 
	 * Merge {@link igor.bts.entity.Bank} entity instance to database.
	 * @param bank {@link igor.bts.entity.Bank} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityEception
	 */
	public void updateBank(Bank bank){
		bankDAO.update(bank);
	}
	
	
	/** 
	 * Remove {@link igor.bts.entity.Bank} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.Bank} entity instance
	 * @return true if the removing is successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteBank(Integer id){
		return bankDAO.delete(id);
	}
	
	/**
	 * Get {@link igor.bts.entity.Bank} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.Bank} entity instance 
	 * @return {@link igor.bts.entity.Bank} entity instance or null if not exists
	 */
	public Bank getBank(Integer id){
		return bankDAO.getById(id);
	}
	
	/**
	 * Get all {@link igor.bts.entity.Bank} entity instances.
	 * @return List of all {@link igor.bts.entity.Bank} entity instances
	 * @throws CRUDEntityException
	 */
	public List<Bank> getAllBank(){
		return bankDAO.getAll();
	}
	
	/* Manager */
	/**
	 * Get {@link igor.bts.entity.Manager} entity instances by ID.
	 * @param id {@link igor.bts.entity.Manager} POJO object
	 * @return {@link igor.bts.entity.Manager} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public Manager getManager(Integer id){
		return managerDAO.getById(id);
	}
	
	/**
	 * Get of all {@link igor.bts.entity.Manager} entity instances.
	 * @return List of {@link igor.bts.entity.Manager} entity instances 
	 * @throws CRUDEntityException
	 */
	public List<Manager> getAllManager(){
		return managerDAO.getAll();
	}
	
	/* DocType */
	/**
	 * Get {@link igor.bts.entity.DocType} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.DocType} entity instance
	 * @return {@link igor.bts.entity.DocType} entity instance or null if not exists
	 */
	public DocType getDocType(Integer id){
		return docTypeDAO.getById(id);
	}
	
	/**
	 * Get all of {@link igor.bts.entity.DocType} entity instances.
	 * @return List of {@link igor.bts.entity.DocType} entity instances
	 * @throws CRUDEntityException
	 */
	public List<DocType> getAllDocType(){
		return docTypeDAO.getAll();
	}
	
	/* EdIzm */
	/**
	 * Get {@link igor.bts.entity.EdIzm} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.EdIzm} entity instance 
	 * @return {@link igor.bts.entity.EdIzm} entity instance of null if not exists
	 */
	public EdIzm getEdIzm(Integer id){
		return edIzmDAO.getById(id);
	}
	
	/**
	 * Get all of {@link igor.bts.entity.EdIzm} entity instances.
	 * @return List of all {@link igor.bts.entity.EdIzm} entity instances
	 * @throws CRUDEntityException
	 */
	public List<EdIzm> getAllEdIzm(){
		return edIzmDAO.getAll();
	}
	
	/* TpInternet */
	/** 
	 * Get {@link igor.bts.entity.TpInternet} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.TpInternet} entity instance
	 * @return {@link igor.bts.entity.TpInternet} entity instance or null if not exists
	 */
	public TpInternet getTpInternt(Integer id){
		return tpInternetDAO.getById(id);
	}
	
	/**
	 * Get all of {@link igor.bts.entity.TpInternet} entity instances.
	 * @return List of all {@link igor.bts.entity.TpInternet} entity instances
	 * @throws CRUDEntityException
	 */
	public List<TpInternet> getAllTpInternet(){
		return tpInternetDAO.getAll();
	}
	
	/* Client */
	/**
	 * Create {@link igor.bts.entity.Client} entity instance
	 * @param client {@link igor.bts.entity.Client} POJO object
	 * @return created {@link igor.bts.entity.Client} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public Client createClient(Client client){
		return clientDAO.create(client);
	}
	
	/** 
	 * Merge {@link igor.bts.entity.Client} entity instance to database
	 * @param client {@link igor.bts.entity.Client} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public void updateClient(Client client){
		clientDAO.update(client);
	}
	
	/**
	 * Remove an {@link igor.bts.entity.Client} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.Client} entity instance
	 * @return true if the removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteClient(Integer id){
		return clientDAO.delete(id);
	}
	
	/**
	 * Get {@link igor.bts.entity.Client} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.Client} entiy instance
	 * @return {@link igor.bts.entity.Client} entity instance or null if not exists
	 */
	public Client getClient(Integer id){
		return clientDAO.getById(id);
	}
	
	/**
	 * Get all of {@link igor.bts.entity.Client} entity instances.
	 * @return List of all {@link igor.bts.entity.Client} entity instances
	 * @throws CRUDEntityException
	 */
	public List<Client> getAllClient(){
		return clientDAO.getAll();
	}
	
	/* Dogovor */
	/**
	 * Create {@link igor.bts.entity.Dogovor} entity instance.
	 * @param dogovor {@link igor.bts.entity.Dogovor} POJO object
	 * @return created {@link igor.bts.entity.Dogovor} entity instance 
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public Dogovor createDogovor(Dogovor dogovor){
		return dogovorDAO.create(dogovor);
	}
	
	/**
	 * Merge {@link igor.bts.entity.Dogovor} entity instance to database
	 * @param dogovor {@link igor.bts.entity.Dogovor} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public void updateDogovor(Dogovor dogovor){
		dogovorDAO.update(dogovor);
	}
	
	/**
	 * Remove {@link igor.bts.entity.Dogovor} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.Dogovor} entity instance
	 * @return true if the removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteDogovor(Integer id){
		return dogovorDAO.delete(id);
	}
	
	/**
	 * Get {@link igor.bts.entity.Dogovor} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.Dogovor} entity instance
	 * @return {@link igor.bts.entity.Dogovor} entity instance or null if nots exists
	 */
	public Dogovor getDogovor(Integer id){
		return dogovorDAO.getById(id);
	}
	
	/**
	 * Get all of {@link igor.bts.entity.Dogovor} entity instances.
	 * @return List of all {@link igor.bts.entity.Dogovor} entity instances 
	 * @throws CRUDEntityException
	 */
	public List<Dogovor> getAllDogovor(){
		return dogovorDAO.getAll();
	}
	
	/* Bz */
	/**
	 * Create {@link igor.bts.entity.BZ} entity instance.
	 * @param bz {@link igor.bts.entity.BZ} POJO object
	 * @return {@link igor.bts.entity.BZ} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public BZ createBZ(BZ bz){
		return bzDAO.create(bz);
	}
	
	/**
	 * Merge {@link igor.bts.entity.BZ} entity instance to database.
	 * @param bz {@link igor.bts.entity.BZ} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public void updateBZ(BZ bz){
		bzDAO.update(bz);
	}
	
	/**
	 * Remove {@link igor.bts.entity.BZ} entity instance by ID
	 * @param id IF of {@link igor.bts.entity.BZ} entity instance
	 * @return true if the removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteBZ(Integer id){
		return bzDAO.delete(id);
	}
	
	/**
	 * Get {@link igor.bts.entity.BZ} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.BZ} entity instance
	 * @return {@link igor.bts.entity.BZ} entity instance or null if not exists
	 */
	public BZ getBZ(Integer id){
		return bzDAO.getById(id);
	}
	
	/**
	 * Get all of {@link igor.bts.entity.BZ} entity instances.
	 * @return List of all {@link igor.bts.entity.BZ} entity instances
	 * @throws CRUDEntityException
	 */
	public List<BZ> getAllBZ(){
		return bzDAO.getAll();
	}
	
	
	/* Service */
	/**
	 * Create {@link igor.bts.entity.Service} entity instance
	 * @param service {@link igor.bts.entity.Service} POJO object
	 * @return {@link igor.bts.entity.Service} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDEntityException
	 */
	public Service createService(Service service){
		return serviceDAO.create(service);
	}
	
	/**
	 * Merge {@link igor.bts.entity.Service} entity instance to database.
	 * @param service {@link igor.bts.entity.Service} entity instance
	 * @throws IllegalArgumentException
	 * @throws CRUDENtityException
	 */
	public void updateService(Service service){
		serviceDAO.update(service);
	}
	
	/**
	 * Remove {@link igor.bts.entity.Service} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.Service} entiy instance
	 * @return true if the removing was successful
	 * @throws CRUDEntityException
	 */
	public boolean deleteService(Integer id){
		return serviceDAO.delete(id);
	}
	
	/** 
	 * Get {@link igor.bts.entity.Service} entity instance by ID.
	 * @param id ID of {@link igor.bts.entity.Service} entity instance
	 * @return {@link igor.bts.entity.Service} entity instace ot null if not exists
	 */
	public Service getService(Integer id){
		return serviceDAO.getById(id);
	}
	
	/**
	 * Get all of {@link igor.bts.entity.Service} entity instances.
	 * @return List of all {@link igor.bts.entity.Service} entity instances
	 * @throws CRUDEnttiyException
	 */
	public List<Service> getAllService(){
		return serviceDAO.getAll();
	}
	
}
