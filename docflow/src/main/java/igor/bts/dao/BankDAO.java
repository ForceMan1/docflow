package igor.bts.dao;

import igor.bts.CRUDEntityException;

import igor.bts.entity.Bank;

import java.util.List;
import java.util.NoSuchElementException;

import javax.enterprise.context.Dependent;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.ws.rs.Produces;

/**
 * 
 * 
 * @author Igor
 * Class for managing {@link igor.bts.entity.Bank} entity instances.
 *
 * @param Bank Entity type
 */

@Dependent
//@BankQualifier
//public class BankDAO<T extends Bank> implements IBankDAO<T> {
public class BankDAO extends IBankDAO {
	/**
	 * EntityManager for JPA Provider.
	 */
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	/**
	 * Set EntityManager
	 * @param em EntityManager
	 */
	public void setEm(EntityManager em){
		this.em = em;
	}

	/**
	 * Create new Bank entity instance.
	 * 
	 * @param bank {@link igor.bts.entity.Bank} POJO object for creating
	 * @throws CRUDEntityException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
     *         or if the entity already exists
     *         or by or another <code>PersistenceException</code
	 * @throws IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return bank entity instance
	 */
	@Override
	public Bank create(Bank bank) {
		try {
			em.persist(bank);
			return bank;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
			
	}

	/**
	 * Find Bank entity by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.Bank} instance
	 * @return true if the deleting result is successful or false in a another case
	 * @throws CRUDEntityException causing by an ETransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public boolean delete(Integer id) {
		try {
			Bank bank = em.find(Bank.class, id);
			if(bank == null)
				return false;
			em.remove(bank);
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Update {@link igor.bts.entity.Bank} entity in the Persistence context.
	 * 
	 * @param bank {@link igor.bts.entity.Bank} entity
	 * @return the updated Bank entity
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws CRUDEntityException causing by TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public Bank update(Bank bank) {
		try {
			return em.merge(bank);
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.Bank} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public Bank getById(Integer id) {
		Bank bank = em.find(Bank.class, id);
		return bank;
	}

	
	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.Bank} from the Peristence context.
	 *  @return List of all {@link igor.bts.entity.Bank} entity instances
	 *  @throws CRUDEntityException causing by any PersistenceException
	 */
	@Override
	public List<Bank> getAll() {
		try {
			TypedQuery<Bank> query = em.createNamedQuery(Bank.ALL_BANK, Bank.class);
			return query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}
}
