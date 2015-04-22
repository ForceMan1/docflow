package igor.bts.dao;

import igor.bts.entity.BZ;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

//public interface IBZDAO<BZ> extends ICommonDAO<BZ> {
public abstract class IBZDAO {
	abstract public BZ create(BZ t);
	abstract public boolean delete(Integer id);
	abstract public BZ update(BZ t);
	abstract public BZ getById(Integer id);
	abstract public List<BZ> getAll();
}
