package org.helianto.core.form;

import org.helianto.core.def.ProviderType;

/**
 * Connection data form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentitySecurityForm 
	extends IdentityIdForm {

    /**
     * <<Transient>> Convenience to read identity principal.
     */
    String getPrincipal();
    
    /**
     * Provider type.
     */
    ProviderType getProviderType();
    
}
