package org.helianto.core.security;

import java.io.Serializable;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.helianto.core.ActivityState;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserType;

/**
 * A class compatible to an anonymous <code>UserDetails</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 *
 */
public class AnonymousUserDetails extends AbstractUserDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User anonymousUser = new AnonymousUser();

	public User getUser() {
		return anonymousUser; 
	}

	public GrantedAuthority[] getAuthorities() {
        return new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_ANONYMOUS") };
	}
	
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
         return true;
    }

    public String getPassword() {
        return "";
     }

	public class AnonymousUser extends User {

		private static final long serialVersionUID = 1L;

		@Override
		public char getUserType() {
			return UserType.INTERNAL.getValue();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public Entity getEntity() {
			Entity entity = new Entity();
			entity.setAlias("ENTITY");
			return entity;
		}

		@Override
		public Identity getIdentity() {
			Identity identity = new Identity();
			identity.setPrincipal("ANONYMOUS");
			return identity;
		}

		@Override
		public char getUserState() {
			return ActivityState.ACTIVE.getValue();
		}
		
	}

}
