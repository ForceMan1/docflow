package igor.bts.dao;

import igor.bts.entity.Manager;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class ManagerDAO implements IManagerDAO<Manager> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	@Override
	public Manager create(Manager manager) {
		em.persist(manager);
		return manager;
	}

	@Override
	public void delete(Integer id) {
		Manager manager = em.find(Manager.class, id);
		em.remove(manager);
		
	}

	@Override
	public void update(Manager manager) {
		em.merge(manager);
	}

	@Override
	public Manager getById(Integer id) {
		Manager manager = em.find(Manager.class, id);
		return manager;
	}

	@Override
	public List<Manager> getAll() {
		Query query = em.createNamedQuery(Manager.ALL_MANAGER);
		return (List<Manager>)query.getResultList();
	}
}
