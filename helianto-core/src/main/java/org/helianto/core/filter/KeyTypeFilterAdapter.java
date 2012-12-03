package org.helianto.core.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;

/**
 * Key type filter adapter.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 * @see KeyTypeFormFilterAdapter
 */
public class KeyTypeFilterAdapter extends AbstractRootFilterAdapter<KeyType> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param filter
	 */
	public KeyTypeFilterAdapter(KeyType filter) {
		super(filter);
	}

	/**
	 * Key constructor.
	 * 
	 * @param operator
	 * @param keyCode
	 */
	public KeyTypeFilterAdapter(Operator operator, String keyCode) {
		super(new KeyType(operator, keyCode));
	}

	public void reset() { }
	
	@Override
	public boolean isSelection() {
		return getForm().getKeyCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("keyCode", getForm().getKeyCode(), mainCriteriaBuilder);		
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { 
		appendLikeFilter("keyName", getForm().getKeyName(), mainCriteriaBuilder);
	}
	
}
