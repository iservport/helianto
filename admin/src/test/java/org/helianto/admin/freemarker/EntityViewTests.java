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

import org.helianto.core.Entity;
import org.helianto.core.EntityFilter;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.core.test.OperatorTestSupport;
import org.helianto.web.test.AbstractViewTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class EntityViewTests extends AbstractViewTest<EntityFilter, Entity> {

	@Override
	protected EntityFilter createFilter() {
		return EntityFilter.entityFilterFactory(null);
	}

	@Override
	protected Entity createTarget() {
		return EntityTestSupport.createEntity();
	}

	@Override
	protected List<Entity> getList() {
		return EntityTestSupport.createEntityList(5);
	}

	@Override
	protected String getBase() {
		return "entity";
	}

	// by default, assign is "/assign"
	@Override
	protected String getAssign() {
		return null;
	}
	
	// by default, info is null
	@Override
	protected String getInfo() {
		return "/info";
	}
	
	protected void addToModel() {
		model.put("operator", OperatorTestSupport.createOperator("DEFAULT"));
	}
	
	@Override
	protected boolean visualTest() {
		return false;
	}

}
