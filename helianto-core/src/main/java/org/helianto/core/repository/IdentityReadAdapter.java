package org.helianto.core.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.core.def.Gender;
import org.helianto.core.def.IdentityType;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PersonalData;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Identity read adapter.
 *
 * @author Eldevan Nery Junior
 */
public class IdentityReadAdapter
	implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Identity adaptee ;

	private Integer id = 0;
	
	private IdentityType identityType = IdentityType.PERSONAL_EMAIL;

	private String principal;
	
	private String displayName = "";
	
	private Character appellation = '0';

	private String firstName = "";
	
	private String lastName = "";
	
	private Gender gender = Gender.NOT_SUPPLIED;
	
	private Character notification;
	
	private Date birthDate;  

	/**
	 * Super constructor.
	 */
	public IdentityReadAdapter() {
		super();
	}

	/**
	 * Adaptee constructor.
	 *
	 * @param adaptee
	 */
	public IdentityReadAdapter(Identity identity) {
		this();
		this.adaptee = identity;
	}
	
	/**
	 * 
	 * @param id
	 * @param userGroupId
	 * @param identityType
	 * @param principal
	 * @param displayName
	 * @param appellation
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param notification
	 * @param birthDate
	 */
	public IdentityReadAdapter(Integer id, IdentityType identityType, String principal, String displayName,
			Character appellation, String firstName, String lastName,
			Gender gender, Character notification, Date birthDate) {
		super();
		this.id = id;
		this.identityType = identityType;
		this.principal = principal;
		this.displayName = displayName;
		this.appellation = appellation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.notification = notification;
		this.birthDate = birthDate;
	}
	
	public IdentityReadAdapter build(){
		if (adaptee==null) {
			throw new RuntimeException("Adaptee canoot be null.");
		}
		setId(adaptee.getId());
		setPrincipal(adaptee.getPrincipal());
		setIdentityType(IdentityType.valueOf(adaptee.getIdentityType()));
		setDisplayName(adaptee.getDisplayName());
		setAppellation(adaptee.getAppellation());
		setFirstName(adaptee.getIdentityFirstName());
		setLastName(adaptee.getIdentityLastName());
		setGender(Gender.valueOf(adaptee.getGender()));
		setNotification(adaptee.getNotification());
		setBirthDate(adaptee.getBirthDate());
		return this;
	}
	
	public Identity merge(){
		adaptee.setId(getId());
		adaptee.setPrincipal(getPrincipal());
		adaptee.setIdentityType(getIdentityType().getValue());
		adaptee.setDisplayName(getDisplayName());
		PersonalData personalData = new PersonalData(getFirstName(), getLastName()); 
		personalData.setGender(getGender().getValue());
		personalData.setAppellation(getAppellation());
		personalData.setBirthDate(getBirthDate());
		adaptee.setPersonalData(personalData);
		adaptee.setNotification(getNotification());
		return adaptee; 
	}
	
	
	@JsonIgnore
	public Identity getAdaptee() {
		return adaptee;
	}

	public IdentityReadAdapter setAdaptee(Identity adaptee) {
		this.adaptee = adaptee;
		return this; 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IdentityType getIdentityType() {
		return identityType;
	}

	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Character getAppellation() {
		return appellation;
	}

	public void setAppellation(Character appellation) {
		this.appellation = appellation;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Character getNotification() {
		return notification;
	}

	public void setNotification(Character notification) {
		this.notification = notification;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		IdentityReadAdapter other = (IdentityReadAdapter) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

}