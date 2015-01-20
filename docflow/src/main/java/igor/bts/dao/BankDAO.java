package igor.bts.dao;

import igor.bts.entity.Bank;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Produces;
@Dependent
//@BankQualifier
public class BankDAO implements IBankDAO<Bank> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}

	@Override
	public Bank create(Bank bank) {
		em.persist(bank);
		return bank;
	}

	@Override
	public void delete(Integer id) {
		Bank bank = em.find(Bank.class, id);
		em.remove(bank);
	}

	@Override
	public void update(Bank bank) {
		em.merge(bank);
	}

	@Override
	public Bank getById(Integer id) {
		Bank bank = em.find(Bank.class, id);
		return bank;
	}

	@Override
	public List<Bank> getAll() {
		TypedQuery<Bank> query = em.createNamedQuery(Bank.ALL_BANK, Bank.class);
		return query.getResultList();
	}

	

}
