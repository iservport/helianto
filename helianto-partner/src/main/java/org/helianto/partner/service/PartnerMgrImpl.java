/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.partner.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.ContextMgr;
import org.helianto.core.SequenceMgr;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.ProvinceRepository;
import org.helianto.core.utils.AddressUtils;
import org.helianto.partner.PartnerMgr;
import org.helianto.partner.PartnerState;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;
import org.helianto.partner.domain.PartnerKey;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.PrivateEntityKey;
import org.helianto.partner.domain.nature.Agent;
import org.helianto.partner.domain.nature.Customer;
import org.helianto.partner.domain.nature.Division;
import org.helianto.partner.domain.nature.EducationPartner;
import org.helianto.partner.domain.nature.Laboratory;
import org.helianto.partner.domain.nature.Manufacturer;
import org.helianto.partner.domain.nature.Supplier;
import org.helianto.partner.domain.nature.TransportPartner;
import org.helianto.partner.filter.ContactGroupFormFilterAdapter;
import org.helianto.partner.filter.PartnerCategoryFormFilterAdapter;
import org.helianto.partner.filter.PartnerFormFilterAdapter;
import org.helianto.partner.filter.PartnerPhoneFormFilterAdapter;
import org.helianto.partner.filter.PrivateAddressFormFilterAdapter;
import org.helianto.partner.filter.PrivateEntityFormFilterAdapter;
import org.helianto.partner.filter.PrivateEntityKeyFormFilterAdapter;
import org.helianto.partner.form.ContactGroupForm;
import org.helianto.partner.form.PartnerCategoryForm;
import org.helianto.partner.form.PartnerForm;
import org.helianto.partner.form.PartnerPhoneForm;
import org.helianto.partner.form.PrivateAddressForm;
import org.helianto.partner.form.PrivateEntityForm;
import org.helianto.partner.form.PrivateEntityKeyForm;
import org.helianto.partner.repository.PartnerCategoryRepository;
import org.helianto.partner.repository.PartnerKeyRepository;
import org.helianto.partner.repository.PartnerPhoneRepository;
import org.helianto.partner.repository.PartnerRepository;
import org.helianto.partner.repository.PrivateAddressRepository;
import org.helianto.partner.repository.PrivateEntityKeyRepository;
import org.helianto.partner.repository.PrivateEntityRepository;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation for <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("partnerMgr")
public class PartnerMgrImpl implements PartnerMgr {

	@Transactional(readOnly=true)
	public List<PrivateEntity> findPrivateEntities(PrivateEntityForm form) {
		PrivateEntityFormFilterAdapter filter = new PrivateEntityFormFilterAdapter(form);
		List<PrivateEntity> privateEntityList = (List<PrivateEntity>) privateEntityRepository.find(filter);
    	if (logger.isDebugEnabled() && privateEntityList!=null) {
    		logger.debug("Found private entity list of size {}", privateEntityList.size());
    	}
		return privateEntityList;
	}

	@Transactional
	public PrivateEntity storePrivateEntity(PrivateEntity privateEntity) {
		privateEntity = privateEntityRepository.save(privateEntity);
		sequenceMgr.validateInternalNumber(privateEntity);
		Set<Partner> partners = privateEntity.getPartners();
		
		// create partner, if does not exist, or activate it
		Partner partner = null;
		for (String nature: privateEntity.getNatureAsArray()) {
			if (nature.equals("C")) {
				if (reviewPartner(partners, Customer.class)) {
					partner = partnerRepository.saveAndFlush(new Customer(privateEntity));
				}
			}
			else if (nature.equals("S")) {
				if (reviewPartner(partners, Supplier.class)) {
					partner = partnerRepository.saveAndFlush(new Supplier(privateEntity));
				}
			}
			else if (nature.equals("D")) {
				if (reviewPartner(partners, Division.class)) {
					partner = partnerRepository.saveAndFlush(new Division(privateEntity));
				}				
			}
			else if (nature.equals("A")) {
				if (reviewPartner(partners, Agent.class)) {
					partner = partnerRepository.saveAndFlush(new Agent(privateEntity));
				}
			}
			else if (nature.equals("L")) {
				if (reviewPartner(partners, Laboratory.class)) {
					partner = partnerRepository.saveAndFlush(new Laboratory(privateEntity));
				}
			}
			else if (nature.equals("M")) {
				if (reviewPartner(partners, Manufacturer.class)) {
					partner = partnerRepository.saveAndFlush(new Manufacturer(privateEntity));
				}
			}
			else if (nature.equals("T")) {
				if (reviewPartner(partners, TransportPartner.class)) {
					partner = partnerRepository.saveAndFlush(new TransportPartner(privateEntity));
				}
			}
			else if (nature.equals("E")) {
				if (reviewPartner(partners, EducationPartner.class)) {
					partner = partnerRepository.saveAndFlush(new EducationPartner(privateEntity));
				}
			}
		}
		logger.debug("Partner is {}.", partner);
		return privateEntity;
	}
	
