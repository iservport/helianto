package org.helianto.core.form;

import java.util.Collection;

import org.helianto.core.domain.Identity;

/**
 * Composite identity form.
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractIdentityForm 

	extends AbstractControllable 

	implements 
	  IdentityForm
	
{
	
	private static final long serialVersionUID = 1L;
	private String principal;
	private String firstName;
	private String lastName;
	private String nameLike;
	private char gender;
	private char identityType;
	private char notification;
	private Collection<Identity> exclusions;
	
//	/**
//	 * Construtor principal.
//	 * 
//	 * @param principal
//	 */
//	public AbstractIdentityForm(String principal) {
//		setPrincipal(principal);
//	}
//	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
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
	
	public String getNameLike() {
		return nameLike;
	}
	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
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
