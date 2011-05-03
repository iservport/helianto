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
	 * @param form
	 */
	public ServerFilterAdapter(Server form) {
		super(form);
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
    	getForm().setServerType(' ');
    	getForm().setPriority((byte) 0);
    	getForm().setServerState(' ');
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getServerName().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("serverName", getForm().getServerName(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("serverType", getForm().getServerType(), mainCriteriaBuilder);
		appendEqualFilter("priority", getForm().getPriority(), mainCriteriaBuilder);
		appendEqualFilter("serverState", getForm().getServerState(), mainCriteriaBuilder);
		appendOrderBy("priority", mainCriteriaBuilder);
	}

}
