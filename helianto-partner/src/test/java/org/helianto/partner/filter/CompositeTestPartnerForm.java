package org.helianto.partner.filter;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.form.CompositeEntityForm;
import org.helianto.core.form.KeyTypeForm;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.form.ContactGroupForm;
import org.helianto.partner.form.PartnerCategoryForm;
import org.helianto.partner.form.PartnerForm;
import org.helianto.partner.form.PartnerPhoneForm;
import org.helianto.partner.form.PrivateAddressForm;
import org.helianto.partner.form.PrivateEntityForm;
import org.helianto.partner.form.PrivateEntityKeyForm;

/**
 * Composite partner form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeTestPartnerForm
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
    private int partnerId;
    private Category category;
    private int userGroupParentId;
    private String userKey;
    private char userState;
    private char entityActivityState;
	private int[] userIdArray;
    private char userOrderBy;
	private int categoryId;
	private int privateEntityId;
	private int keyTypeId;
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public CompositeTestPartnerForm(Entity entity) {
		super();
		setEntity(entity);
	}
	
	/**
	 * Parent constructor.
	 * 
	 * @param parent
	 */
	public CompositeTestPartnerForm(PrivateEntity parent) {
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
	
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public int getUserGroupParentId() {
		return userGroupParentId;
	}
	public void setUserGroupParentId(int userGroupParentId) {
		this.userGroupParentId = userGroupParentId;
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
	
	public char getEntityActivityState() {
		return entityActivityState;
	}
	public void setEntityActivityState(char entityActivityState) {
		this.entityActivityState = entityActivityState;
	}
	
	public int[] getUserIdArray() {
		return userIdArray;
	}
	public void setUserIdArray(int[] userIdArray) {
		this.userIdArray = userIdArray;
	}
	
	public char getUserOrderBy() {
		return userOrderBy;
	}
	public void setUserOrderBy(char userOrderBy) {
		this.userOrderBy = userOrderBy;
	}
	
	/**
	 * Clone the form and set a new parent.
	 * 
	 * @param parent
	 */
	public CompositeTestPartnerForm clone(PrivateEntity parent) {
		try {
			CompositeTestPartnerForm form = (CompositeTestPartnerForm) super.clone();
			form.setParent(parent);
			return form;
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException("Unable to clone CompositePartnerForm.");
		}
	}

	@Override
	public int getCategoryId() {
		if (getCategory()!=null) {
			return getCategory().getId();
		}
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int getPrivateEntityId() {
		if (getParent()!=null) {
			return getParent().getId();
		}
		return privateEntityId;
	}
	public void setPrivateEntityId(int privateEntityId) {
		this.privateEntityId = privateEntityId;
	}

	@Override
	public int getKeyTypeId() {
		if (getKeyType()!=null) {
			return getKeyType().getId();
		}
		return keyTypeId;
	}
	public void setKeyTypeId(int keyTypeId) {
		this.keyTypeId = keyTypeId;
	}

}
