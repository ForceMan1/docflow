package igor.bts.dao;

import igor.bts.CRUDEntityException;
import igor.bts.entity.Bank;
import igor.bts.entity.Client;
import igor.bts.entity.Manager;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

/**
 * 
 * 
 * @author Igor
 * Class for managing {@link igor.bts.entity.Client} entity instances.
 *
 * @param Client Entity type
 */

//public class ClientDAO implements IClientDAO<Client> {
public class ClientDAO extends IClientDAO {
	/**
	 * EntityManager for JPA Provider.
	 */
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	/**
	 * Set Entity manager.
	 * 
	 * @param em Entity manager
	 */
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	/**
	 * Create new Client instance.
	 * 
	 * @param client {@link igor.bts.entity.Client} POJO object for creating
	 * @throws CRUDEntityException causing by TransactionRequiredException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
	 * @throws IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return Client entity instance
	 */	
	@Override
	public Client create(Client client) {
		try {
			Bank bank = client.getBank();
			if(client.getBank() != null){
				if(client.getBank().getId() != null)
					bank = em.merge(bank);
				else 
					em.persist(bank);
			}
			Manager manager = client.getManager();
			if(client.getManager() != null){
				if(client.getManager().getId() != null)
					manager = em.merge(manager);
				else
					em.persist(manager);
			}
			client.setBank(bank);
			client.setManager(manager);
			em.persist(client);
			em.flush();
			return client;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Find Client entity by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.Client} instance
	 * @return true if the deleting result is successfull or false in a another case
	 * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */	
	@Override
	public boolean delete(Integer id) {
		try {
			Client client = em.find(Client.class, id);
			if(client == null)
				return false;
			em.remove(client);
			em.flush();
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Update {@link igor.bts.entity.Client} entity in the Persistence context.
	 * 
	 * @param bank {@link igor.bts.entity.Client} entity
	 * @return the updated Client entity instance
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public Client update(Client client) {
		try {
			em.merge(client);
			em.flush();
			return client;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.Client} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public Client getById(Integer id) {
		Client client = em.find(Client.class, id);
		return client;
	}

	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.Client} from the Peristence context.
	 *  @return List of all {@link igor.bts.entity.Client} entity instances
	 *  @throws CRUDEntityException causing by any of PersistenceException
	 */
	@Override
	public List<Client> getAll() {
		try {
			TypedQuery<Client> query = em.createNamedQuery(Client.ALL_CLIENT, Client.class);
			return (List<Client>)query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}
	
}
