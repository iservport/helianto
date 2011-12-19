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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.KeyTypeFilterAdapter;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.core.service.SequenceMgr;
import org.helianto.core.utils.AddressUtils;
import org.helianto.partner.PartnerState;
import org.helianto.partner.domain.Partner;
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
import org.helianto.partner.filter.PartnerFormFilterAdapter;
import org.helianto.partner.filter.PrivateAddressFormFilterAdapter;
import org.helianto.partner.filter.PrivateEntityFormFilterAdapter;
import org.helianto.partner.filter.PrivateEntityKeyFormFilterAdapter;
import org.helianto.partner.form.PartnerForm;
import org.helianto.partner.form.PrivateAddressForm;
import org.helianto.partner.form.PrivateEntityForm;
import org.helianto.partner.form.PrivateEntityKeyForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default implementation for <code>PartnerMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("partnerMgr")
public class PartnerMgrImpl implements PartnerMgr {

	public List<PrivateEntity> findPrivateEntities(PrivateEntityForm form) {
		PrivateEntityFormFilterAdapter filter = new PrivateEntityFormFilterAdapter(form);
		List<PrivateEntity> privateEntityList = (List<PrivateEntity>) privateEntityDao.find(filter);
    	if (logger.isDebugEnabled() && privateEntityList!=null) {
    		logger.debug("Found private entity list of size {}", privateEntityList.size());
    	}
		return privateEntityList;
	}

	/**
	 * @deprecated
	 */
	public List<PrivateEntity> findPrivateEntities(Filter privateEntityFilter) {
		List<PrivateEntity> privateEntityList = (List<PrivateEntity>) privateEntityDao.find(privateEntityFilter);
    	if (logger.isDebugEnabled() && privateEntityList!=null) {
    		logger.debug("Found private entity list of size {}", privateEntityList.size());
    	}
		return privateEntityList;
	}

	public PrivateEntity preparePrivateEntity(PrivateEntity privateEntity) {
		PrivateEntity managedPartnerRegistry = privateEntityDao.merge(privateEntity);
		managedPartnerRegistry.setPartnerList(new ArrayList<Partner>(managedPartnerRegistry.getPartners()));
		managedPartnerRegistry.setAddressList(new ArrayList<PrivateAddress>(managedPartnerRegistry.getAddresses()));
		managedPartnerRegistry.setPartnerRegistryKeyList(new ArrayList<PrivateEntityKey>(managedPartnerRegistry.getPartnerRegistryKeys()));
		privateEntityDao.evict(managedPartnerRegistry);
		return managedPartnerRegistry;
	}
	
