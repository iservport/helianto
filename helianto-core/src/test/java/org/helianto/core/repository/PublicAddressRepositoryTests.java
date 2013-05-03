package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.PublicAddress;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicAddressRepositoryTests extends AbstractJpaRepositoryIntegrationTest<PublicAddress, PublicAddressRepository> {

	@Autowired
	private PublicAddressRepository repository;
	
	protected PublicAddressRepository getRepository() {
		return repository;
	}
	
	protected PublicAddress getNewTarget() {
		return new PublicAddress(operator, "CODE");		
	}
	
	protected Serializable getTargetId(PublicAddress target) {
		return target.getId();
	}
	
	protected PublicAddress findByKey() {
		return getRepository().findByOperatorAndPostalCode(operator, "CODE");	
	}
	
}
