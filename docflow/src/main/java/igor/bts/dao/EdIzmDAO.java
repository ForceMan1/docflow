package igor.bts.dao;

import igor.bts.CRUDEntityException;
import igor.bts.entity.EdIzm;






import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.enterprise.inject.Produces;

/**
 * 
 * 
 * @author Igor
 * Class for managing {@link igor.bts.entity.EdIzm} entity instances.
 *
 * @param EdIzm entity type
 */
//public class EdIzmDAO implements IEdIzmDAO<EdIzm> {
public class EdIzmDAO extends IEdIzmDAO {
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
	 * Create new EdIzm entity instance.
	 * 
	 * @param edIzm {@link igor.bts.entity.EdIzm} POJO object for creating
	 * @throws CRUDEntityException causing by an TransactionRequiredException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type 
     *         <code>PersistenceContextType.TRANSACTION</code>
	 * @throws IllegalArgumentException when  if instance is not an
     *         entity or is a removed entity
     * @return EdIzm entity instance
	 */
	@Override
	public EdIzm create(EdIzm edIzm) {
		try {
			em.persist(edIzm);
			return edIzm;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Find EdIzm entity by primary key and 
	 * remove it from the persistence context.
	 * 
	 * @param id primary key of the {@link igor.bts.entity.EdIzm} instance
	 * @return true if the deleting result is successfull or false in a another case
	 * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public boolean delete(Integer id) {
		try {
			EdIzm edIzm = em.find(EdIzm.class, id);
			if(edIzm == null)
				return false;
			em.remove(edIzm);
			return true;
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Update {@link igor.bts.entity.EdIzm} entity in the Persistence context.
	 * 
	 * @param edIzm {@link igor.bts.entity.EdIzm} entity
	 * @return the updated Bank entity
	 * @throws IllegalArgumentException if the instance is not an
     *         entity or is a detached entity
     * @throws CRUDEntityException causing by an TransactionRequiredException if invoked on a
     *         container-managed entity manager of type 
     *         <code>PersistenceContextType.TRANSACTION</code> and there is 
     *         no transaction
	 */
	@Override
	public EdIzm update(EdIzm edIzm) {
		try {
			return em.merge(edIzm);
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

	/**
	 * Get {@link igor.bts.entity.EdZm} entity from Persistence Context by primary key.
	 * 
	 * @param id primary key
	 * @return the found entity instance or null if the entity doesn't exist
	 */
	@Override
	public EdIzm getById(Integer id) {
		EdIzm edIzm = em.find(EdIzm.class, id);
		return edIzm;
	}

	/**
	 * Create an instance of <code>Query</code> for getting 
	 *  all {@link igor.bts.entity.EdIzm} from the Peristence context.
	 *  @return List of all {@link igor.bts.entity.EdIzm} entity instances
	 *  @throws CrudEntityException causing by any PeristenceException
	 */
	@Override
	public List<EdIzm> getAll() {
		try {
			TypedQuery<EdIzm> query = em.createNamedQuery(EdIzm.ALL_ED_IZM, EdIzm.class);
			return query.getResultList();
		}catch(PersistenceException e){
			throw new CRUDEntityException(e);
		}
	}

}
