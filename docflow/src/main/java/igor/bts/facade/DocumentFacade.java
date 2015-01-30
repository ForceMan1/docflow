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

@Stateless
public class DocumentFacade {
	@Inject
	private IClientDAO<Client> clientDAO; 
	@Inject
	private IDogovorDAO<Dogovor> dogovorDAO;
	@Inject
	private IBZDAO<BZ> bzDAO;
	@Inject
	private IServiceDAO<Service> serviceDAO;
	@Inject
	private IDocTypeDAO<DocType> docTypeDAO;
	@Inject
	private IEdIzmDAO<EdIzm> edIzmDAO;
	@Inject
	private IManagerDAO<Manager> managerDAO;
	@Inject
	private ITpInternetDAO<TpInternet> tpInternetDAO;
	@Inject
	private IBankDAO<Bank> bankDAO;
	
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
	public IClientDAO<Client> getClientDAO() {
		return clientDAO;
	}
	public void setClientDAO(IClientDAO<Client> clientDAO) {
		this.clientDAO = clientDAO;
	}
	public IDogovorDAO<Dogovor> getDogovorDAO() {
		return dogovorDAO;
	}
	public void setDogovorDAO(IDogovorDAO<Dogovor> dogovorDAO) {
		this.dogovorDAO = dogovorDAO;
	}
	public IBZDAO<BZ> getBzDAO() {
		return bzDAO;
	}
	public void setBzDAO(IBZDAO<BZ> bzDAO) {
		this.bzDAO = bzDAO;
	}
	public IServiceDAO<Service> getServiceDAO() {
		return serviceDAO;
	}
	public void setServiceDAO(IServiceDAO<Service> serviceDAO) {
		this.serviceDAO = serviceDAO;
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
	
	/* Manager */
	public Manager getManager(Integer id){
		return managerDAO.getById(id);
	}
	
	public List<Manager> getAllManager(){
		return managerDAO.getAll();
	}
	
	/* DocType */
	public DocType getDocType(Integer id){
		return docTypeDAO.getById(id);
	}
	
	public List<DocType> getAllDocType(){
		return docTypeDAO.getAll();
	}
	
	/* EdIzm */
	public EdIzm getEdIzm(Integer id){
		return edIzmDAO.getById(id);
	}
	
	public List<EdIzm> getAllEdIzm(){
		return edIzmDAO.getAll();
	}
	
	/* TpInternet */
	public TpInternet getTpInternt(Integer id){
		return tpInternetDAO.getById(id);
	}
	
	public List<TpInternet> getAllTpInternet(){
		return tpInternetDAO.getAll();
	}
	
	/* Client */
	public Client createClient(Client client){
		return clientDAO.create(client);
	}
	
	public void updateClient(Client client){
		clientDAO.update(client);
	}
	
	public void deleteClient(Integer id){
		clientDAO.delete(id);
	}
	
	public Client getClient(Integer id){
		return clientDAO.getById(id);
	}
	
	public List<Client> getAllClient(){
		return clientDAO.getAll();
	}
	
	/* Dogovor */
	public Dogovor createDogovor(Dogovor dogovor){
		return dogovorDAO.create(dogovor);
	}
	
	public void updateDogovor(Dogovor dogovor){
		dogovorDAO.update(dogovor);
	}
	
	public void deleteDogovor(Integer id){
		dogovorDAO.delete(id);
	}
	
	public Dogovor getDogovor(Integer id){
		return dogovorDAO.getById(id);
	}
	
	public List<Dogovor> getAllDogovor(){
		return dogovorDAO.getAll();
	}
	
	/* Bz */
	public BZ createBZ(BZ bz){
		return bzDAO.create(bz);
	}
	
	public void updateBZ(BZ bz){
		bzDAO.update(bz);
	}
	
	public void deleteBZ(Integer id){
		bzDAO.delete(id);
	}
	
	public BZ getBZ(Integer id){
		return bzDAO.getById(id);
	}
	
	public List<BZ> getAllBZ(){
		return bzDAO.getAll();
	}
	
	
	/* Service */
	public Service createService(Service service){
		return serviceDAO.create(service);
	}
	
	public void updateService(Service service){
		serviceDAO.update(service);
	}
	
	public void deleteService(Integer id){
		serviceDAO.delete(id);
	}
	
	public Service getService(Integer id){
		return serviceDAO.getById(id);
	}
	
	public List<Service> getAllService(){
		return serviceDAO.getAll();
	}
	
}
