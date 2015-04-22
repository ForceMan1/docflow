package igor.bts.dao;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import igor.bts.entity.Manager;

//public interface IManagerDAO<Manager> extends ICommonDAO<Manager> {
public abstract class IManagerDAO {
	abstract public Manager create(Manager t);
	abstract public boolean delete(Integer id);
	abstract public Manager update(Manager t);
	abstract public Manager getById(Integer id);
	abstract public List<Manager> getAll();
}
