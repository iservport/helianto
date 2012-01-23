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

package org.helianto.partner.repository;

import org.helianto.core.repository.FilterDao;
import org.helianto.core.repository.base.AbstractRepositoryConfiguration;
import org.helianto.partner.domain.Account;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PartnerCategory;
import org.helianto.partner.domain.PartnerKey;
import org.helianto.partner.domain.PartnerPhone;
import org.helianto.partner.domain.PrivateAddress;
import org.helianto.partner.domain.PrivateEntity2;
import org.helianto.partner.domain.PrivateEntityKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java config to the repository.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Configuration
public class PartnerRepositoryConfiguration extends AbstractRepositoryConfiguration {
		
	/**
	 * Account data access.
	 */
	@Bean
	public FilterDao<Account> accountDao() {
		return getFilterDao(Account.class, "entity", "accountCode");
	}

	/**
	 * Address data access.
	 */
	@Bean
	public FilterDao<PrivateAddress> addressDao() {
		return getFilterDao(PrivateAddress.class, "privateEntity", "sequence");
	}

	/**
	 * Partner data access.
	 */
	@Bean
	public FilterDao<Partner> partnerDao() {
		return getFilterDao(Partner.class, "privateEntity", "class");
	}

	/**
	 * Partner key data access.
	 */
	@Bean
	public FilterDao<PartnerKey> partnerKeyDao() {
		return getFilterDao(PartnerKey.class, "partner", "keyType");
	}

	/**
	 * Private entity data access.
	 */
	@Bean
	public FilterDao<PrivateEntity2> privateEntityDao() {
		return getFilterDao(PrivateEntity2.class, "entity", "entityAlias");
	}

	/**
	 * Private entity key data access.
	 */
	@Bean
	public FilterDao<PrivateEntityKey> privateEntityKeyDao() {
		return getFilterDao(PrivateEntityKey.class, "privateEntity", "keyType");
	}

	/**
	 * Phone data access.
	 */
	@Bean
	public FilterDao<PartnerPhone> partnerPhoneDao() {
		return getFilterDao(PartnerPhone.class, "privateEntity", "sequence");
	}

	/**
	 * Partner category data access.
	 */
	@Bean
	public FilterDao<PartnerCategory> partnerCategoryDao() {
		return getFilterDao(PartnerCategory.class, "partner", "category");
	}

}
