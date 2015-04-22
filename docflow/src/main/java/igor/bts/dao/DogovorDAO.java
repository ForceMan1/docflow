package igor.bts.dao;

import igor.bts.CRUDEntityException;
import igor.bts.entity.Client;
import igor.bts.entity.DocType;
import igor.bts.entity.Dogovor;

import java.util.List;

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
 * Class for managing {@link igor.bts.entity.Dogovor} entity instances.
 *
 * @param Dogovor entity type
 */
//public class DogovorDAO implements IDogovorDAO<Dogovor> {
public class DogovorDAO extends IDogovorDAO {
	/**
	 * Entity manager for the JPA Provider
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
	 * Create new Dogovor entity instance.
	 * 
	 * @param bank {@link igor.bts.entity.Dogovor} POJO object for creating
	 * @throws CRUDEntityException causing by TransactionRequiredException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
	 * @throws IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return Dogovor entity instance
	 */
	@Override
	public Dogovor create(Dogovor dogovor) {
		try {
			DocType docType = dogovor.getType();
			if(docType != null)
				if(docType.getId() != null)
					em.merge(docType);
				else
					em.persist(docType);
			Client client = dogovor.getClient();
			if(client != null)
				if(client.getId() != null)
					if(client.getId() != null)
						em.merge(client);
					else
						em.persist(client);
			
			em.persist(dogovor);
			return dogovor;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Find Dogovor entity by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.Dogovor} instance
	 * @return true if the deleting result is successfull or false in a another case
	 * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public boolean delete(Integer id) {
		try {
			Dogovor dogovor = em.find(Dogovor.class, id);
			if(dogovor == null)
				return false;
			em.remove(dogovor);
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Update {@link igor.bts.entity.Dogovor} entity in the Persistence context.
	 * 
	 * @param bank {@link igor.bts.entity.Dogovor} entity
	 * @return the updated Dogovor entity
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws CRUDEntityExceptiion causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public Dogovor update(Dogovor dogovor) {
		try {
			return em.merge(dogovor);
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.Dogovor} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public Dogovor getById(Integer id) {
		Dogovor dogovor = em.find(Dogovor.class, id);
		return dogovor;
	}

	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.Dogovor} from the Peristence context.
	 *  @return List of all {@link igor.bts.entity.Dogovor} entity instances
	 *  @throws CRUDEntityException causing by any PersitenceException
	 */
	@Override
	public List<Dogovor> getAll() {
		try {
			TypedQuery<Dogovor> query = em.createNamedQuery(Dogovor.ALL_DOGOVOR, Dogovor.class);
			return (List<Dogovor>)query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e); 
		}
	}
}
