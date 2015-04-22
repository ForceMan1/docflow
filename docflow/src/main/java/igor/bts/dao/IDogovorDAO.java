package igor.bts.dao;

import igor.bts.entity.Dogovor;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

//public interface IDogovorDAO<Dogovor> extends ICommonDAO<Dogovor> {
public abstract class IDogovorDAO {
	abstract public Dogovor create(Dogovor t);
	abstract public boolean delete(Integer id);
	abstract public Dogovor update(Dogovor t);
	abstract public Dogovor getById(Integer id);
	abstract public List<Dogovor> getAll();

}
