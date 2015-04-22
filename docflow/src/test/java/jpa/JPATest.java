package jpa;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import igor.bts.Podpisant;
import igor.bts.dao.BZDAO;
import igor.bts.dao.BankDAO;
import igor.bts.dao.ClientDAO;
import igor.bts.dao.DocTypeDAO;
import igor.bts.dao.DogovorDAO;
import igor.bts.dao.EdIzmDAO;
import igor.bts.dao.IBankDAO;
import igor.bts.dao.ManagerDAO;
import igor.bts.dao.ServiceDAO;
import igor.bts.dao.TpInternetDAO;
import igor.bts.entity.BZ;
import igor.bts.entity.Bank;
import igor.bts.entity.Client;
import igor.bts.entity.DocType;
import igor.bts.entity.Dogovor;
import igor.bts.entity.EdIzm;
import igor.bts.entity.Manager;
import igor.bts.entity.Service;
import igor.bts.entity.TpInternet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JPATest {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test1");
	private static EntityManager em;
	private static EntityTransaction tx;
	private static ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	private static Validator validator;
	
	
	@BeforeClass
	public static void initEntityManager() throws Exception {
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}
	
	@BeforeClass
	public static void createValidator(){
		validator = vf.getValidator();
	}
	
	@AfterClass
	public static void closeEntityManager(){
		em.close();
		emf.close();
	}
	
	@AfterClass
	public static void closeValidator(){
		vf.close();
	}

	@Test
	public void testBank(){
		BankDAO bankDAO = new BankDAO();
		bankDAO.setEm(em);
		// Create bank
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		bank = bankDAO.create(bank);
		assertTrue(bank.getName() == "TestBank" && bank.getBik() == "111111111" && 
				bank.getOkpo() == "2222222222" && bank.getId() > 0);
		// Get bank
		bank = bankDAO.getById(bank.getId());
		assertTrue(bank.getName() == "TestBank" && bank.getBik() == "111111111" && 
				bank.getOkpo() == "2222222222" && bank.getId() > 0);
		// Delete bank
		bankDAO.delete(bank.getId());
		bank = bankDAO.getById(bank.getId());
		assertTrue(bank == null);
		Bank bank1 = new Bank("TestBank", "111111111", null);
		Bank bank2 = new Bank("TestBank", "111111111", null);
		Bank bank3 = new Bank("TestBank", "111111111", null);
		tx.begin();
		bankDAO.create(bank1);
		System.out.println(bank1.getId());
		bankDAO.create(bank2);
		System.out.println(bank2.getId());
		bankDAO.create(bank3);
		tx.commit();
		System.out.println(bank3.getId());
		System.out.println(bankDAO.getAll().size());
		assertEquals(bankDAO.getAll().size(), 3);
		TypedQuery<Bank> query = em.createNamedQuery(Bank.ALL_BANK, Bank.class); 
		System.out.println(query.getResultList().size());
		
		tx.begin();
		bankDAO.delete(bank1.getId());
		bankDAO.delete(bank2.getId());
		bankDAO.delete(bank3.getId());
		tx.commit();
	}
	
	@Test
	public void testDocType(){
		DocTypeDAO docTypeDAO = new DocTypeDAO();
		docTypeDAO.setEm(em);
		
		DocType docType = new DocType("name1", "info", 0, (short)0);
		tx.begin();
		docTypeDAO.create(docType);
		tx.commit();
		assertTrue(docType.getId()!= 0);
		
		docType = docTypeDAO.getById(docType.getId());
		assertTrue(docType != null);
		
		tx.begin();
		docTypeDAO.delete(docType.getId());
		tx.commit();
		
		assertTrue(docTypeDAO.getById(docType.getId()) == null);
		
		DocType d1 = new DocType("name", "fff", 0, (short)0);
		DocType d2 = new DocType("name", "fff", 0, (short)0);
		DocType d3 = new DocType("name", "fff", 0, (short)0);
		
		tx.begin();
		docTypeDAO.create(d1);
		docTypeDAO.create(d2);
		docTypeDAO.create(d3);
		tx.commit();
		assertEquals(docTypeDAO.getAll().size(), 3);
		
		tx.begin();
		docTypeDAO.delete(d1.getId());
		docTypeDAO.delete(d2.getId());
		docTypeDAO.delete(d3.getId());
		tx.commit();
	}
	
	@Test
	public void testEdIzm(){
		EdIzmDAO edIzmDAO = new EdIzmDAO();
		edIzmDAO.setEm(em);
		EdIzm edIzm = new EdIzm("name", "info");
		
		tx.begin();
		edIzmDAO.create(edIzm);
		tx.commit();
		assertTrue(edIzm.getId() != null);
		
		edIzm = edIzmDAO.getById(edIzm.getId());
		assertTrue(edIzm != null);
		
		tx.begin();
		edIzmDAO.delete(edIzm.getId());
		tx.commit();
		edIzm = edIzmDAO.getById(edIzm.getId());
		assertTrue(edIzm == null);
		
		EdIzm e1 = new EdIzm("e", "i");
		EdIzm e2 = new EdIzm("e", "i");
		EdIzm e3 = new EdIzm("e", "i");
		
		tx.begin();
		edIzmDAO.create(e1);
		edIzmDAO.create(e2);
		edIzmDAO.create(e3);
		tx.commit();
		assertEquals(edIzmDAO.getAll().size(), 3);
		
		tx.begin();
		edIzmDAO.delete(e1.getId());
		edIzmDAO.delete(e2.getId());
		edIzmDAO.delete(e3.getId());
		tx.commit();
	}
	
	@Test
	public void testManager(){
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.setEm(em);
		
		Manager manager = new Manager("test", "test");
		tx.begin();
		managerDAO.create(manager);
		tx.commit();
		assertTrue(manager.getId() != null);
		
		tx.begin();
		managerDAO.delete(manager.getId());
		tx.commit();
		manager = managerDAO.getById(manager.getId());
		assertEquals(manager, null);
		
		Manager m1 = new Manager("w", "w");
		Manager m2 = new Manager("e", "e");
		Manager m3 = new Manager("f", "f");
		
		tx.begin();
		managerDAO.create(m1);
		managerDAO.create(m2);
		managerDAO.create(m3);
		tx.commit();
		assertEquals(managerDAO.getAll().size(), 3);
		
		tx.begin();
		managerDAO.delete(m1.getId());
		managerDAO.delete(m2.getId());
		managerDAO.delete(m3.getId());
		tx.commit();
	}
	
	@Test
	public void testTpInternet(){
		TpInternetDAO tpInternetDAO = new TpInternetDAO();
		tpInternetDAO.setEm(em);
		
		TpInternet tpInternet = new TpInternet(new BigDecimal(1), new BigDecimal(1),
				(byte)1, new BigDecimal(1), true, new BigDecimal(1));
		tx.begin();
		tpInternetDAO.create(tpInternet);
		tx.commit();
		assertTrue(tpInternet.getId() != null);
		
		tpInternet = tpInternetDAO.getById(tpInternet.getId());
		assertTrue(tpInternet != null);
		
		tx.begin();
		tpInternetDAO.delete(tpInternet.getId());
		tx.commit();
		tpInternet = tpInternetDAO.getById(tpInternet.getId());
		assertEquals(tpInternet, null);
		
		TpInternet t1 = new TpInternet(new BigDecimal(0), new BigDecimal(0),
				(byte)0, new BigDecimal(0), true, new BigDecimal(0));
		TpInternet t2 = new TpInternet(new BigDecimal(0), new BigDecimal(0),
				(byte)0, new BigDecimal(0), true, new BigDecimal(0));
		TpInternet t3 = new TpInternet(new BigDecimal(0), new BigDecimal(0),
				(byte)0, new BigDecimal(0), true, new BigDecimal(0));
		tx.begin();
		tpInternetDAO.create(t1);
		tpInternetDAO.create(t2);
		tpInternetDAO.create(t3);
		tx.commit();
		assertEquals(tpInternetDAO.getAll().size(), 3);
		
		tx.begin();
		tpInternetDAO.delete(t1.getId());
		tpInternetDAO.delete(t2.getId());
		tpInternetDAO.delete(t3.getId());
		tx.commit();
	}
	
	@Test
	public void testClient(){
		ClientDAO clientDAO = new ClientDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		BankDAO bankDAO = new BankDAO();
		bankDAO.setEm(em);
		managerDAO.setEm(em);
		clientDAO.setEm(em);
		
		Manager manager = new Manager("test", "test");
				
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio", "dolzhnost", "", "", null, "");
		
		tx.begin();
		bankDAO.create(bank);
		managerDAO.create(manager);
		tx.commit();
		
		Client client = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "123456789011", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		Set<ConstraintViolation<Client>> violations = validator.validate(client);
		assertEquals(violations.size(), 0);
		tx.begin();
		client = clientDAO.create(client);
		tx.commit();
		assertTrue(client.getId() != null);
		
		client = clientDAO.getById(client.getId());
		assertTrue(client != null);
		
		tx.begin();
		clientDAO.delete(client.getId());
		tx.commit();
		client = clientDAO.getById(client.getId());
		assertEquals(client, null);
		
		Client c1 = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		Client c2 = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		Client c3 = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		tx.begin();
		clientDAO.create(c1);
		clientDAO.create(c2);
		clientDAO.create(c3);
		tx.commit();
		assertEquals(clientDAO.getAll().size(), 3);
		
		tx.begin();
		clientDAO.delete(c1.getId());
		clientDAO.delete(c2.getId());
		clientDAO.delete(c3.getId());
		managerDAO.delete(manager.getId());
		bankDAO.delete(bank.getId());
		tx.commit();
	}
	
	@Test
	public void testDogovor(){
		DogovorDAO dogovorDAO = new DogovorDAO();
		dogovorDAO.setEm(em);
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.setEm(em);
		DocTypeDAO docTypeDAO = new DocTypeDAO();
		docTypeDAO.setEm(em);
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.setEm(em);
		BankDAO bankDAO = new BankDAO();
		bankDAO.setEm(em);
		DocType docType = new DocType("name", "info", 0, (short)0);
		
		
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio", "dolzhnost", "", "", null, "");
		tx.begin();
		manager = managerDAO.create(manager);
		bank = bankDAO.create(bank);
		tx.commit();
		
		Client client = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		tx.begin();
		clientDAO.create(client);
		docTypeDAO.create(docType);
		tx.commit();
		
		Dogovor dogovor = new Dogovor(docType, "13/011", new Date(), new Date(), "info", false, null, 
				client, null);
		tx.begin();
		dogovorDAO.create(dogovor);
		tx.commit();
		assertTrue(dogovor.getId() != null);
		
		dogovor = dogovorDAO.getById(dogovor.getId());
		assertTrue(dogovor != null);
		
		tx.begin();
		dogovorDAO.delete(dogovor.getId());
		tx.commit();
		dogovor = dogovorDAO.getById(dogovor.getId());
		assertEquals(dogovor, null);
		
		Dogovor d1 = new Dogovor(docType, "13/011", new Date(), new Date(), "info", false, null, 
				client, null);
		Dogovor d2 = new Dogovor(docType, "13/012", new Date(), new Date(), "info", false, null, 
				client, null);
		Dogovor d3 = new Dogovor(docType, "13/013", new Date(), new Date(), "info", false, null, 
				client, null);
		tx.begin();
		dogovorDAO.create(d1);
		dogovorDAO.create(d2);
		dogovorDAO.create(d3);
		tx.commit();
		assertEquals(dogovorDAO.getAll().size(), 3);
		
		tx.begin();
		dogovorDAO.delete(d1.getId());
		dogovorDAO.delete(d2.getId());
		dogovorDAO.delete(d3.getId());
		managerDAO.delete(manager.getId());
		bankDAO.delete(bank.getId());
		clientDAO.delete(client.getId());
		docTypeDAO.delete(docType.getId());
		tx.commit();
	}
	
	@Test
	public void testBZ(){
		DogovorDAO dogovorDAO = new DogovorDAO();
		dogovorDAO.setEm(em);
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.setEm(em);
		DocTypeDAO docTypeDAO = new DocTypeDAO();
		docTypeDAO.setEm(em);
		DocType docType = new DocType("name", "info", 0, (short)0);
		BZDAO bzDAO = new BZDAO();
		bzDAO.setEm(em);
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.setEm(em);
		BankDAO bankDAO = new BankDAO();
		bankDAO.setEm(em);
		
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio", "dolzhnost", "", "", null, "");
		tx.begin();
		bank = bankDAO.create(bank);
		manager = managerDAO.create(manager);
		assertTrue(manager.getId() != null);
		tx.commit();
		
		Client client = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		tx.begin();
		clientDAO.create(client);
		docTypeDAO.create(docType);
		tx.commit();
		
		Dogovor dogovor = new Dogovor(docType, "13/011", new Date(), new Date(), "info", false, null, 
				client, null);
		tx.begin();
		dogovorDAO.create(dogovor);
		tx.commit();
		
		
		BZ bz = new BZ(docType, "1", new Date(), new Date(), new  Date(), "1024 Kbits/s",
				"2299499", true, "ул.Ленина, 28", "comment", false, null, dogovor, null );
		tx.begin();
		bz = bzDAO.create(bz);
		tx.commit();
		assertTrue(bz.getId() != null);
		
		bz = bzDAO.getById(bz.getId());
		assertTrue(bz != null);
		
		tx.begin();
		bzDAO.delete(bz.getId());
		tx.commit();
		bz = bzDAO.getById(bz.getId());
		assertEquals(bz, null);
		
		BZ b1 = new BZ(docType, "1", new Date(), new Date(), new  Date(), "1024 Kbits/s",
				"2299499", true, "ул.Ленина, 28", "comment", false, null, dogovor, null );
		BZ b2 = new BZ(docType, "1", new Date(), new Date(), new  Date(), "1024 Kbits/s",
				"2299499", true, "ул.Ленина, 28", "comment", false, null, dogovor, null );
		BZ b3 = new BZ(docType, "1", new Date(), new Date(), new  Date(), "1024 Kbits/s",
				"2299499", true, "ул.Ленина, 28", "comment", false, null, dogovor, null );
		tx.begin();
		bzDAO.create(b1);
		bzDAO.create(b2);
		bzDAO.create(b3);
		tx.commit();
		assertEquals(bzDAO.getAll().size(), 3);
		
		tx.begin();
		bzDAO.delete(b1.getId());
		bzDAO.delete(b2.getId());
		bzDAO.delete(b3.getId());
		dogovorDAO.delete(dogovor.getId());
		clientDAO.delete(client.getId());
		managerDAO.delete(manager.getId());
		bankDAO.delete(bank.getId());
		docTypeDAO.delete(docType.getId());
		tx.commit();
	}
	
	@Test
	public void testService(){
		ServiceDAO serviceDAO = new ServiceDAO();
		serviceDAO.setEm(em);
		DogovorDAO dogovorDAO = new DogovorDAO();
		dogovorDAO.setEm(em);
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.setEm(em);
		DocTypeDAO docTypeDAO = new DocTypeDAO();
		docTypeDAO.setEm(em);
		DocType docType = new DocType("name", "info", 0, (short)0);
		BZDAO bzDAO = new BZDAO();
		bzDAO.setEm(em);
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.setEm(em);
		BankDAO bankDAO = new BankDAO();
		bankDAO.setEm(em);
		EdIzmDAO edIzmDAO = new EdIzmDAO();
		edIzmDAO.setEm(em);
		TpInternetDAO tpInternetDAO = new TpInternetDAO();
		tpInternetDAO.setEm(em);
				
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio", "dolzhnost", "", "", null, "");
		tx.begin();
		bank = bankDAO.create(bank);
		manager = managerDAO.create(manager);
		assertTrue(manager.getId() != null);
		tx.commit();
		
		Client client = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		tx.begin();
		clientDAO.create(client);
		docTypeDAO.create(docType);
		tx.commit();
		
		Dogovor dogovor = new Dogovor(docType, "13/011", new Date(), new Date(), "info", false, null, 
				client, null);
		tx.begin();
		dogovorDAO.create(dogovor);
		tx.commit();
		
		BZ bz = new BZ(docType, "1", new Date(), new Date(), new  Date(), "1024 Kbits/s",
				"2299499", true, "ул.Ленина, 28", "comment", false, null, dogovor, null );
		tx.begin();
		bz = bzDAO.create(bz);
		tx.commit();
		
		
		EdIzm edIzm = new EdIzm("name", "info");
		tx.begin();
		edIzm = edIzmDAO.create(edIzm);
		tx.commit();
		
		TpInternet tpInternet = new TpInternet(new BigDecimal(0), new BigDecimal(0),
				(byte)0, new BigDecimal(0), true, new BigDecimal(0));
		tx.begin();
		tpInternet = tpInternetDAO.create(tpInternet);
		tx.commit();
		
		Service service = new Service(docType, true, edIzm, new BigDecimal(2000),
				new BigDecimal(2), new BigDecimal(4000), tpInternet, new HashSet(), bz);
		tx.begin();
		service = serviceDAO.create(service);
		tx.commit();
		assertTrue(service.getId() != null);
		
		service = serviceDAO.getById(service.getId());
		assertTrue(service != null);
		
		tx.begin();
		serviceDAO.delete(service.getId());
		tx.commit();
		service = serviceDAO.getById(service.getId());
		assertEquals(service, null);
		
		Service s1 = new Service(docType, true, edIzm, new BigDecimal(2000),
				new BigDecimal(2), new BigDecimal(4000), tpInternet, new HashSet(), bz);
		Service s2 = new Service(docType, true, edIzm, new BigDecimal(2000),
				new BigDecimal(2), new BigDecimal(4000), tpInternet, new HashSet(), bz);
		Service s3 = new Service(docType, true, edIzm, new BigDecimal(2000),
				new BigDecimal(2), new BigDecimal(4000), tpInternet, new HashSet(), bz);
		tx.begin();
		serviceDAO.create(s1);
		serviceDAO.create(s2);
		serviceDAO.create(s3);
		tx.commit();
		assertEquals(serviceDAO.getAll().size(), 3);
		
		tx.begin();
		serviceDAO.delete(s1.getId());
		serviceDAO.delete(s2.getId());
		serviceDAO.delete(s3.getId());
		bzDAO.delete(bz.getId());
		dogovorDAO.delete(dogovor.getId());
		clientDAO.delete(client.getId());
		tpInternetDAO.delete(tpInternet.getId());
		edIzmDAO.delete(edIzm.getId());
		managerDAO.delete(manager.getId());
		bankDAO.delete(bank.getId());
		docTypeDAO.delete(docType.getId());
		tx.commit();
	}
}
