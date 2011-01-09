package org.helianto.core.filter;

import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Convenience to be used in tests.
 * 
 * @author mauriciofernandesdecastro
 */
public class TestingFilter extends AbstractListFilter {

	private static final long serialVersionUID = 1L;

	public void reset() { }

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) { }

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) { }

}
