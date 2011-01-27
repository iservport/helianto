package org.helianto.core.filter;

import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Key type filter adapter.
 * 
 * @author mauriciofernandesdecastro
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
		return getFilter().getKeyCode().length()>0;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("keyCode", getFilter().getKeyCode(), mainCriteriaBuilder);		
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) { 
		appendLikeFilter("keyName", getFilter().getKeyName(), mainCriteriaBuilder);
	}
	
}
