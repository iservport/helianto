package org.helianto.core.orm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

/**
 * A post processor to simply cache managed class names from various persistence.xml files 
 * in the classpath and merge them in the <code>PersistenceUnitInfo</<code>.
 * 
 * @author Mauricio Fernandes de Castro.
 */
public class DefaultPersitenceUnitPostProcessor implements PersistenceUnitPostProcessor, InitializingBean {
	
	private ThreadLocal<List<String>> classNames;

	/**
	 * Post process the persistence unit info to merge managed class names.
	 * 
	 * TODO: cache manage class names for each persistence unit name.
	 */
	public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
		
		classNames.get().addAll(pui.getManagedClassNames());
		pui.getManagedClassNames().clear();
		pui.getManagedClassNames().addAll(classNames.get());
		
	}

	public void afterPropertiesSet() throws Exception {
		classNames = new ThreadLocal<List<String>>();
		classNames.set(new ArrayList<String>());
	}

}
