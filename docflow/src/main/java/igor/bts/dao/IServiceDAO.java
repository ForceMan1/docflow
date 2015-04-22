package igor.bts.dao;

import igor.bts.entity.Service;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

//public interface IServiceDAO<Service> extends ICommonDAO<Service> {
public abstract class IServiceDAO {
	abstract public Service create(Service t);
	abstract public boolean delete(Integer id);
	abstract public Service update(Service t);
	abstract public Service getById(Integer id);
	abstract public List<Service> getAll();
}
