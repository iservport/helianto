package org.helianto.message.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.message.domain.NotificationEvent;

/**
 * Notification event repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface NotificationEventRepository 
	extends FilterRepository<NotificationEvent, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	NotificationEvent findByEntityAndInternalNumber(Entity entity, long internalNumber);

}