	/**
	 * Review a set of partners and return true if it is necessary to create a new instance.
	 * 
	 * @param partners
	 * @param clazz
	 */
	protected boolean reviewPartner(Set<Partner> partners, Class<? extends Partner> clazz) {
		boolean create = true;
		for (Partner partner: partners) {
			if (partner.getClass().isAssignableFrom(clazz)) {
				logger.debug("{} exists.", partner);
				create = false;
				break;
			}
		}
		return create;
	}
	
	@Transactional
    public void removePrivateEntity(PrivateEntity privateEntity) {
    	privateEntityRepository.delete(privateEntity);
    }

	@Transactional(readOnly=true)
	public List<? extends Partner> findPartners(PartnerForm form) {
		PartnerFormFilterAdapter filter = new PartnerFormFilterAdapter(form);
		List<Partner> partnerList = (List<Partner>) partnerRepository.find(filter);
    	if (logger.isDebugEnabled() && partnerList!=null) {
    		logger.debug("Found partner list of size {}", partnerList.size());
    	}
		return partnerList;
	}
	
	@Transactional(readOnly=true)
	public List<? extends Partner> findPartners(Filter partnerFilter) {
		List<Partner> partnerList = (List<Partner>) partnerRepository.find(partnerFilter);
    	if (logger.isDebugEnabled() && partnerList!=null) {
    		logger.debug("Found partner list of size {}", partnerList.size());
    	}
		return partnerList;
	}
	
	@Transactional
	public Partner storePartner(Partner partner) {
		return partnerRepository.saveAndFlush(partner);
	}

	@Transactional
	public Partner storePartner(Partner partner, Entity entity) {
		if (partner.isNewPrivateEntityRequested(entity)) {
			return storePartner(partner);
		}
		throw new IllegalArgumentException("Unable to create partner: a private entity is required.");
	}

	@Transactional
	public void removePartner(Partner partner) {
		partnerRepository.delete(partner);
	}

	@Transactional(readOnly=true)
	public Map<String, PartnerKey> loadPartnerKeyMap(Partner partner) {
		Map<String, PartnerKey> partnerKeyMap = new HashMap<String, PartnerKey>();
		Set<PartnerKey> partnerKeys = partnerRepository.save(partner).getPartnerKeys();
		for (PartnerKey partnerKey: partnerKeys) {
			partnerKeyMap.put(partnerKey.getKeyType().getKeyCode(), partnerKey);
		}
		return partnerKeyMap;
	}

	@Transactional
	public PartnerKey storePartnerKey(PartnerKey partnerKey) {
		return partnerKeyRepository.saveAndFlush(partnerKey);
	}

	@Transactional
	public PrivateEntity removePartnerKey(PartnerKey partnerKey) {
		throw new IllegalArgumentException("Not yet implemented");
	}
	
	@Transactional(readOnly=true)
	public List<PartnerPhone> findPartnerPhones(PartnerPhoneForm form) {
		PartnerPhoneFormFilterAdapter filter = new PartnerPhoneFormFilterAdapter(form);
		List<PartnerPhone> partnerPhoneList = (List<PartnerPhone>) partnerPhoneRepository.find(filter);
    	if (logger.isDebugEnabled() && partnerPhoneList!=null) {
    		logger.debug("Found partner phone list of size {}", partnerPhoneList.size());
    	}
		return partnerPhoneList;
	}

	@Transactional
	public PartnerPhone storePartnerPhone(PartnerPhone phone) {
		return partnerPhoneRepository.saveAndFlush(phone);
	}

