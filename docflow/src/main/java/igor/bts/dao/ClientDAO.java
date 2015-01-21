package igor.bts.dao;

import igor.bts.entity.Bank;
import igor.bts.entity.Client;
import igor.bts.entity.Manager;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ClientDAO implements IClientDAO<Client> {
	@PersistenceContext(unitName = "workflow")
	private EntityManager em;
	
	public void setEm(EntityManager em){
		this.em = em;
	}
	
	@Override
	public Client create(Client client) {
		//Bank bank = null;
		//if(client.getBank() != null && client.getBank().getId() != null)
		//	bank = em.find(Bank.class, client.getBank().getId());
		//em.merge(bank);
		//Manager manager = null;
		//if(client.getManager() != null && client.getManager().getId() != null)
		//	manager = em.find(Manager.class, client.getManager().getId());
		//em.merge(manager);
		//client.setBank(bank);
		//client.setManager(manager);
		em.persist(client);
		return client;
	}

	@Override
	public void delete(Integer id) {
		Client client = em.find(Client.class, id);
		em.remove(client);
	}

	@Override
	public void update(Client client) {
		em.merge(client);
	}

	@Override
	public Client getById(Integer id) {
		Client client = em.find(Client.class, id);
		return client;
	}

	@Override
	public List<Client> getAll() {
		TypedQuery<Client> query = em.createNamedQuery(Client.ALL_CLIENT, Client.class);
		return (List<Client>)query.getResultList();
	}
	
}
