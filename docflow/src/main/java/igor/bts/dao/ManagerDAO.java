package igor.bts.dao;

import igor.bts.CRUDEntityException;
import igor.bts.entity.Manager;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

/**
 * 
 * 
 * @author Igor
 * Class for managing {@link igor.bts.entity.Manager} entity instances.
 *
 * @param Manager entity type
 */

//public class ManagerDAO implements IManagerDAO<Manager> {
public class ManagerDAO extends IManagerDAO {
	/**
	 * Entity manager for the JPA Provider.
	 */
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	/**
	 * Set Entity manager.
	 * @param em Entity manager
	 */
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	/**
	 * Create new Manager entity instance.
	 * 
	 * @param manager {@link igor.bts.entity.Manager} POJO object for creating
	 * @throws CRUDEntityException causing by an TransactionRequiredException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
	 * @throws IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return Manager entity instance
	 */
	@Override
	public Manager create(Manager manager) {
		try {
			em.persist(manager);
			return manager;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Find Manager entity by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.Manager} instance
	 * @return true if the deleting result is successfull or false in a another case
	 * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public boolean delete(Integer id) {
		try {
			Manager manager = em.find(Manager.class, id);
			if(manager == null)
				return false;
			em.remove(manager);
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Update {@link igor.bts.entity.Manager} entity in the Persistence context.
	 * 
	 * @param manager {@link igor.bts.entity.Manager} entity
	 * @return the updated Manager entity
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public Manager update(Manager manager) {
		try {
			return em.merge(manager);
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.Manager} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public Manager getById(Integer id) {
		Manager manager = em.find(Manager.class, id);
		return manager;
	}

	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.Manager} from the Peristence context.
	 *  @return List of all {@link igor.bt.entity.Manager} entity instances
	 *  @throws CRUDEntityException causing by any PeristenceException
	 */
	@Override
	public List<Manager> getAll() {
		try {
			Query query = em.createNamedQuery(Manager.ALL_MANAGER);
			return (List<Manager>)query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e); 
		}
	}
}
