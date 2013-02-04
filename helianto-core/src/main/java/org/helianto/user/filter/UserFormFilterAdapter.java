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

package org.helianto.user.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.criteria.SelectFromBuilder;
import org.helianto.core.domain.Identity;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.form.UserGroupForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Form filter adapter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserFormFilterAdapter 
	extends AbstractTrunkFilterAdapter<UserGroupForm> {

    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     * 
     * @param form
     */
    public UserFormFilterAdapter(UserGroupForm form) {
    	super(form);
    }
    
    /**
     * If parent is not null, select also from inner join parent.
     */
	@Override
	public String createSelectAsString() {
		if (hasParentCriterion() | (getForm().getParentUserKey()!=null && getForm().getParentUserKey().length()>0)) {
			SelectFromBuilder selectFromBuilder = new SelectFromBuilder(UserGroup.class, getObjectAlias());
			selectFromBuilder.createSelectFrom().appendParentInnerJoin();
			return selectFromBuilder.getAsString();
		}
		return null;
	}
	

	@Override
	protected boolean hasParentCriterion() {
		return getForm().getUserGroupParentId()>0;
	}
	
	@Override
	protected void preProcessParentFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendAnd().append("parentAssociations.parent.id =").append(getForm().getUserGroupParentId());
		mainCriteriaBuilder.addSegmentCount(1);
	}

	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = super.preProcessFilter(mainCriteriaBuilder);
		if (getForm().getUserGroupType()!=0 && getForm().getUserGroupType()!=' ' && getForm().getUserGroupType()!='_') {
			mainCriteriaBuilder.appendAnd().appendSegment("class", "=").append(getForm().getUserGroupType());
			mainCriteriaBuilder.addSegmentCount(1);
			connect = true;
		}
		if (getForm().getParentUserKey()!=null && getForm().getParentUserKey().length()>0) {
			mainCriteriaBuilder.appendAnd().append("parentAssociations.parent.userKey =").appendString(getForm().getParentUserKey());
			mainCriteriaBuilder.addSegmentCount(1);
			connect = true;
		}
		if (getForm().getIdentity()!=null && getForm().getIdentity().getId()>0) {
			mainCriteriaBuilder.appendAnd().appendSegment("identity.id", "=").append(getForm().getIdentity().getId());
			connect = true;
		}
		return connect;
	}

	public boolean isSelection() {
		return super.isSelection() && getForm().getUserKey()!=null && getForm().getUserKey().length()>0;
	}
	
	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userKey", getForm().getUserKey(), (OrmCriteriaBuilder) mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userKey", getForm().getUserKey(), mainCriteriaBuilder);
		appendEqualFilter("userState", getForm().getUserState(), mainCriteriaBuilder);
		appendEqualFilter("entity.activityState", getForm().getEntityActivityState(), mainCriteriaBuilder);
		appendInArray("id", stringArrayConverter(getForm().getUserIdArray()), mainCriteriaBuilder);
        appendExclusionsFilter( mainCriteriaBuilder);
	}
	
	@Override
	public boolean isSearch() {
		return getForm().getSearchString()!=null 
				&& getForm().getSearchString().length()>0
				&& getForm().getSearchMode()!=' ';
	}
	
	@Override
	protected String[] getFieldNames() {
		if (getForm().getSearchMode()=='P') {
			return new String[] { "identity.principal" };
		}
		return new String[] { "identity.principal", "identity.optionalAlias", "identity.personalData.firstName", "identity.personalData.lastName" };
	}
	
	@Override
	public String getOrderByString() {
		if (getForm().getUserOrderBy()=='L') {
			return "lastEvent DESC";
		}
		else {
			return "userKey";
		}
	}
	
    /**
     * <code>exclusions</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendExclusionsFilter(OrmCriteriaBuilder criteriaBuilder) {
        if (getForm().getExclusions()!=null && getForm().getExclusions().size() > 0) {
            logger.debug("Found {} exclusion(s).", getForm().getExclusions().size());
            criteriaBuilder.appendAnd().appendSegment("identity.id", "not in")
            .append("(");
            String separator = "";
            for (Identity identity: getForm().getExclusions()) {
            	if (identity!=null) {
                    criteriaBuilder.append(separator).append(identity.getId());
                    if (separator.equals("")) {
                        separator = ", ";
                    }
            	}
            }
            criteriaBuilder.append(")");
       }
    }
    
    private static Logger logger = LoggerFactory.getLogger(UserFormFilterAdapter.class);

}
