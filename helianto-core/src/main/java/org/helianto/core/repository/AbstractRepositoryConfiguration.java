package org.helianto.core.repository;

import javax.annotation.Resource;

/**
 * Base class to repository configuration using Spring 3.x Java Config to create DAOs.
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractRepositoryConfiguration {
	
	private String defaultKeys[];
	
	/**
	 * Default constructor.
	 */
	public AbstractRepositoryConfiguration() {
		setDefaultKeys("entity", "internalNumber");
	}
	
	/**
	 * Default Keys.
	 * 
	 * @param defaultKeys
	 */
	public void setDefaultKeys(String... defaultKeys) {
		this.defaultKeys = defaultKeys;
	}
	
	/**
	 * Get a new FilterDao using default keys.
	 * 
	 * @param <T>
	 * @param clazz
	 */
	public <T> FilterDao<T> getFilterDao(Class<T> clazz) {
		return repositoryFactory.filterDaoFactory(clazz, defaultKeys);
	}

	/**
	 * Get a new FilterDao.
	 * 
	 * @param <T>
	 * @param clazz
	 * @param keys
	 */
	public <T> FilterDao<T> getFilterDao(Class<T> clazz, String... keys) {
		return repositoryFactory.filterDaoFactory(clazz, keys);
	}

	// collabs
    
    private RepositoryFactory repositoryFactory;
    
    @Resource
	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

}
