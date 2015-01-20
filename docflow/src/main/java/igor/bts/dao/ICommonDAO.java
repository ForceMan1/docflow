package igor.bts.dao;

import igor.bts.entity.Service;

import java.util.List;

public interface ICommonDAO<T> {
	public T create(T t);
	public void delete(Integer id);
	public void update(T t);
	public T getById(Integer id);
	public List<T> getAll();
}
