package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.def.AddressType;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalAddress;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PersonalAddressRepositoryTests extends AbstractJpaRepositoryIntegrationTest<PersonalAddress, PersonalAddressRepository> {

	@Autowired
	private PersonalAddressRepository repository;
	
	@Autowired
	private IdentityRepository identityRepository;
	
	private Identity identity;
	
	protected PersonalAddressRepository getRepository() {
		return repository;
	}
	
	protected PersonalAddress getNewTarget() {
		return new PersonalAddress(identity, AddressType.MAIN);		
	}
	
	protected Serializable getTargetId(PersonalAddress target) {
		return target.getId();
	}
	
	protected PersonalAddress findByKey() {
		return getRepository().findByIdentityAndAddressType(identity, AddressType.MAIN.getValue());
	}
	
	public void setUp() {
		identity = identityRepository.save(new Identity("PRINCIPAL"));
	}
	
}
