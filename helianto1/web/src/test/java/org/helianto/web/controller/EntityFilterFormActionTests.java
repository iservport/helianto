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


package org.helianto.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.helianto.core.UserFilter;
import org.helianto.core.UserState;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.SecureUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.test.IdentityTestSupport;
import org.helianto.core.test.SecurityTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.webflow.test.MockRequestContext;


/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityFilterFormActionTests {
	
	@Test
	public void testPreProcessExpiredCredential() throws Exception {
		publicUserDetails.getCredential().setExpirationDate(new Date(Long.MIN_VALUE));
		assertFalse(entityFilterFormAction2.doPreProcess(filter, context));
		SecureUserDetails secureUser = (SecureUserDetails) context.getConversationScope().get("secureUser");
		assertNotNull(secureUser);
		assertTrue(secureUser instanceof SecureUserDetails);
		assertEquals(UserState.ACTIVE.getValue(), filter.getUserState());
	}
	
	@Test
	public void testPreProcessNotExpired() throws Exception {
		publicUserDetails.getCredential().setExpirationDate(new Date(Long.MAX_VALUE));
		assertTrue(entityFilterFormAction2.doPreProcess(filter, context));
		SecureUserDetails currentSecureUser = (SecureUserDetails) context.getConversationScope().get("secureUser");
		assertNotNull(currentSecureUser);
		assertTrue(currentSecureUser instanceof SecureUserDetails);
		assertEquals(UserState.ACTIVE.getValue(), filter.getUserState());
	}
	
	EntityFilterFormAction2 entityFilterFormAction2;
	MockRequestContext context;
	UserFilter filter;
	UserDetailsAdapter publicUserDetails;
	
	@Before
	public void setUp() {
		entityFilterFormAction2 = new EntityFilterFormAction2() {
			@Override
			protected PublicUserDetails getPublicUserDetails() {
				return publicUserDetails;
			}
		};
		context = new MockRequestContext();
		filter = UserFilter.userFilterFactory(IdentityTestSupport.createIdentity());
		publicUserDetails = SecurityTestSupport.createUserDetailsAdapter();
	}

}