	public PrivateEntity storePrivateEntity(PrivateEntity privateEntity) {
		privateEntityDao.saveOrUpdate(privateEntity);
		sequenceMgr.validateInternalNumber(privateEntity);
		Set<Partner> partners = privateEntity.getPartners();
		
		// create partner, if does not exist, or activate it
		for (String nature: privateEntity.getNatureAsArray()) {
			if (nature.equals("C")) {
				if (reviewPartner(partners, Customer.class)) {
					Customer customer = new Customer(privateEntity);
					partnerDao.saveOrUpdate(customer);
				}
			}
			else if (nature.equals("S")) {
				if (reviewPartner(partners, Supplier.class)) {
					Supplier supplier = new Supplier(privateEntity);
					partnerDao.saveOrUpdate(supplier);
				}
			}
			else if (nature.equals("D")) {
				if (reviewPartner(partners, Division.class)) {
					Division division = new Division(privateEntity);
					partnerDao.saveOrUpdate(division);
				}				
			}
			else if (nature.equals("A")) {
				if (reviewPartner(partners, Agent.class)) {
					Agent agent = new Agent(privateEntity);
					partnerDao.saveOrUpdate(agent);
				}
			}
			else if (nature.equals("L")) {
				if (reviewPartner(partners, Laboratory.class)) {
					Laboratory laboratory = new Laboratory(privateEntity);
					partnerDao.saveOrUpdate(laboratory);
				}
			}
			else if (nature.equals("M")) {
				if (reviewPartner(partners, Manufacturer.class)) {
					Manufacturer manufacturer = new Manufacturer(privateEntity);
					partnerDao.saveOrUpdate(manufacturer);
				}
			}
			else if (nature.equals("T")) {
				if (reviewPartner(partners, TransportPartner.class)) {
					TransportPartner transportPartner = new TransportPartner(privateEntity);
					partnerDao.saveOrUpdate(transportPartner);
				}
			}
			else if (nature.equals("E")) {
				if (reviewPartner(partners, EducationPartner.class)) {
					EducationPartner educationPartner = new EducationPartner(privateEntity);
					partnerDao.saveOrUpdate(educationPartner);
				}
			}
		}
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
	
    public void removePrivateEntity(PrivateEntity privateEntity) {
    	privateEntityDao.remove(privateEntity);
    }

	public List<? extends Partner> findPartners(PartnerForm form) {
		PartnerFormFilterAdapter filter = new PartnerFormFilterAdapter(form);
		List<Partner> partnerList = (List<Partner>) partnerDao.find(filter);
    	if (logger.isDebugEnabled() && partnerList!=null) {
    		logger.debug("Found partner list of size {}", partnerList.size());
    	}
		return partnerList;
	}
	
	public List<? extends Partner> findPartners(Filter partnerFilter) {
		List<Partner> partnerList = (List<Partner>) partnerDao.find(partnerFilter);
    	if (logger.isDebugEnabled() && partnerList!=null) {
    		logger.debug("Found partner list of size {}", partnerList.size());
    	}
		return partnerList;
	}
	
	public Partner storePartner(Partner partner) {
		partnerDao.saveOrUpdate(partner);
		return partner;
	}

	public Partner storePartner(Partner partner, Entity entity) {
		if (partner.isNewPrivateEntityRequested(entity)) {
			return storePartner(partner);
		}
		throw new IllegalArgumentException("Unable to create partner: a private entity is required.");
	}

	public void removePartner(Partner partner) {
		partnerDao.remove(partner);
	}

	public Map<String, PartnerKey> loadPartnerKeyMap(Partner partner) {
		Map<String, PartnerKey> partnerKeyMap = new HashMap<String, PartnerKey>();
		Set<PartnerKey> partnerKeys = partnerDao.merge(partner).getPartnerKeys();
		for (PartnerKey partnerKey: partnerKeys) {
			partnerKeyMap.put(partnerKey.getKeyType().getKeyCode(), partnerKey);
		}
		return partnerKeyMap;
	}

	public PartnerKey storePartnerKey(PartnerKey partnerKey) {
		partnerDao.saveOrUpdate(partnerKey.getPartner());
		partnerKeyDao.saveOrUpdate(partnerKey);
		return partnerKey;
	}

	public PrivateEntity removePartnerKey(PartnerKey partnerKey) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	public PartnerPhone storePhone(PartnerPhone phone) {
		return phoneDao.merge(phone);
	}

	public PrivateEntity removePhone(PartnerPhone phone) {
		throw new IllegalArgumentException("Not yet implemented");
	}

	public Division installDivision(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PrivateEntity privateEntity = privateEntityDao.findUnique(entity, partnerAlias);
		Division division = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", partnerAlias);
			privateEntity = new PrivateEntity(entity, partnerAlias);
			privateEntity.setPartnerName(partnerName);
			AddressUtils.copyAddress(partnerAddress, privateEntity);
			Province province = provinceDao.findUnique(entity.getOperator(), partnerAddress.getProvinceCode());
			if (province==null) {
				logger.error("A province was not found in database for {}, please, install first.", partnerAddress.getProvinceCode());
				throw new IllegalArgumentException("A province is required here.");
			}
			privateEntity.setProvince(province);
			privateEntityDao.saveOrUpdate(privateEntity);
		}
		else {
			division = (Division) partnerDao.findUnique(privateEntity, "D");
		}
		if (division==null) {
			logger.info("Creating division for {}.", partnerAlias);
			division = new Division(privateEntity);
			division.setPartnerStateAsEnum(PartnerState.ACTIVE);
			partnerDao.saveOrUpdate(division);
		}
		logger.info("Default division is {} ", division);
		partnerDao.flush();
		return division;
	}
	
	public Customer installCustomer(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PrivateEntity privateEntity = privateEntityDao.findUnique(entity, partnerAlias);
		Customer customer = null;
		if (privateEntity==null) {
			logger.info("Creating private entity for {}.", partnerAlias);
			privateEntity = new PrivateEntity(entity, partnerAlias);
			privateEntity.setPartnerName(partnerName);
			AddressUtils.copyAddress(partnerAddress, privateEntity);
			Province province = provinceDao.findUnique(entity.getOperator(), partnerAddress.getProvinceCode());
			if (province==null) {
				logger.error("A province was not found in database for {}, please, install first.", partnerAddress.getProvinceCode());
				throw new IllegalArgumentException("A province is required here.");
			}
			privateEntity.setProvince(partnerAddress.getProvince());
			privateEntityDao.saveOrUpdate(privateEntity);
		}
		else {
			customer = (Customer) partnerDao.findUnique(privateEntity, "D");
		}
		if (customer==null) {
			logger.info("Creating division for {}.", partnerAlias);
			customer = new Customer(privateEntity);
			customer.setPartnerStateAsEnum(PartnerState.ACTIVE);
			partnerDao.saveOrUpdate(customer);
		}
		logger.info("Default division is {} ", customer);
		partnerDao.flush();
		return customer;
	}
	
