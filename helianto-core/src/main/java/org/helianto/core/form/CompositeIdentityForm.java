package org.helianto.core.form;

import java.util.Collection;

import org.helianto.core.domain.Identity;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.form.UserRequestForm;

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
	
{
	
	private static final long serialVersionUID = 1L;
	private UserGroup userGroup;
	private String principal;
	private String tempPassword;
	private String firstName;
	private String lastName;
	private char gender;
	private char identityType;
	private char notification;
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
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public char getIdentityType() {
		return identityType;
	}
	public void setIdentityType(char identityType) {
		this.identityType = identityType;
	}
	
	public char getNotification() {
		return notification;
	}
	public void setNotification(char notification) {
		this.notification = notification;
	}
	
	public Collection<Identity> getExclusions() {
		return exclusions;
	}
	public void setExclusions(Collection<Identity> exclusions) {
		this.exclusions = exclusions;
	}

}
