package org.helianto.partner.form;

import org.helianto.core.Category;
import org.helianto.core.Entity;
import org.helianto.core.KeyType;
import org.helianto.core.filter.form.CompositeEntityForm;
import org.helianto.core.filter.form.KeyTypeForm;
import org.helianto.partner.domain.Partner;
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
	, PartnerPhoneForm
	, KeyTypeForm
	, PartnerCategoryForm
	, ContactGroupForm
	  
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
    private String areaCode;
    private char phoneType;
    private Partner partner;
    private Category category;
    private String userKey;
    private char userState;
	
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
	
	/**
	 * Partner constructor.
	 * 
	 * @param partner
	 */
	public CompositePartnerForm(Partner partner) {
		this(partner.getEntity());
		setPartner(partner);
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
	
	public int getParentId() {
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
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public char getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(char phoneType) {
		this.phoneType = phoneType;
	}
	
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	public char getUserState() {
		return userState;
	}
	public void setUserState(char userState) {
		this.userState = userState;
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
