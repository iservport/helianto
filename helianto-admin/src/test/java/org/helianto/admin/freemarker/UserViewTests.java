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


package org.helianto.admin.freemarker;

import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.filter.classic.UserFilter;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.core.test.UserTestSupport;
import org.helianto.web.test.AbstractViewTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserViewTests extends AbstractViewTest<UserFilter, User> {

	@Override
	protected UserFilter createFilter() {
		return new UserFilter();
	}

	@Override
	protected User createTarget() {
		return UserTestSupport.createUser();
	}

	@Override
	protected String getBase() {
		return "user";
	}

	@Override
	protected List<User> getList() {
		return UserTestSupport.createUserList(5);
	}

	protected void addToModel() {
		Operator operator = OperatorTestSupport.createOperator("DEFAULT");
		model.put("operator", operator);
		model.put("entity", EntityTestSupport.createEntity(operator, "ENTITY"));
	}
	
	@Override
	protected boolean visualTest() {
		return false;
	}

}
