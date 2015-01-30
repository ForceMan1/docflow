package igor.bts.dao;

import igor.bts.entity.Bank;
import igor.bts.entity.Client;
import igor.bts.entity.Manager;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
		Bank bank = client.getBank();
		if(client.getBank() != null){
			if(client.getBank().getId() != null)
				bank = em.merge(bank);
			else 
				em.persist(bank);
		}
		Manager manager = client.getManager();
		if(client.getManager() != null){
			if(client.getManager().getId() != null)
				manager = em.merge(manager);
			else
				em.persist(manager);
		}
		client.setBank(bank);
		client.setManager(manager);
		em.persist(client);
		em.flush();
		return client;
	}

	@Override
	public void delete(Integer id) {
		Client client = em.find(Client.class, id);
		em.remove(client);
		em.flush();
	}

	@Override
	public void update(Client client) {
		em.merge(client);
		em.flush();
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
