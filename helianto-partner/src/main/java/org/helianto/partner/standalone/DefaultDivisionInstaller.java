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


package org.helianto.partner.standalone;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.standalone.DefaultEntityInstaller;
import org.helianto.core.standalone.NamespaceDefaults;
import org.helianto.partner.AbstractAddress;
import org.helianto.partner.Division;
import org.helianto.partner.DivisionType;
import org.helianto.partner.Partner;
import org.helianto.partner.PartnerRegistry;
import org.helianto.partner.PartnerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Convenient to install the default division if the namespace does not 
 * require multiple entities.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultDivisionInstaller implements InitializingBean {
	
	private String partnerName = "DEFAULT";
	private String provinceCode = "";
	private AbstractAddress partnerAddress;
	
	/**
	 * Empty constructor.
	 */
	public DefaultDivisionInstaller() {
		partnerAddress = new AbstractAddress() {};
	}

	public void afterPropertiesSet() throws Exception {
		boolean reinstall = defaultEntityInstaller.isReinstall();
		if (namespace.getDefaultEntity()==null) {
			throw new IllegalArgumentException("Unable to retrieve default entity from namespace defaults "+namespace);
		}
		if (namespace instanceof ExtendedNamespaceDefaults) {
			Province province = provinceDao.findUnique(namespace.getDefaultEntity().getOperator(), getProvinceCode());
			if (province==null) {
				throw new IllegalArgumentException("Requires valid province code");
			}
			getPartnerAddress().setProvince(province);
			Division defaultDivision = installDivision(namespace.getDefaultEntity(), getPartnerName(), getPartnerAddress(), reinstall);
			((ExtendedNamespaceDefaults) namespace).setDefaultDivision(defaultDivision);
		}
		else {
			throw new IllegalArgumentException("Requires extended namespace defaults");
		}
	}
	
	protected Division installDivision(Entity entity, String partnerName, AbstractAddress partnerAddress, boolean reinstall) {
		String partnerAlias = entity.getAlias();
		PartnerRegistry partnerRegistry = partnerRegistryDao.findUnique(entity, partnerAlias);
		Division defaultDivision = null;
		if (partnerRegistry==null) {
			logger.info("Creating registry for {}.", partnerAlias);
			partnerRegistry = PartnerRegistry.partnerRegistryFactory(entity, partnerAlias);
			partnerRegistry.setPartnerName(partnerName);
			partnerRegistry.setAddress1(partnerAddress.getAddress1());
			partnerRegistry.setAddress2(partnerAddress.getAddress2());
			partnerRegistry.setAddress3(partnerAddress.getAddress3());
			partnerRegistry.setAddressDetail(partnerAddress.getAddressDetail());
			partnerRegistry.setAddressNumber(partnerAddress.getAddressNumber());
			partnerRegistry.setCityName(partnerAddress.getCityName());
			partnerRegistry.setPostalCode(partnerAddress.getPostalCode());
			partnerRegistry.setPostOfficeBox(partnerAddress.getPostOfficeBox());
			partnerRegistry.setProvince(partnerAddress.getProvince());
			partnerRegistry = partnerRegistryDao.merge(partnerRegistry);
		}
		else {
			defaultDivision = (Division) partnerDao.findUnique(partnerRegistry, "D");
		}
		if (defaultDivision==null) {
			logger.info("Creating division for {}.", partnerAlias);
			defaultDivision = new Division();
			defaultDivision.setPartnerRegistry(partnerRegistry);
			defaultDivision.setDivisionType(DivisionType.HEADQUARTER);
			defaultDivision.setPartnerState(PartnerState.ACTIVE);
			defaultDivision = (Division) partnerDao.merge(defaultDivision);
		}
		logger.info("Default division is {} ", defaultDivision);
		return defaultDivision;
	}
	
	/**
	 * Name to apply to the default division.
	 */
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	
	/**
	 * Province code.
	 */
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	/**
	 * Partner address.
	 */
	public AbstractAddress getPartnerAddress() {
		return partnerAddress;
	}
	public void setPartnerAddress(AbstractAddress partnerAddress) {
		this.partnerAddress = partnerAddress;
	}
	
	// collabs
	private NamespaceDefaults namespace;
	private DefaultEntityInstaller defaultEntityInstaller;
	private FilterDao<Province, ProvinceFilter> provinceDao;
	private BasicDao<PartnerRegistry> partnerRegistryDao;
	private BasicDao<Partner> partnerDao; 

	@Resource
	public void setNamespace(NamespaceDefaults namespace) {
		this.namespace = namespace;
	}
	
	@javax.annotation.Resource(name="defaultEntityInstaller")
	public void setDefaultEntityInstaller(DefaultEntityInstaller defaultEntityInstaller) {
		this.defaultEntityInstaller = defaultEntityInstaller;
	}

	@javax.annotation.Resource(name="provinceDao")
	public void setProvinceDao(FilterDao<Province, ProvinceFilter> provinceDao) {
		this.provinceDao = provinceDao;
	}
	
	@javax.annotation.Resource(name="partnerRegistryDao")
	public void setPartnerRegistryDao(BasicDao<PartnerRegistry> partnerRegistryDao) {
		this.partnerRegistryDao = partnerRegistryDao;
	}

	@javax.annotation.Resource(name="partnerDao")
	public void setPartnerDao(BasicDao<Partner> partnerDao) {
		this.partnerDao = partnerDao;
	}

	private static final Logger logger = LoggerFactory.getLogger(DefaultDivisionInstaller.class);

}
