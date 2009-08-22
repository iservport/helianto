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

package org.helianto.core.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.helianto.core.Category;
import org.helianto.core.CategoryFilter;
import org.helianto.core.Country;
import org.helianto.core.CountryFilter;
import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.IdentityFilter;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.OperatorFilter;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;
import org.helianto.core.Server;
import org.helianto.core.ServerFilter;
import org.helianto.core.Service;
import org.helianto.core.ServiceFilter;
import org.helianto.core.dao.BasicDao;
import org.helianto.core.dao.FilterDao;
import org.helianto.core.test.AbstractDaoIntegrationTest;
import org.helianto.core.test.CategoryTestSupport;
import org.helianto.core.test.CountryTestSupport;
import org.helianto.core.test.CredentialTestSupport;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.InternalEnumeratorTestSupport;
import org.helianto.core.test.KeyTypeTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.ProvinceTestSupport;
import org.helianto.core.test.ServerTestSupport;
import org.helianto.core.test.ServiceTestSupport;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
@Transactional
public class CoreIntegrationTest extends AbstractDaoIntegrationTest {

	@Resource FilterDao<Category, CategoryFilter> categoryDao;
	@Test
	public void category() {
		Category target = CategoryTestSupport.createCategory();
		assertEquals(categoryDao.merge(target), categoryDao.findUnique(target.getEntity(), target.getCategoryGroup(), target.getCategoryCode()));
	}
	
	@Resource FilterDao<Country, CountryFilter> countryDao;
	@Test
	public void country() {
		Country target = CountryTestSupport.createCountry();
		assertEquals(countryDao.merge(target), countryDao.findUnique(target.getOperator(), target.getCountryCode()));
	}
	
	@Resource BasicDao<Credential> credentialDao;
	@Test
	public void credential() {
		Credential target = CredentialTestSupport.createCredential();
		assertEquals(credentialDao.merge(target), credentialDao.findUnique(target.getIdentity()));
	}
	
	@Resource FilterDao<Identity, IdentityFilter> identityDao;
	@Test
	public void identity() {
		Identity target = IdentityTestSupport.createIdentity();
		assertEquals(identityDao.merge(target), identityDao.findUnique(target.getPrincipal()));
	}

	@Resource BasicDao<InternalEnumerator> internalEnumeratorDao;
	@Test
	public void internalEnumerator() {
		InternalEnumerator target = InternalEnumeratorTestSupport.createInternalEnumerator();
		assertEquals(internalEnumeratorDao.merge(target), internalEnumeratorDao.findUnique(target.getEntity(), target.getTypeName()));
	}

	@Resource BasicDao<KeyType> keyTypeDao;
	@Test
	public void keyTypeDao() {
		KeyType target = KeyTypeTestSupport.createKeyType();
		assertEquals(keyTypeDao.merge(target), keyTypeDao.findUnique(target.getOperator(), target.getKeyCode()));
	}

	@Resource FilterDao<Operator, OperatorFilter> operatorDao;
	@Test
	public void operator() {
		Operator target = OperatorTestSupport.createOperator();
		assertEquals(operatorDao.merge(target), operatorDao.findUnique(target.getOperatorName()));
	}
	
	@Resource FilterDao<Province, ProvinceFilter> provinceDao;
	@Test
	public void province() {
		Province target = ProvinceTestSupport.createProvince();
		assertEquals(provinceDao.merge(target), provinceDao.findUnique(target.getOperator(), target.getProvinceCode()));
	}
	
	@Resource FilterDao<Server, ServerFilter> serverDao;
	@Test
	public void server() {
		Server target = ServerTestSupport.createServer();
		assertEquals(serverDao.merge(target), serverDao.findUnique(target.getOperator(), target.getServerName()));
	}
	
	@Resource FilterDao<Service, ServiceFilter> serviceDao;
	@Test
	public void service() {
		Service target = ServiceTestSupport.createService();
		assertEquals(serviceDao.merge(target), serviceDao.findUnique(target.getOperator(), target.getServiceName()));
	}
	
}
