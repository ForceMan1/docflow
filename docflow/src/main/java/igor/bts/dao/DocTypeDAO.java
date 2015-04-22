package igor.bts.dao;

import igor.bts.CRUDEntityException;
import igor.bts.entity.DocType;

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
 * Class for managing {@link igor.bts.entity.DocType} entity instances.
 *
 * @param DocType Entity type
 */

//public class DocTypeDAO implements IDocTypeDAO<DocType> {
public class DocTypeDAO extends IDocTypeDAO {
	/**
	 * Entity manager for JPA Provider.
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
	 * Create new DocType instance.
	 * 
	 * @param bank {@link igor.bts.entity.DocType} POJO object for creating
	 * @throws CRUDEntityException caussing by TransactionRequiredException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
	 * @throws IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return DocType entity instance
	 */
	@Override
	public DocType create(DocType t) {
		try {
			em.persist(t);
			return t;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Find DocType entity by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.DocType} instance
	 * @return true if the deleting result is successfull or false in a another case
	 * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public boolean delete(Integer id) {
		try {
			DocType docType = em.find(DocType.class, id);
			if(docType == null)
				return false;
			em.remove(docType);
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e); 
		}
	}

	/**
	 * Update {@link igor.bts.entity.DocType} entity in the Persistence context.
	 * 
	 * @param bank {@link igor.bts.entity.DocType} entity
	 * @return the updated DocType entity instance
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public DocType update(DocType docType) {
		try {
			return em.merge(docType);
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.DocType} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public DocType getById(Integer id) {
		DocType docType = em.find(DocType.class, id);
		return docType;
	}

	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.DocType} from the Peristence context.
	 *  @return List of all {@link igor.bts.entity.DocType} entity instances
	 *  @throws CRUDEntityException causing by any PeristenceException
	 */
	@Override
	public List<DocType> getAll() {
		try {
			TypedQuery<DocType> query = em.createNamedQuery(DocType.ALL_TYPE, DocType.class);
			return query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}
	
}