	@Transactional
	public PrivateEntity removePartnerPhone(PartnerPhone phone) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	@Transactional
	public Division installDivision(Entity entity, String entityName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PrivateEntity privateEntity = privateEntityRepository.findByEntityAndEntityAlias(entity, partnerAlias);
		Division division = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", partnerAlias);
			privateEntity = new PrivateEntity(entity, partnerAlias);
			privateEntity.setEntityName(entityName);
			AddressUtils.copyAddress(partnerAddress, privateEntity);
			Province province = provinceRepository.findByOperatorAndProvinceCode(entity.getOperator(), partnerAddress.getProvinceCode());
			if (province==null) {
				logger.error("A province was not found in database for {}, please, install first.", partnerAddress.getProvinceCode());
				throw new IllegalArgumentException("A province is required here.");
			}
			privateEntity.setProvince(province);
			privateEntity = privateEntityRepository.saveAndFlush(privateEntity);
		}
		else {
			division = (Division) partnerRepository.findByPrivateEntityAndType(privateEntity, 'D');
		}
		if (division==null) {
			logger.info("Creating division for {}.", partnerAlias);
			division = new Division(privateEntity);
			division.setPartnerStateAsEnum(PartnerState.ACTIVE);
			partnerRepository.saveAndFlush(division);
		}
		logger.info("Default division is {} ", division);
		return division;
	}
	
	@Transactional
	public Customer installCustomer(Entity entity, String entityName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PrivateEntity privateEntity = privateEntityRepository.findByEntityAndEntityAlias(entity, partnerAlias);
		Customer customer = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", partnerAlias);
			privateEntity = new PrivateEntity(entity, partnerAlias);
			privateEntity.setEntityName(entityName);
			AddressUtils.copyAddress(partnerAddress, privateEntity);
			Province province = provinceRepository.findByOperatorAndProvinceCode(entity.getOperator(), partnerAddress.getProvinceCode());
			if (province==null) {
				logger.error("A province was not found in database for {}, please, install first.", partnerAddress.getProvinceCode());
				throw new IllegalArgumentException("A province is required here.");
			}
			privateEntity.setProvince(partnerAddress.getProvince());
			privateEntity = privateEntityRepository.saveAndFlush(privateEntity);
		}
		else {
			customer = (Customer) partnerRepository.findByPrivateEntityAndType(privateEntity, 'C');
		}
		if (customer==null) {
			logger.info("Creating division for {}.", partnerAlias);
			customer = new Customer(privateEntity);
			customer.setPartnerStateAsEnum(PartnerState.ACTIVE);
			partnerRepository.saveAndFlush(customer);
		}
		logger.info("Default division is {} ", customer);
		return customer;
	}
	
	@Transactional
	public void installPartnerKeys(String[] keyValues, Partner partner) {
		logger.debug("Ready to install key value pairs {} to {}", keyValues, partner);
		Operator operator = partner.getPrivateEntity().getEntity().getOperator();
		List<KeyType> keyTypes = contextMgr.findKeyTypes(operator);
		Map<String, PartnerKey> partnerKeyMap = loadPartnerKeyMap(partner);
		for (String keyValueTuple: keyValues) {
			String[] keyValue = keyValueTuple.split(":");
			boolean keyNotAvailable = true;
			for (KeyType keyType: keyTypes) {
				if (keyValue.length>1 && keyValue[0].trim().equals(keyType.getKeyCode())) {
					PartnerKey partnerKey = null;
					if (partnerKeyMap.containsKey(keyType.getKeyCode())) {
						partnerKey = partnerKeyMap.get(keyType.getKeyCode());
						logger.debug("Partner key {} already existing.", partnerKey);
					}
					else {
						partnerKey = new PartnerKey(partner, keyType);
					}
					partnerKey.setKeyValue(keyValue[1]);
					partnerKeyRepository.save(partnerKey);
					keyNotAvailable = false;
					break;
				}
			}
			if(keyNotAvailable) {
				logger.warn("Unable to set key value {}", keyValueTuple);
			}
		}
		partnerKeyRepository.flush();
	}
	
	@Transactional(readOnly=true)
	public List<PrivateAddress> findPrivateAddresses(PrivateAddressForm form) {
		Filter filter = new PrivateAddressFormFilterAdapter(form);
		List<PrivateAddress> privateAddressList = (List<PrivateAddress>) privateAddressRepository.find(filter);
    	if (logger.isDebugEnabled() && privateAddressList!=null) {
    		logger.debug("Found private address list of size {}", privateAddressList.size());
    	}
		return privateAddressList;
	}
	
	@Transactional
	public PrivateAddress storePrivateAddress(PrivateAddress address) {
		return privateAddressRepository.saveAndFlush(address);
	}

	@Transactional
	public PrivateEntity removePrivateAddress(PrivateAddress address) {
		throw new IllegalArgumentException("Not yet implemented");
	}
	
	@Transactional(readOnly=true)
	public List<PrivateEntityKey> findPrivateEntityKeys(PrivateEntityKeyForm form) {
		Filter filter = new PrivateEntityKeyFormFilterAdapter(form);
		List<PrivateEntityKey> privateEntityKeyList = (List<PrivateEntityKey>) privateEntityKeyRepository.find(filter);
    	if (logger.isDebugEnabled() && privateEntityKeyList!=null) {
    		logger.debug("Found private entity key list of size {}", privateEntityKeyList.size());
    	}
		return privateEntityKeyList;
	}
	
	@Transactional
	public PrivateEntityKey storePrivateEntityKey(PrivateEntityKey privateEntityKey) {
		return privateEntityKeyRepository.saveAndFlush(privateEntityKey);
	}
	
	@Transactional(readOnly=true)
	public List<PartnerCategory> findPartnerCategories(PartnerCategoryForm form) {
		PartnerCategoryFormFilterAdapter filter = new PartnerCategoryFormFilterAdapter(form);
		List<PartnerCategory> partnerCategoryList = (List<PartnerCategory>) partnerCategoryRepository.find(filter);
    	if (logger.isDebugEnabled() && partnerCategoryList!=null) {
    		logger.debug("Found partner category list of size {}", partnerCategoryList.size());
    	}
		return partnerCategoryList;
	}

	@Transactional
	public PartnerCategory storePartnerCategory(PartnerCategory partnerCategory) {
		return partnerCategoryRepository.saveAndFlush(partnerCategory);
	}

	@Transactional(readOnly=true)
	public List<? extends UserGroup> findContactGroups(ContactGroupForm form) {
		Filter filter = new ContactGroupFormFilterAdapter(form);
		List<? extends UserGroup> contactGroupList = (List<? extends UserGroup>) userRepository.find(filter);
    	if (logger.isDebugEnabled() && contactGroupList!=null) {
    		logger.debug("Found contact group list of size {}", contactGroupList.size());
    	}
		return contactGroupList;
	}
	
    //- collaborators
    
    private PrivateEntityRepository privateEntityRepository;
    private PartnerRepository partnerRepository;
    private PrivateAddressRepository privateAddressRepository;
    private PrivateEntityKeyRepository privateEntityKeyRepository;
    private PartnerKeyRepository partnerKeyRepository;
    private ProvinceRepository provinceRepository;
    private PartnerPhoneRepository partnerPhoneRepository;
    private PartnerCategoryRepository partnerCategoryRepository;
    private UserRepository userRepository;
	private ContextMgr contextMgr;
	private SequenceMgr sequenceMgr;

    @Resource
    public void setPrivateEntityRepository(PrivateEntityRepository privateEntityRepository) {
		this.privateEntityRepository = privateEntityRepository;
	}

    @Resource
    public void setPartnerRepository(PartnerRepository partnerRepository) {
		this.partnerRepository = partnerRepository;
	}

    @Resource
    public void setPrivateAddressRepository(PrivateAddressRepository privateAddressRepository) {
		this.privateAddressRepository = privateAddressRepository;
	}

    @Resource
    public void setPrivateEntityKeyRepository(
			PrivateEntityKeyRepository privateEntityKeyRepository) {
		this.privateEntityKeyRepository = privateEntityKeyRepository;
	}

    @Resource
   public void setPartnerKeyRepository(PartnerKeyRepository partnerKeyRepository) {
	this.partnerKeyRepository = partnerKeyRepository;
}
    
    @Resource
    public void setProvinceRepository(ProvinceRepository provinceRepository) {
		this.provinceRepository = provinceRepository;
	}
    
    @Resource
    public void setPartnerPhoneRepository(PartnerPhoneRepository partnerPhoneRepository) {
		this.partnerPhoneRepository = partnerPhoneRepository;
	}
    
    @Resource
    public void setPartnerCategoryRepository(PartnerCategoryRepository partnerCategoryRepository) {
		this.partnerCategoryRepository = partnerCategoryRepository;
	}
    
    @Resource
    public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
	@Resource(name="namespaceMgr")
	public void setContextMgr(ContextMgr contextMgr) {
		this.contextMgr = contextMgr;
	}
	
	@Resource
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
    private Logger logger = LoggerFactory.getLogger(PartnerMgrImpl.class);

}
