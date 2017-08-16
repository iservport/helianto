package org.helianto.bootstrap.service;

import java.util.List;

import org.helianto.classic.def.ActivityState;
import org.helianto.classic.def.AddressType;
import org.helianto.classic.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalAddress;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.domain.Unit;
import org.helianto.classic.filter.PersonalAddressFilterAdapter;
import org.helianto.classic.filter.PublicEntityFormFilterAdapter;
import org.helianto.classic.form.CompositeEntityForm;
import org.helianto.core.repository.CategoryRepository;
import org.helianto.core.repository.CredentialRepository;
import org.helianto.core.repository.EntityRepository;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.repository.PersonalAddressRepository;
import org.helianto.core.repository.PublicEntityRepository;
import org.helianto.core.repository.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Installation service interface.
 * 
 * @author mauriciofernandesdecastro
 */
public class InstallationMgrImpl {

	@Autowired
    private IdentityRepository identityRepository;

	@Autowired
    private EntityRepository entityRepository;

	@Autowired
	private CredentialRepository credentialRepository;

	@Autowired
    private PersonalAddressRepository personalAddressRepository;

	@Autowired
	private CategoryRepository categoryRepository;
    
	@Autowired
    private PublicEntityRepository publicEntityRepository;

	@Autowired
    private UnitRepository unitRepository;

	public Category installCategory(Entity entity, CategoryGroup categoryGroup, String categoryCode, String categoryName) {
    	Category category = categoryRepository.findByEntityAndCategoryGroupAndCategoryCode(entity, categoryGroup.getValue(), categoryCode);
    	if (category!=null) {
        	logger.debug("Found category {}", category);
    		return category;
    	}
    	category = new Category(entity, categoryGroup, categoryCode);
    	category.setCategoryName(categoryName);
    	categoryRepository.save(category);
    	logger.debug("Category {} installed.", category);
    	return category;
	}

	public Credential installIdentity(String principal) {
		Identity identity = identityRepository.findByPrincipal(principal);
		if (identity==null) {
			logger.info("Will install identity for {}.", principal);
			identity = new Identity(principal);
			identityRepository.save(identity);
		}
		else {
			logger.debug("Found existing identity for {}.", principal);
		}
		PersonalAddress personalAddress = new PersonalAddress(identity, null);
		List<PersonalAddress> personalAddressList = (List<PersonalAddress>) personalAddressRepository.find(new PersonalAddressFilterAdapter(personalAddress));
		if (personalAddressList.size()==0) {
			personalAddress.setAddressTypeAsEnum(AddressType.PERSONAL);
			personalAddressRepository.save(personalAddress);
		}
		return installCredential(identity);
	}
	
	public Credential installCredential(Identity identity) {
		Credential credential = credentialRepository.findByIdentity(identity);
		if (credential==null) {
			logger.info("Will install credential for {}.", identity);
			credential = new Credential(identity);
			// TODO make it INITIAL
			credential.setCredentialState(ActivityState.ACTIVE);
			credentialRepository.save(credential);
		}
		else {
			logger.debug("Found existing credential for {}.", identity);
		}
		return credential;
	}
	

	public PublicEntity installPublicEntity(Entity entity) {
		PublicEntity publicEntity = null;
    	if (entity.getNatureAsArray().length>0) {
    		logger.debug("Looking for existing public entity");
    		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(new CompositeEntityForm(entity));
    		List<? extends PublicEntity> publicEntities = (List<PublicEntity>) publicEntityRepository.find(filter);
    		if (publicEntities!=null && publicEntities.size()==0) {
    			logger.debug("Installing public entity ...");
    			publicEntity = new PublicEntity(entity);
    			publicEntityRepository.save(publicEntity);
    			logger.debug("Installed {}.", publicEntity);
    		}
    	}
    	else {
    		logger.warn("Public entity not installed: need to set up entity nature first.");
    	}
		return publicEntity;
	}
	
	public Unit installUnit(Category category, String unitCode, String unitName) {
		Unit unit = unitRepository.findByCategoryAndUnitCode(category, unitCode);
    	if (unit!=null) {
        	logger.debug("Found existing unit  {}", unit);
    		return unit;
    	}
    	unit = new Unit(category, unitCode);
    	unit.setUnitName(unitName);
    	unitRepository.save(unit);
    	logger.debug("Installed unit  {}", unit);
    	unitRepository.flush();
		return unit;
	}

    private final Logger logger = LoggerFactory.getLogger(InstallationMgrImpl.class);

}
