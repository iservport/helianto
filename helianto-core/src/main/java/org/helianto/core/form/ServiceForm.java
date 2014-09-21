package org.helianto.core.form;

import org.helianto.core.domain.type.RootEntity;

/**
 * Service form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ServiceForm 
	extends RootEntity 
{
	
    /**
     * Service name.
     */
	public String getServiceName();
	
    /**
     * Service name like.
     */
	public String getServiceNameLike();
	
}
