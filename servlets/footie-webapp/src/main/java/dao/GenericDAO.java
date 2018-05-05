package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public interface GenericDAO<T, ID extends Serializable> {

	T save(T entity);
	T update(T entity);
	void delete(T entity);
	T findById(ID id);
	List<T> findAll();
	void flush();
}
