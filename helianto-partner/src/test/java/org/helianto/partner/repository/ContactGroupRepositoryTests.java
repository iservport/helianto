package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.ContactGroup;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ContactGroupRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ContactGroup, ContactGroupRepository> 
{

	@Autowired
	private ContactGroupRepository repository;
	
	protected ContactGroupRepository getRepository() {
		return repository;
	}
	
	protected ContactGroup getNewTarget() {
		return new ContactGroup(entity, "KEY");
	}
	
	protected Serializable getTargetId(ContactGroup target) {
		return target.getId();
	}
	
	protected ContactGroup findByKey() {
		return getRepository().findByEntityAndUserKey(entity, "KEY");
	}
	
}
