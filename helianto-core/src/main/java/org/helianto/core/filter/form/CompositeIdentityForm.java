package org.helianto.core.filter.form;

import java.util.Collection;

import org.helianto.core.Identity;
import org.helianto.core.Resettable;
import org.helianto.core.UserGroup;

/**
 * Composite identity form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeIdentityForm 

	extends AbstractControllable 

	implements 
	  IdentityForm
	, UserRequestForm
	, Resettable
	
{
	
	private static final long serialVersionUID = 1L;
	private UserGroup userGroup;
	private String principal;
	private String tempPassword;
	private String firstName;
	private Collection<Identity> exclusions;
	
	/**
	 * Construtor principal.
	 * 
	 * @param principal
	 */
	public CompositeIdentityForm(String principal) {
		setPrincipal(principal);
	}
	
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	public String getTempPassword() {
		return tempPassword;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public Collection<Identity> getExclusions() {
		return exclusions;
	}
	public void setExclusions(Collection<Identity> exclusions) {
		this.exclusions = exclusions;
	}

}
