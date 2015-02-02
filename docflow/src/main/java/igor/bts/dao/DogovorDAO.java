package igor.bts.dao;

import igor.bts.entity.Client;
import igor.bts.entity.DocType;
import igor.bts.entity.Dogovor;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Produces;

public class DogovorDAO implements IDogovorDAO<Dogovor> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	@Override
	public Dogovor create(Dogovor dogovor) {
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
	}

	@Override
	public void delete(Integer id) {
		Dogovor dogovor = em.find(Dogovor.class, id);
		em.remove(dogovor);
	}

	@Override
	public void update(Dogovor dogovor) {
		em.merge(dogovor);
	}

	@Override
	public Dogovor getById(Integer id) {
		Dogovor dogovor = em.find(Dogovor.class, id);
		return dogovor;
	}

	@Override
	public List<Dogovor> getAll() {
		TypedQuery<Dogovor> query = em.createNamedQuery(Dogovor.ALL_DOGOVOR, Dogovor.class);
		return (List<Dogovor>)query.getResultList();
	}
}
