package igor.bts.dao;

import igor.bts.entity.EdIzm;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

//public interface IEdIzmDAO<EdIzm> extends ICommonDAO<EdIzm> {
public abstract class IEdIzmDAO {
	abstract public EdIzm create(EdIzm t);
	abstract public boolean delete(Integer id);
	abstract public EdIzm update(EdIzm t);
	abstract public EdIzm getById(Integer id);
	abstract public List<EdIzm> getAll();
}
