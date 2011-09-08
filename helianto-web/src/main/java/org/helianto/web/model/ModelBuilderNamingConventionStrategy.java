package org.helianto.web.model;

import org.helianto.core.naming.AbstractNamingConventionStrategy;
import org.springframework.stereotype.Component;

/**
 * Implementation to extract target name from the model builder class name.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("modelBuilderNamingConventionStrategy")
public class ModelBuilderNamingConventionStrategy extends AbstractNamingConventionStrategy {

	@Override
	protected String getSuffix() { return "ModelBuilder"; }

	@Override
	protected boolean isValid(Class<?> clazz) {
		return ModelBuilder.class.isAssignableFrom(clazz);
	}

}
