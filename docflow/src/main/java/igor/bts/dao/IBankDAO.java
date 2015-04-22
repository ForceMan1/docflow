package igor.bts.dao;

import igor.bts.entity.Bank;

import java.util.List;

//public interface IBankDAO<T extends Bank> extends ICommonDAO<T> {
public abstract class IBankDAO {
	abstract public Bank create(Bank t);
	abstract public boolean delete(Integer id);
	abstract public Bank update(Bank t);
	abstract public Bank getById(Integer id);
	abstract public List<Bank> getAll();

}
