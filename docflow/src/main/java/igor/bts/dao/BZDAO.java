package igor.bts.dao;

import igor.bts.entity.BZ;
import igor.bts.entity.DocType;
import igor.bts.entity.Dogovor;

import java.util.List;






import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Produces;

public class BZDAO implements IBZDAO<BZ> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	@Override
	public BZ create(BZ bz) {
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
		
	}

	@Override
	public void delete(Integer id) {
		BZ bz =em.find(BZ.class, id);
		em.remove(bz);
	}

	@Override
	public void update(BZ bz) {
		em.merge(bz);
	}

	@Override
	public BZ getById(Integer id) {
		BZ bz = em.find(BZ.class, id);
		return bz;
	}

	@Override
	public List<BZ> getAll() {
		javax.persistence.TypedQuery<BZ> query = em.createNamedQuery(BZ.ALL_BZ, BZ.class);
		return (List<BZ>)query.getResultList();
	}

}
