package igor.bts.dao;

import igor.bts.entity.BZ;
import igor.bts.entity.DocType;
import igor.bts.entity.Service;
import igor.bts.entity.TpInternet;

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
		DocType docType = service.getType();
		if(docType != null)
			if(docType.getId() != null)
				em.merge(docType);
			else
				em.persist(docType);
		TpInternet tp = service.getTpInternet();
		if(tp != null)
			if(tp.getId() != null)
				em.merge(tp);
			else
				em.persist(tp);
		BZ bz = service.getBz();
		if(bz != null)
			if(bz.getId() != null)
				em.merge(bz);
			else
				em.persist(bz);
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
