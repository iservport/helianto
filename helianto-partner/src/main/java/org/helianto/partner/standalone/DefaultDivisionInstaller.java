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

import org.helianto.core.Province;
import org.helianto.core.service.NamespaceMgr;
import org.helianto.core.standalone.DefaultEntityInstaller;
import org.helianto.core.standalone.NamespaceDefaults;
import org.helianto.partner.AbstractAddress;
import org.helianto.partner.Division;
import org.helianto.partner.service.PartnerMgr;
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
	private String[] keyValues;
	
	/**
	 * Empty constructor.
	 */
	@SuppressWarnings("serial")
	public DefaultDivisionInstaller() {
		partnerAddress = new AbstractAddress() {};
	}

	public void afterPropertiesSet() throws Exception {
		boolean reinstall = defaultEntityInstaller.isReinstall();
		if (namespace.getDefaultEntity()==null) {
			throw new IllegalArgumentException("Unable to retrieve default entity from namespace defaults "+namespace);
		}
		if (!(namespace instanceof ExtendedNamespaceDefaults)) {
			throw new IllegalArgumentException("Requires extended namespace defaults");
		}
		Province province = namespaceMgr.findProvince(namespace.getDefaultEntity().getOperator(), getProvinceCode());
		if (province==null) {
			throw new IllegalArgumentException("Requires valid province or city code");
		}
		getPartnerAddress().setProvince(province);
		logger.debug("Default division province is {}.", province.getProvinceCode());
		logger.debug("Ready to install default division with allias {} and name {}.", namespace.getDefaultEntity(), getPartnerName());
		Division defaultDivision = partnerMgr.installDivision(namespace.getDefaultEntity(), getPartnerName(), getPartnerAddress(), reinstall);
		((ExtendedNamespaceDefaults) namespace).setDefaultDivision(defaultDivision);
		logger.debug("Default division is {}.", defaultDivision);

		if (getKeyValues()!=null) {
			partnerMgr.installPartnerKeys(getKeyValues(), defaultDivision);
		}
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
	
	/**
	 * Key value list, formatted as key1:value1, key2:value2...
	 */
	public String[] getKeyValues() {
		return keyValues;
	}
	public void setKeyValues(String[] keyValues) {
		this.keyValues = keyValues;
	}

	// collabs
	protected NamespaceDefaults namespace;
	private DefaultEntityInstaller defaultEntityInstaller;
	private NamespaceMgr namespaceMgr;
	private PartnerMgr partnerMgr; 

	@Resource
	public void setNamespace(NamespaceDefaults namespace) {
		this.namespace = namespace;
	}
	
	@javax.annotation.Resource(name="defaultEntityInstaller")
	public void setDefaultEntityInstaller(DefaultEntityInstaller defaultEntityInstaller) {
		this.defaultEntityInstaller = defaultEntityInstaller;
	}

	@javax.annotation.Resource(name="namespaceMgr")
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}
	
	@javax.annotation.Resource(name="partnerMgr")
	public void setPartnerMgr(PartnerMgr partnerMgr) {
		this.partnerMgr = partnerMgr;
	}

	private static final Logger logger = LoggerFactory.getLogger(DefaultDivisionInstaller.class);
	
}
