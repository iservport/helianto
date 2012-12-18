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


package org.helianto.bootstrap.config;

import java.util.List;

import org.helianto.bootstrap.PartnerInstallationMgr;
import org.helianto.core.ContextMgr;
import org.helianto.core.base.AbstractAddress;
import org.helianto.core.domain.Province;
import org.helianto.core.form.CompositeContextForm;
import org.helianto.core.form.ProvinceForm;
import org.helianto.partner.domain.nature.Division;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Convenient to install the default division if the namespace does not 
 * require multiple entities.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultDivisionInstaller 
	extends AbstractAddress
	implements InitializingBean {
	
	private static final long serialVersionUID = 1L;
	private String partnerName = "DEFAULT";
	private String provinceCode = "";
	private String[] keyValues;
	
	protected ContextDefaults namespace;
	private DefaultEntityInstaller defaultEntityInstaller;
	private ContextMgr namespaceMgr;
	private PartnerInstallationMgr partnerInstallationMgr; 

	/**
	 * Empty constructor.
	 */
	public DefaultDivisionInstaller(ContextDefaults namespace,
			DefaultEntityInstaller defaultEntityInstaller,
			ContextMgr namespaceMgr,
			PartnerInstallationMgr partnerInstallationMgr) {
		this.namespace = namespace;
		this.defaultEntityInstaller = defaultEntityInstaller;
		this.namespaceMgr = namespaceMgr;
		this.partnerInstallationMgr = partnerInstallationMgr;
	}

	public void afterPropertiesSet() throws Exception {
		boolean reinstall = defaultEntityInstaller.isReinstall();
		if (namespace.getDefaultEntity()==null) {
			throw new IllegalArgumentException("Unable to retrieve default entity from namespace defaults "+namespace);
		}
		if (!(namespace instanceof ExtendedNamespaceDefaults)) {
			throw new IllegalArgumentException("Requires extended namespace defaults");
		}
		ProvinceForm provinceForm = new CompositeContextForm(namespace.getDefaultEntity().getOperator(), getProvinceCode());
		List<Province> provinceList = namespaceMgr.findProvinces(provinceForm);
		Province province = null;
		if (provinceList!=null && provinceList.size()>0) {
			province = provinceList.get(0);
		}
		if (province==null) {
			throw new IllegalArgumentException("Requires valid province or city code");
		}
		setProvince(province);
		logger.debug("Default division province is {}.", province.getProvinceCode());
		logger.debug("Ready to install default division with allias {} and name {}.", namespace.getDefaultEntity(), getPartnerName());
		Division defaultDivision = partnerInstallationMgr.installDivision(namespace.getDefaultEntity(), getPartnerName(), this, reinstall);
		((ExtendedNamespaceDefaults) namespace).setDefaultDivision(defaultDivision);
		logger.debug("Default division is {}.", defaultDivision);

		if (getKeyValues()!=null) {
			partnerInstallationMgr.installPartnerKeys(getKeyValues(), defaultDivision);
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
	 * Key value list, formatted as key1:value1, key2:value2...
	 */
	public String[] getKeyValues() {
		return keyValues;
	}
	public void setKeyValues(String[] keyValues) {
		this.keyValues = keyValues;
	}

	private static final Logger logger = LoggerFactory.getLogger(DefaultDivisionInstaller.class);
	
}
