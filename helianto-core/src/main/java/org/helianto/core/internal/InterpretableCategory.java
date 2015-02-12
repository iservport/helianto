package org.helianto.core.internal;

import org.helianto.core.domain.Category;

/**
 * Interface para classes onde a categoria define a origem dos scripts a serem interpretados.
 * 
 * @author mauriciofernandesdecastro
 */
public interface InterpretableCategory 

	extends Interpretable 

{
	
    /**
     * Category.
     */
    Category getCategory();
    
}