	public void installPartnerKeys(String[] keyValues, Partner partner) {
		logger.debug("Ready to install key value pairs {} to {}", keyValues, partner);
		Operator operator = partner.getPrivateEntity().getEntity().getOperator();
		List<KeyType> keyTypes = namespaceMgr.findKeyTypes(new KeyTypeFilterAdapter(operator, ""));
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
					partnerKeyDao.saveOrUpdate(partnerKey);
					keyNotAvailable = false;
					break;
				}
			}
			if(keyNotAvailable) {
				logger.warn("Unable to set key value {}", keyValueTuple);
			}
		}
		partnerKeyDao.flush();
	}
	
	public List<PrivateAddress> findPrivateAddresses(PrivateAddressForm form) {
		Filter filter = new PrivateAddressFormFilterAdapter(form);
		List<PrivateAddress> privateAddressList = (List<PrivateAddress>) addressDao.find(filter);
    	if (logger.isDebugEnabled() && privateAddressList!=null) {
    		logger.debug("Found private address list of size {}", privateAddressList.size());
    	}
		return privateAddressList;
	}
	
	public PrivateAddress storePrivateAddress(PrivateAddress address) {
		addressDao.saveOrUpdate(address);
		return address;
	}

	public PrivateEntity removePrivateAddress(PrivateAddress address) {
		throw new IllegalArgumentException("Not yet implemented");
	}
	
	public List<PrivateEntityKey> findPrivateEntityKeys(PrivateEntityKeyForm form) {
		Filter filter = new PrivateEntityKeyFormFilterAdapter(form);
		List<PrivateEntityKey> privateEntityKeyList = (List<PrivateEntityKey>) privateEntityKeyDao.find(filter);
    	if (logger.isDebugEnabled() && privateEntityKeyList!=null) {
    		logger.debug("Found private entity key list of size {}", privateEntityKeyList.size());
    	}
		return privateEntityKeyList;
	}
	
	public PrivateEntityKey storePrivateEntityKey(PrivateEntityKey privateEntityKey) {
		privateEntityKeyDao.saveOrUpdate(privateEntityKey);
		return privateEntityKey;
	}

    //- collaborators
    
    private FilterDao<PrivateEntity> privateEntityDao;
    private FilterDao<Partner> partnerDao;
    private FilterDao<PrivateAddress> addressDao;
    private FilterDao<PrivateEntityKey> privateEntityKeyDao;
    private FilterDao<PartnerKey> partnerKeyDao;
    private FilterDao<Province> provinceDao;
    private FilterDao<PartnerPhone> phoneDao;
	private NamespaceMgr namespaceMgr;
	private SequenceMgr sequenceMgr;

    @Resource(name="privateEntityDao")
    public void setPrivateEntityDao(FilterDao<PrivateEntity> privateEntityDao) {
        this.privateEntityDao = privateEntityDao;
    }

    @Resource(name="partnerDao")
    public void setPartnerDao(FilterDao<Partner> partnerDao) {
        this.partnerDao = partnerDao;
    }

    @Resource(name="addressDao")
    public void setAddressDao(FilterDao<PrivateAddress> addressDao) {
        this.addressDao = addressDao;
    }

    @Resource(name="privateEntityKeyDao")
    public void setPrivateEntityKeyDao(FilterDao<PrivateEntityKey> privateEntityKeyDao) {
		this.privateEntityKeyDao = privateEntityKeyDao;
	}

    @Resource(name="partnerKeyDao")
    public void setPartnerKeyDao(FilterDao<PartnerKey> partnerKeyDao) {
        this.partnerKeyDao = partnerKeyDao;
    }
    
    @Resource(name="provinceDao")
    public void setProvinceDao(FilterDao<Province> provinceDao) {
		this.provinceDao = provinceDao;
	}
    
    @Resource(name="phoneDao")
    public void setPhoneDao(FilterDao<PartnerPhone> phoneDao) {
        this.phoneDao = phoneDao;
    }
    
	@Resource(name="namespaceMgr")
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
	
	@Resource(name="sequenceMgr")
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}
	
    private Logger logger = LoggerFactory.getLogger(PartnerMgrImpl.class);

}
