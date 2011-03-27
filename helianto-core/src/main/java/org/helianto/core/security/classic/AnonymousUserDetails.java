package org.helianto.core.security.classic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.helianto.core.ActivityState;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserType;
import org.helianto.core.security.UserDetailsAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 * A class compatible to an anonymous <code>UserDetails</code> implementation.
 * 
 * @author Mauricio Fernandes de Castro
 *
 */
public class AnonymousUserDetails extends UserDetailsAdapter implements Serializable {

	AnonymousUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	
	private User anonymousUser = new AnonymousUser();

	public User getUser() {
		return anonymousUser; 
	}

//	public GrantedAuthority[] getAuthorities() {
//		GrantedAuthority[] authorities = new GrantedAuthority[1];
//		authorities[0] = new GrantedAuthorityImpl("ROLE_ANONYMOUS");
//        return authorities;
//	}
	
	// TODO
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_ANONYMOUS"));
        return authorities;
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
