package org.helianto.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.def.AddressType;
import org.helianto.core.form.PersonalForm;
import org.helianto.core.internal.AbstractAddress;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Personal address.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name = "core_address"
	,uniqueConstraints = { @UniqueConstraint(columnNames = {"identityId", "addressType" })
})
public class PersonalAddress extends AbstractAddress implements PersonalForm {

	private static final long serialVersionUID = 1L;
	private int version;
	private Identity identity;
	private char addressType;
	
	/**
	 * Empty constructor.
	 */
	public PersonalAddress() {
		this(AddressType.PERSONAL);
	}
	
	/**
	 * Hidden constructor.
	 * 
	 * @param addressType
	 */
	private PersonalAddress(AddressType addressType) {
		setAddressTypeAsEnum(addressType);
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param identity
	 * @param addressType
	 */
	public PersonalAddress(Identity identity, AddressType addressType) {
		this(addressType);
		setIdentity(identity);
	}
	
	/**
	 * Optimistic locking control.
	 */
	@Version
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * Reset.
	 */
	public void reset() {
		setAddressType(' ');
	}
	
	/**
	 * Identity.
	 */
	@JsonBackReference 
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "identityId")
	public Identity getIdentity() {
		return identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	
	/**
	 * Address type.
	 */
	public char getAddressType() {
		return addressType;
	}
	public void setAddressType(char addressType) {
		this.addressType = addressType;
	}
	public void setAddressTypeAsEnum(AddressType addressType) {
		if (addressType==null) {
			this.addressType = ' ';
		}
		else {
			this.addressType = addressType.getValue();
		}
	}
	
	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("identity").append("='").append(getIdentity()).append("' ");
		buffer.append("addressType").append("='").append(getAddressType()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	/**
	 * equals
	 */
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof PersonalAddress)) return false;
		PersonalAddress castOther = (PersonalAddress) other;

		return ((this.getIdentity() == castOther.getIdentity()) || (this.getIdentity() != null
				&& castOther.getIdentity() != null && this.getIdentity().equals(castOther.getIdentity())))
				&& ((this.getAddressType()==castOther.getAddressType()));
	}

	/**
	 * hashCode
	 */
	public int hashCode() {
		int result = 17;
		result = 37 * result + (getIdentity() == null ? 0 : this.getIdentity().hashCode());
        result = 37 * result + (int) this.getAddressType();
		return result;
	}

}
