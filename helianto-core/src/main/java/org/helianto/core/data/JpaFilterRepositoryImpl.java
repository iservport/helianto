package org.helianto.core.data;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.helianto.core.filter.Filter;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
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

	@SuppressWarnings("unchecked")
	public Iterable<T> find(Filter filter) {
		String alias = filter.getObjectAlias()!=null ? filter.getObjectAlias() : "alias";
		StringBuilder queryString = new StringBuilder("select ")
			.append(alias)
			.append(" from ")
			.append(metadata.getEntityName())
			.append(" ")
			.append(alias)
			.append(" ");
		String whereClause = filter.createCriteriaAsString();
        if (whereClause!=null && whereClause.length()>0) {
        	queryString.append("where ").append(whereClause);
        }
		Query query = entityManager.createQuery(queryString.toString());
		return query.getResultList();
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

}