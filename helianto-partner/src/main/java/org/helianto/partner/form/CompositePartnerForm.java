package org.helianto.partner.form;

import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.filter.form.CompositeEntityForm;
import org.helianto.partner.domain.PrivateEntity;

/**
 * Composite partner form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositePartnerForm

	extends CompositeEntityForm
	
	implements 
	  PrivateEntityForm
	, PartnerForm
	, PrivateAddressForm
	, PrivateEntityKeyForm
	  
{

	private static final long serialVersionUID = 1L;
	private char addressType;
	private String cityName;
	private PrivateEntity parent;
	private char partnerType;
	private char priority;
	private char partnerState;
	private int sequence;
    private KeyType keyType;
    private String keyValue;
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public CompositePartnerForm(Entity entity) {
		super();
		setEntity(entity);
	}
	
	/**
	 * Parent constructor.
	 * 
	 * @param parent
	 */
	public CompositePartnerForm(PrivateEntity parent) {
		this(parent.getEntity());
		setParent(parent);
	}
	
	public char getAddressType() {
		return addressType;
	}
	public void setAddressType(char addressType) {
		this.addressType = addressType;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public PrivateEntity getParent() {
		return parent;
	}
	public void setParent(PrivateEntity parent) {
		this.parent = parent;
	}
	
	public long getParentId() {
		if (getParent()!=null) {
			return getParent().getId();
		}
		return 0;
	}
	
	public String getParentName() {
		return "privateEntity";
	}
	
	public char getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(char partnerType) {
		this.partnerType = partnerType;
	}
	
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}
	
	public char getPartnerState() {
		return partnerState;
	}
	public void setPartnerState(char partnerState) {
		this.partnerState = partnerState;
	}
	
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public KeyType getKeyType() {
		return keyType;
	}
	public void setKeyType(KeyType keyType) {
		this.keyType = keyType;
	}
	
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	/**
	 * Clone the form and set a new parent.
	 * 
	 * @param parent
	 */
	public CompositePartnerForm clone(PrivateEntity parent) {
		try {
			CompositePartnerForm form = (CompositePartnerForm) super.clone();
			form.setParent(parent);
			return form;
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException("Unable to clone CompositePartnerForm.");
		}
	}
	
}
