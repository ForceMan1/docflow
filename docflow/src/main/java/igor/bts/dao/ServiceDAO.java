package igor.bts.dao;

import igor.bts.CRUDEntityException;
import igor.bts.entity.BZ;
import igor.bts.entity.DocType;
import igor.bts.entity.Service;
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
 * Class for managing {@link igor.bts.entity.Service} entity instances.
 *
 * @param Service entity type
 */

//public class ServiceDAO implements IServiceDAO<Service> {
public class ServiceDAO extends IServiceDAO {
	/**
	 * Entity Manager for the JPA Provider.
	 */
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	/**
	 * Set Entity Manager.
	 * @param em EntityManager
	 */
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	/**
	 * Create new Service entity instance.
	 * 
	 * @param bank {@link igor.bts.entity.Service} POJO object for creating
	 * @throws CRUDEntityExceptio ncausing by an TransactionRequiredException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
	 * @throws IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return Service entity instance
	 */
	@Override
	public Service create(Service service) {
		try {
			DocType docType = service.getType();
			if(docType != null)
				if(docType.getId() != null)
					em.merge(docType);
				else
					em.persist(docType);
			TpInternet tp = service.getTpInternet();
			if(tp != null)
				if(tp.getId() != null)
					em.merge(tp);
				else
					em.persist(tp);
			BZ bz = service.getBz();
			if(bz != null)
				if(bz.getId() != null)
					em.merge(bz);
				else
					em.persist(bz);
			em.persist(service);
			return service;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Find Service entity by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.Service} instance
	 * @return true if the deleting result is successfull or false in a another case
	 * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public boolean delete(Integer id) {
		try {
			Service service = em.find(Service.class, id);
			if(service == null)
				return false;
			em.remove(service);
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Update {@link igor.bts.entity.Service} entity in the Persistence context.
	 * 
	 * @param service {@link igor.bts.entity.Service} entity
	 * @return the updated Service entity instance
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public Service update(Service service) {
		try {
			return em.merge(service);
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.Service} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public Service getById(Integer id) {
		Service service = em.find(Service.class, id);
		return service;
	}

	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.Service} from the Peristence context.
	 *  @return List of all {@link igor.bts.entity.Service} entity instances
	 *  @throws CRUDEntityException causing by any PeristenceException
	 */
	@Override
	public List<Service> getAll() {
		try {
			TypedQuery<Service> query = em.createNamedQuery(Service.ALL_SERVICE, Service.class);
			return query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

}
