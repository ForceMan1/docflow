package igor.bts.dao;

import igor.bts.entity.TpInternet;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class TpInternetDAO implements ITpInternetDAO<TpInternet> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	@Override
	public TpInternet create(TpInternet tpInternet) {
		em.persist(tpInternet);
		return tpInternet;
	}

	@Override
	public void delete(Integer  id) {
		TpInternet tpInternet = em.find(TpInternet.class, id);
		em.remove(tpInternet);
		
	}

	@Override
	public void update(TpInternet tpInternet) {
		em.merge(tpInternet);
	}

	@Override
	public TpInternet getById(Integer id) {
		return em.find(TpInternet.class, id);	
	}

	@Override
	public List<TpInternet> getAll() {
		TypedQuery<TpInternet> query = em.createNamedQuery(TpInternet.ALL_TP, TpInternet.class);
		return query.getResultList();
	}

}
