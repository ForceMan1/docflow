package igor.bts.dao;

import igor.bts.entity.DocType;

import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

//public interface IDocTypeDAO<DocType> extends ICommonDAO<DocType> {
public abstract class IDocTypeDAO {
	abstract public DocType create(DocType t);
	abstract public boolean delete(Integer id);
	abstract public DocType update(DocType t);
	abstract public DocType getById(Integer id);
	abstract public List<DocType> getAll();

}
