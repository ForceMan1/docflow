package igor.bts.dao;

import igor.bts.entity.Client;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

//public interface IClientDAO<Client> extends ICommonDAO<Client> {
public abstract class IClientDAO {
	abstract public Client create(Client t);
	abstract public boolean delete(Integer id);
	abstract public Client update(Client t);
	abstract public Client getById(Integer id);
	abstract public List<Client> getAll();

}
