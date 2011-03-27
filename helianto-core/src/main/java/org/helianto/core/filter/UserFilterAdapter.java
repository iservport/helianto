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

package org.helianto.core.filter;

import java.util.Collection;
import java.util.HashSet;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.criteria.SelectFromBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter adapter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserFilterAdapter extends AbstractTrunkFilterAdapter<UserGroup> {

    private static final long serialVersionUID = 1L;
    private Class<? extends UserGroup> clazz;
    private UserGroup parent;
    private String parentUserKey;
    private boolean orderByLastEventDesc = false;
	private Collection<Identity> exclusions;
	private Identity identity;
    
    /**
     * Default constructor.
     * 
     * @param userGroup
     */
    public UserFilterAdapter(UserGroup userGroup) {
    	super(userGroup);
    	reset();
    }
    
    /**
     * User group key constructor.
     * 
     * @param entity
     * @param userKey
     */
    public UserFilterAdapter(Entity entity, String userKey) {
    	this(new UserGroup(entity, userKey));
    }
    
    /**
     * User key constructor.
     * 
     * @param entity
     * @param identity
     */
    public UserFilterAdapter(Entity entity, Identity identity) {
    	this(new User(entity, new Credential(identity)));
    }
    
    /**
     * Parent user key constructor.
     * 
     * @param parentUserKey
     */
    public UserFilterAdapter(String parentUserKey) {
    	this(new UserGroup());
    	setParentUserKey(parentUserKey);
    }
    
    /**
     * Manager constructor.
     * 
     * @param parentUserKey
     * @param identity
     */
    public UserFilterAdapter(String parentUserKey, Identity identity) {
    	this(parentUserKey);
    	setIdentity(identity);
    }
    
    /**
     * Force filter to standards.
     */
    public void reset() {
    	getFilter().setUserState(' ');
    	setExclusions(new HashSet<Identity>(0));
    }
    
    /**
     * If parent is not null, select also from inner join parent.
     */
	@Override
	public String createSelectAsString() {
		if (getParent()!=null | (getParentUserKey()!=null && getParentUserKey().length()>0)) {
			SelectFromBuilder selectFromBuilder = new SelectFromBuilder(UserGroup.class, getObjectAlias());
			selectFromBuilder.createSelectFrom().appendParentInnerJoin();
			return selectFromBuilder.getAsString();
		}
		return null;
	}
	

	public boolean isSelection() {
		return getFilter().getUserKey()!=null && getFilter().getUserKey().length()>0;
	}

	@Override
	public void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
		if (getParent()!=null) {
			mainCriteriaBuilder.appendAnd().append("parentAssociations.parent.id =").append(getParent().getId());
			mainCriteriaBuilder.addSegmentCount(1);
		}
		if (getParentUserKey()!=null && getParentUserKey().length()>0) {
			mainCriteriaBuilder.appendAnd().append("parentAssociations.parent.userKey =").appendString(getParentUserKey());
			mainCriteriaBuilder.addSegmentCount(1);
		}
		if (getIdentity()!=null && getIdentity().getId()>0) {
			mainCriteriaBuilder.appendAnd().appendSegment("identity.id", "=").append(getIdentity().getId());
		}
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userKey", getFilter().getUserKey(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userState", getFilter().getUserState(), mainCriteriaBuilder);
        appendExclusionsFilter( mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		if (isOrderByLastEventDesc()) {
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
    protected void appendExclusionsFilter(CriteriaBuilder criteriaBuilder) {
        if (getExclusions()!=null && getExclusions().size() > 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("Found "+getExclusions().size()+" exclusion(s).");
            }
            criteriaBuilder.appendAnd().appendSegment("identity.id", "not in")
            .append("(");
            String separator = "";
            for (Identity identity: getExclusions()) {
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
    
    /**
     * Class constraint to polimorphic filters.
     */
	public Class<? extends UserGroup> getClazz() {
		return this.clazz;
	}
	public void setClazz(Class<? extends UserGroup> clazz) {
		this.clazz = clazz;
	}

    /**
     * Discriminator that maps to a class constraint.
     */
	public char getDiscriminator() {
		if (clazz.equals(UserGroup.class)) {
			return 'G'; 
		}
		if (clazz.equals(User.class)) {
			return 'U'; 
		}
		return ' ';
	}
	public void setDiscriminator(char discriminator) {
		if (discriminator=='G') {
			clazz = UserGroup.class; 
		}
		else if (discriminator=='U') {
			clazz = User.class; 
		}
		else {
			clazz = null;
		}
	}
	
	/**
	 * Parent filter.
	 */
	public UserGroup getParent() {
		return parent;
	}
	public void setParent(UserGroup parent) {
		this.parent = parent;
	}
	
	/**
	 * Parent user key filter.
	 */
	public String getParentUserKey() {
		return parentUserKey;
	}
	public void setParentUserKey(String parentUserKey) {
		this.parentUserKey = parentUserKey;
	}

    /**
     * True if order by last event is required.
     */
    public boolean isOrderByLastEventDesc() {
		return orderByLastEventDesc;
	}
	public void setOrderByLastEventDesc(boolean orderByLastEventDesc) {
		this.orderByLastEventDesc = orderByLastEventDesc;
	}

	/**
     * Collection of identities to exclude on result set.
     */
    public Collection<Identity> getExclusions() {
        return exclusions;
    }
    public void setExclusions(Collection<Identity> exclusions) {
        this.exclusions = exclusions;
    }
    
    /**
     * Identity filter.
     */
    public Identity getIdentity() {
		return identity;
	}
    public void setIdentity(Identity identity) {
		this.identity = identity;
	}
    
    private static Logger logger = LoggerFactory.getLogger(UserFilterAdapter.class);

}
