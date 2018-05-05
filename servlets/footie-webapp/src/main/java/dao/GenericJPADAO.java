package dao;

import tableEntities.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public abstract class GenericJPADAO<T, ID extends Serializable> implements GenericDAO<T,ID> {

	
	private Class<T> persistenceClass;
    @PersistenceContext(unitName="Tutorial")
	private EntityManager entityManager;
	
	public GenericJPADAO(Class<T> persistenceClass){
		this.persistenceClass = persistenceClass;
	}
	
	public EntityManager getEntityManager(){
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	public Class<T> getPersistenceClass() {
		return persistenceClass;
	}
	

	@SuppressWarnings("unchecked")
	 @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<T> findAll() {
		return getEntityManager()
				.createQuery("select x from " + getPersistenceClass().getSimpleName() + " x")
				.getResultList();
	}

	public T findById(ID id) {
		T entity = getEntityManager().find(getPersistenceClass(), id);
		return entity;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T save(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	public T update(T entity) {
		T mergedEntity = getEntityManager().merge(entity);
		return mergedEntity;
	}

	public void delete(T entity) {
		if(entity instanceof User){
			User user = (User)entity;
			getEntityManager().remove(getEntityManager().getReference(entity.getClass(),user.getUserId()));
		}
	}

	public void flush() {
		getEntityManager().flush();	
	}
}
