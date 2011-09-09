package org.helianto.core.filter.form;

import org.helianto.core.RootEntity;

/**
 * Service form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ServiceForm extends RootEntity {
	
    /**
     * Service name.
     */
	public String getServiceName();
	
    /**
     * Service name like.
     */
	public String getServiceNameLike();
	
}
