package org.helianto.core.form;

import org.helianto.core.def.ProviderType;

/**
 * Connection data form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ConnectionDataForm 
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
