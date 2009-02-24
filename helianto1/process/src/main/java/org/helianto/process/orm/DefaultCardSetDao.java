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


package org.helianto.process.orm;

import org.helianto.core.Entity;
import org.helianto.core.dao.AbstractFilterDao;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.process.CardSet;
import org.helianto.process.CardSetFilter;
import org.springframework.stereotype.Repository;


/**
 * Card set data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("cardSetDao")
public class DefaultCardSetDao extends AbstractFilterDao<CardSet, CardSetFilter> {

	@Override
	public Class<? extends CardSet> getClazz() {
		return CardSet.class;
	}

	@Override
	protected final void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("entity.id", "=")
        .append(entity.getId());
    }
    
	@Override
	protected void doFilter(CardSetFilter filter, CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	protected void doSelect(CardSetFilter filter, CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	protected boolean isSelection(CardSetFilter filter) {
		return filter.getInternalNumber()!=0;
	}

}
