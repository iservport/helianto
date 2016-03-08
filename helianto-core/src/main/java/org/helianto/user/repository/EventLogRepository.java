package org.helianto.user.repository;

import java.io.Serializable;

import org.helianto.user.domain.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository  extends JpaRepository<EventLog, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param logId
	 */
	EventLog findByLogId(String logId);

}
