package igor.bts.dao;

import igor.bts.entity.EdIzm;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.enterprise.inject.Produces;

public class EdIzmDAO implements IEdIzmDAO<EdIzm> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	@Override
	public EdIzm create(EdIzm edIzm) {
		em.persist(edIzm);
		return edIzm;
	}

	@Override
	public void delete(Integer id) {
		EdIzm edIzm = em.find(EdIzm.class, id);
		em.remove(edIzm);
		
	}

	@Override
	public void update(EdIzm edIzm) {
		em.merge(edIzm);
	}

	@Override
	public EdIzm getById(Integer id) {
		EdIzm edIzm = em.find(EdIzm.class, id);
		return edIzm;
	}

	@Override
	public List<EdIzm> getAll() {
		TypedQuery<EdIzm> query = em.createNamedQuery(EdIzm.ALL_ED_IZM, EdIzm.class);
		return query.getResultList();
	}

}
