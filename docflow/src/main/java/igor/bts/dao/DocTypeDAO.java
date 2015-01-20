package igor.bts.dao;

import igor.bts.entity.DocType;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DocTypeDAO implements IDocTypeDAO<DocType> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	@Override
	public DocType create(DocType t) {
		em.persist(t);
		return t;
	}

	@Override
	public void delete(Integer id) {
		DocType docType = em.find(DocType.class, id);
		em.remove(docType);
	}

	@Override
	public void update(DocType docType) {
		em.merge(docType);
	}

	@Override
	public DocType getById(Integer id) {
		DocType docType = em.find(DocType.class, id);
		return docType;
	}

	@Override
	public List<DocType> getAll() {
			TypedQuery<DocType> query = em.createNamedQuery(DocType.ALL_TYPE, DocType.class);
			return query.getResultList(); 
	}
	
}
