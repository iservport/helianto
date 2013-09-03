package org.helianto.core.data;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.helianto.core.filter.Filter;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 * @param <ID>
 */
public class JpaFilterRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements FilterRepository<T, ID> {

	private EntityManager entityManager;
	private JpaEntityInformation<T, ID> metadata;

	public JpaFilterRepositoryImpl(JpaEntityInformation<T, ID> metadata, EntityManager entityManager) {
		super(metadata, entityManager);
		this.entityManager = entityManager;
		this.metadata = metadata;
	}

	public long count(Filter filter) {
		String alias = filter.getObjectAlias()!=null ? filter.getObjectAlias() : "alias";
		StringBuilder queryString = new StringBuilder("select count(")
			.append(alias)
			.append(".id) from ")
			.append(metadata.getEntityName())
			.append(" ")
			.append(alias)
			.append(" ");
		String whereClause = filter.createCriteriaAsString();
        if (whereClause!=null && whereClause.length()>0) {
        	queryString.append("where ").append(whereClause);
        }
		Query query = entityManager.createQuery(queryString.toString());
		return (Long) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public Iterable<T> find(Filter filter, Pageable pageable) {
		String alias = filter.getObjectAlias()!=null ? filter.getObjectAlias() : "alias";
		String selectClause = filter.createSelectAsString();
		StringBuilder queryString = new StringBuilder();
		if (selectClause!=null) {
			queryString.append(selectClause);
		}
		else {
			queryString.append("select ")
			.append(alias)
			.append(" from ")
			.append(metadata.getEntityName())
			.append(" ")
			.append(alias)
			.append(" ");
		}
		String whereClause = filter.createCriteriaAsString();
        if (whereClause!=null && whereClause.length()>0) {
        	queryString.append("where ").append(whereClause);
        }
		Query query = entityManager.createQuery(queryString.toString());
		return query.getResultList();
	}
	
	public Iterable<T> find(Filter filter) {
		return find(filter, null);
	}
	
	@Override
	@Transactional
	public <S extends T> S save(S entity) {
		if (entityManager instanceof HibernateEntityManager) {
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(entity);
			return entity;
		}
		return super.save(entity);
	}
	
	public void refresh(T entity) {
		entityManager.refresh(entity);
	}

}