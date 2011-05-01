package org.helianto.core.filter;

import org.helianto.core.Operator;
import org.helianto.core.Server;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;

/**
 * Server filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class ServerFilterAdapter extends AbstractRootFilterAdapter<Server> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param filter
	 */
	public ServerFilterAdapter(Server filter) {
		super(filter);
		reset();
	}

	/**
	 * Key constructor.
	 * 
	 * @param operator
	 * @param serverName
	 */
	public ServerFilterAdapter(Operator operator, String serverName) {
		this(new Server(operator, serverName));
	}

	public void reset() {
    	getFilter().setServerType(' ');
    	getFilter().setPriority((byte) 0);
    	getFilter().setServerState(' ');
	}
	
	@Override
	public boolean isSelection() {
		return getFilter().getServerName().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("serverName", getFilter().getServerName(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("serverType", getFilter().getServerType(), mainCriteriaBuilder);
		appendEqualFilter("priority", getFilter().getPriority(), mainCriteriaBuilder);
		appendEqualFilter("serverState", getFilter().getServerState(), mainCriteriaBuilder);
		appendOrderBy("priority", mainCriteriaBuilder);
	}

}
