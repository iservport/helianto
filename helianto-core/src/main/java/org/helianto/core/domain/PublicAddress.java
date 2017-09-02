package org.helianto.core.domain;

import org.helianto.core.internal.AbstractAddress;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Address databases in a common environment searchable by postal code.  
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_publicaddress",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextName", "postalCode"})}
)
public class PublicAddress 
	extends AbstractAddress {

	private static final long serialVersionUID = 1L;

	@Column(length=20)
	private String contextName;
	
	/**
	 * Empty constructor.
	 */
	public PublicAddress() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param contextName
	 * @param postalCode
	 */
	public PublicAddress(String contextName, String postalCode) {
		this();
		setContextName(contextName);
		setPostalCode(postalCode);
	}
	
	/**
	 * State constructor.
	 * 
	 * @param state
	 * @param postalCode
	 */
	public PublicAddress(State state, String postalCode) {
		this(state.getContextName(), postalCode);
		setState(state);
	}
	
	/**
	 * City constructor.
	 * 
	 * @param city
	 * @param postalCode
	 */
	public PublicAddress(City city, String postalCode) {
		this(city.getState(), postalCode);
		setCity(city);
	}

	public String getContextName() {
		return contextName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PublicAddress)) return false;
        final PublicAddress other = (PublicAddress) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$contextName = this.getContextName();
        final Object other$contextName = other.getContextName();
        if (this$contextName == null ? other$contextName != null : !this$contextName.equals(other$contextName))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $contextName = this.getContextName();
        result = result * PRIME + ($contextName == null ? 43 : $contextName.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof PublicAddress;
    }

    public String toString() {
        return "org.helianto.core.domain.PublicAddress(contextName=" + this.getContextName() + ")";
    }
}
