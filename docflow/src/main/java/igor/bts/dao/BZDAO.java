package igor.bts.dao;

import igor.bts.CRUDEntityException;
import igor.bts.entity.BZ;
import igor.bts.entity.DocType;
import igor.bts.entity.Dogovor;

import java.util.List;










import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.ws.rs.Produces;

/**
 * 
 * @author Igor
 * Class for managing {@link igor.bts.entity.BZ} entity instances.
 *
 * @param {@link igor.bts.entity.Bank} class
 */
//public class BZDAO implements IBZDAO<BZ> {
public class BZDAO extends IBZDAO {
	/**
	 * Entity manager for JPA Provider
	 */
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	/**
	 * Set entity manager.
	 * 
	 * @param em EntityManager
	 */
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	/**
	 * Create new instance of {@link igor.bts.entity.BZ} entity.
	 * 
	 * @param bz {@link igor.bts.entity.BZ} POJO object for creating new instance of BZ type
	 * * @throws CRUDEntityException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
     *         or if the entity already exists
     *         or by or another <code>PersistenceException</code
	 * @throws IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return  BZ entity instance
	 */
	@Override
	public BZ create(BZ bz) {
		try {
			DocType docType = bz.getType();
			if(docType != null)
				if(docType.getId() != null)
					em.merge(docType);
				else
					em.persist(docType);
			em.persist(bz);
			Dogovor dogovor = bz.getDogovor();
			if(dogovor != null)
				if(dogovor.getId() != null)
					em.merge(dogovor);
				else
					em.persist(dogovor);
			return bz;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
		
	}

	/**
	 * Find BZ entity by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.BZ} instance
	 * @return true if the deleting result is successfull or false in a another case
	 * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public boolean delete(Integer id) {
		try {
			BZ bz = em.find(BZ.class, id);
			if(bz == null)
				return false;
			em.remove(bz);
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Update {@link igor.bts.entity.BZ} entity in the Persistence context.
	 * 
	 * @param bank {@link igor.bts.entity.BZ} entity
	 * @return the updated Bank entity
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws CRUDEntityException causing by TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public BZ update(BZ bz) {
		try {
			return em.merge(bz);
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.BZ} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public BZ getById(Integer id) {
		BZ bz = em.find(BZ.class, id);
		return bz;
	}

	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.BZ} from the Peristence context.
	 *  @return List of all {@link igor.bts.entity.BZ} entity instances
	 *  @throws CRUDEntityException causing by any PersistenceException
	 */ 
	@Override
	public List<BZ> getAll() {
		try {
			javax.persistence.TypedQuery<BZ> query = em.createNamedQuery(BZ.ALL_BZ, BZ.class);
			return (List<BZ>)query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

}
