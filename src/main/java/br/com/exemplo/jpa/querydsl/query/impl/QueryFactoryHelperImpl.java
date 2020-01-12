package br.com.exemplo.jpa.querydsl.query.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.exemplo.jpa.querydsl.query.IQueryFactoryHelper;

@Repository
public class QueryFactoryHelperImpl implements IQueryFactoryHelper {

	private final JPQLQueryFactory queryFactory;

	@Inject
	public QueryFactoryHelperImpl(final EntityManager entityManager) {
		super();
		queryFactory = new JPAQueryFactory(entityManager);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPQLQueryFactory factory() {
		return queryFactory;
	}

}
