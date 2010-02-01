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

package org.helianto.core;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.filter.PolymorphicFilter;

/**
 * Filter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserFilter extends AbstractUserBackedCriteriaFilter implements PolymorphicFilter<UserGroup> {

    /**
     * Factory method.
     * @param user
     */
    public static UserFilter userFilterFactory(User user) {
    	return AbstractUserBackedCriteriaFilter.filterFactory(UserFilter.class, user);
    }
    
    /**
     * Factory method.
     * @param identity
     */
    public static UserFilter userFilterFactory(Identity identity) {
    	return new UserFilter(identity, true);
    }
    
    private static final long serialVersionUID = 1L;
    private Class<? extends UserGroup> clazz;
    private Identity identity;
    private String identityPrincipal;
    private String identityPrincipalLike;
    private String optionalAlias;
	private String domain;
    private char userState = ' ';
    private boolean orderByLastEventDesc = false;
	private Collection<Identity> exclusions;
    
    /**
     * Default constructor
     */
    public UserFilter() {
    	super();
    	setIdentityPrincipal("");
    	setIdentityPrincipalLike("");
    	setExclusions(new HashSet<Identity>(0));
    }
    
    /**
     * Identity constructor
     */
    public UserFilter(Identity identity, boolean orderByLastEventDesc) {
    	this();
    	setIdentity(identity);
    	setOrderByLastEventDesc(orderByLastEventDesc);
    }
    
    /**
     * Entity constructor
     */
    public UserFilter(Entity entity) {
    	this();
    	setEntity(entity);
    }
    
    /**
     * Force filter to standards.
     */
    public void reset() {
    	setIdentityPrincipal("");
    	setExclusions(new HashSet<Identity>(0));
    }

	public boolean isSelection() {
		return (getIdentity()!=null 
				|| getIdentityPrincipal()!=null && getIdentityPrincipal().length() > 0);
	}

	public String getObjectAlias() {
		return "usergroup";
	}

	/**
	 * Required to avoid exception when entity is not present.
	 */
	@Override
	protected boolean requireEntity() {
		if (logger.isDebugEnabled()) {
			logger.debug("Entity not required; although current is "+getEntity());
		}
		return false;
	}
	
	
	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		if (getIdentity()!=null) {
			appendEqualFilter("identity.id", getIdentity().getId(), mainCriteriaBuilder);
		}
		else if (getIdentityPrincipal().length()>0) {
			appendEqualFilter("identity.principal", getIdentityPrincipal(), mainCriteriaBuilder);
		}
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("userState", getUserState(), mainCriteriaBuilder);
		appendLikeFilter("identity.principal", getIdentityPrincipalLike(), mainCriteriaBuilder);
        appendExclusionsFilter( mainCriteriaBuilder);
		if (isOrderByLastEventDesc()) {
			appendOrderBy("lastEvent DESC", mainCriteriaBuilder);
		}
		else {
			appendOrderBy("identity.principal", mainCriteriaBuilder);
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
     * Identity filter.
     */
    public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

    /**
     * Identity principal filter.
     */
    public String getIdentityPrincipal() {
        return identityPrincipal;
    }
	public void setIdentityPrincipal(String identityPrincipal) {
        this.identityPrincipal = identityPrincipal;
    }
    
    /**
     * Identity principal like filter.
     */
    public String getIdentityPrincipalLike() {
        return identityPrincipalLike;
    }
	public void setIdentityPrincipalLike(String identityPrincipalLike) {
        this.identityPrincipalLike = identityPrincipalLike;
    }
    
    /**
     * Optional alias criterion field
     */
    public String getOptionalAlias() {
		return optionalAlias;
	}
	public void setOptionalAlias(String optionalAlias) {
		this.optionalAlias = optionalAlias;
	}

    /**
     * User domain criterion field
     */
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

    /**
     * User state criterion field
     */
	public char getUserState() {
		return userState;
	}
	public void setUserState(char userState) {
		this.userState = userState;
	}
	public void setUserState(UserState userState) {
		this.userState = userState.getValue();
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

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("identity").append("='").append(getIdentity()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }
    
    private static Logger logger = LoggerFactory.getLogger(UserFilter.class);

}
