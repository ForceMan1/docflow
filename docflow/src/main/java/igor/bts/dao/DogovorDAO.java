package igor.bts.dao;

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
