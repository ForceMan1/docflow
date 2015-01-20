package igor.bts.dao;

import igor.bts.entity.Service;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ServiceDAO implements IServiceDAO<Service> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	@Override
	public Service create(Service service) {
		em.persist(service);
		return service;
	}

	@Override
	public void delete(Integer id) {
		Service service = em.find(Service.class, id);
		em.remove(service);
	}

	@Override
	public void update(Service service) {
		em.merge(service);
	}

	@Override
	public Service getById(Integer id) {
		Service service = em.find(Service.class, id);
		return service;
	}

	@Override
	public List<Service> getAll() {
		TypedQuery<Service> query = em.createNamedQuery(Service.ALL_SERVICE, Service.class);
		return query.getResultList();
	}

}
