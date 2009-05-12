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

import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.test.UserGroupTestSupport;
import org.helianto.web.test.AbstractViewTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class UserViewTests extends AbstractViewTest<UserFilter, UserGroup> {

	@Override
	protected UserFilter createFilter() {
		return new UserFilter();
	}

	@Override
	protected UserGroup createTarget() {
		return UserGroupTestSupport.createUserGroup();
	}

	@Override
	protected String getBase() {
		return "user";
	}

	@Override
	protected List<UserGroup> getList() {
		return UserGroupTestSupport.createUserGroupList(5);
	}

	@Override
	protected boolean visualTest() {
		return false;
	}

}
