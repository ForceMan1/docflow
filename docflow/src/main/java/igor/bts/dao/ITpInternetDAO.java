package igor.bts.dao;

import igor.bts.entity.TpInternet;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

//public interface ITpInternetDAO<TpInternet> extends ICommonDAO<TpInternet> {
public abstract class ITpInternetDAO {
	abstract public TpInternet create(TpInternet t);
	abstract public boolean delete(Integer id);
	abstract public TpInternet update(TpInternet t);
	abstract public TpInternet getById(Integer id);
	abstract public List<TpInternet> getAll();
}
