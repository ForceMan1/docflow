package igor.bts.dao;

import igor.bts.entity.Service;

import java.util.List;

public interface ICommonDAO<T> {
	abstract public T create(T t);
	abstract public boolean delete(Integer id);
	abstract public T update(T t);
	abstract public T getById(Integer id);
	abstract public List<T> getAll();
}
