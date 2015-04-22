package igor.bts.dao;

import igor.bts.CRUDEntityException;
import igor.bts.entity.TpInternet;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

/**
 * 
 * 
 * @author Igor
 * Class for managing {@link igor.bts.entity.TpInternet} entity instances.
 *
 * @param TpInternet entity type
 */
//public class TpInternetDAO implements ITpInternetDAO<TpInternet> {
public class TpInternetDAO extends ITpInternetDAO {
	/**
	 * Entity Manager for the JPA Provider.
	 */
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	/**
	 * Set Entity Manager.
	 * @param em Entity manager
	 */
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	/**
	 * Create new TpInternet entity instance.
	 * 
	 * @param tpInternet {@link igor.bts.entity.TpInternet} POJO object for creating
	 * @throws TransactionRequiredException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
	 * @throws CRUDEntityException causing by an IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return bank entity instance
	 */
	@Override
	public TpInternet create(TpInternet tpInternet) {
		try {
			em.persist(tpInternet);
			return tpInternet;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Find TpInternet entity instance by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.TpInternet} instance
	 * @return true if the deleting result is successfull or false in a another case
	 * @throws CRUDEntityException caisng by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public boolean delete(Integer  id) {
		try {
			TpInternet tpInternet = em.find(TpInternet.class, id);
			if(tpInternet == null)
				return false;
			em.remove(tpInternet);
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Update {@link igor.bts.entity.TpInternet} entity in the Persistence context.
	 * 
	 * @param bank {@link igor.bts.entity.TpInternet} entity
	 * @return the updated TpInternet entity instance
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public TpInternet update(TpInternet tpInternet) {
		try {
			return em.merge(tpInternet);
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.TpInternet} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public TpInternet getById(Integer id) {
		return em.find(TpInternet.class, id);	
	}

	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.TpInternet} from the Persistence context.
	 *  @return List of all {@link igor.bts.entity.TpInternet} entity instances
	 *  @throws CRUDEntityException causing by any PersistenceException
	 */
	@Override
	public List<TpInternet> getAll() {
		try {
			TypedQuery<TpInternet> query = em.createNamedQuery(TpInternet.ALL_TP, TpInternet.class);
			return query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

}
