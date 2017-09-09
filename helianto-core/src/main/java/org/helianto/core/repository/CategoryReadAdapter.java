package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Category2;
import org.helianto.core.internal.KeyNameAdapter;
import org.helianto.core.utils.StringListUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Category adapter.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
public class CategoryReadAdapter 
	implements KeyNameAdapter
{
	
	private Category2 adaptee;
	
	private Integer id;
	
	private Integer entityId;
	
	private Character categoryGroup;
	
	private String categoryCode;
	
	private String categoryName;

	private int countItems;
	
	private int countAlerts;
	
	private int countWarnings;
	
	private int countOthers;
	
	private String categoryIcon;
	
	private String scriptItems;
	
	private String customProperties;
	
	private byte[] content;
	
	/**
	 * Default constructor.
	 */
	public CategoryReadAdapter() {
		super();
	}

	/**
	 * Adaptee constructor.
	 * 
	 * @param category
	 */
	public CategoryReadAdapter(Category2 category) {
		this();
		setAdaptee(category);
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param categoryCode
	 * @param categoryName
	 */
	public CategoryReadAdapter(int id, String categoryCode, String categoryName) {
		super();
		this.id = id;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param categoryGroup
	 * @param categoryCode
	 * @param categoryName
	 * @param categoryIcon
	 * @deprecated
	 */
	public CategoryReadAdapter(int id
			, Character categoryGroup
			, String categoryCode
			, String categoryName
			, String categoryIcon
			) {
		this(id, categoryCode, categoryName);
		this.categoryGroup = categoryGroup;
		setCategoryIcon(categoryIcon);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param entityId
	 * @param categoryGroup
	 * @param categoryCode
	 * @param categoryName
	 * @param categoryIcon
	 * @param scriptItems
	 * @param customProperties
	 * @param content
	 */
	public CategoryReadAdapter(Integer id
			, Integer entityId
			, Character categoryGroup
			, String categoryCode
			, String categoryName
			, String categoryIcon
			, String scriptItems
			, String customProperties
			, byte[] content
			) {
		this(id, categoryCode, categoryName);
		this.entityId = entityId;
		this.categoryGroup = categoryGroup;
		setCategoryIcon(categoryIcon);
		this.scriptItems = scriptItems;
		this.customProperties = customProperties;
		this.content = content;
	}
	
	/**
	 * Builder.
	 */
	public CategoryReadAdapter build() {
		this.id = adaptee.getId();
		this.categoryGroup = adaptee.getCategoryGroup();
		this.categoryCode = adaptee.getCategoryCode();
		this.categoryName = adaptee.getCategoryName();
		this.categoryIcon = adaptee.getCategoryIcon();
		this.customProperties = adaptee.getCustomProperties();
		this.content = adaptee.getContent();
		return this;
	}

	/**
	 * Merger.
	 */
	public Category2 merge() {
		adaptee.setId(id);
		adaptee.setCategoryGroup(categoryGroup);
		adaptee.setCategoryCode(categoryCode);
		adaptee.setCategoryName(categoryName);
		adaptee.setCategoryIcon(categoryIcon);
		adaptee.setCustomProperties(customProperties);
		adaptee.setContent(content);
		return adaptee;
	}

	@JsonIgnore
	public Category2 getAdaptee() {
		return adaptee;
	}
	public CategoryReadAdapter setAdaptee(Category2 adaptee) {
		this.adaptee = adaptee;
		return this;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getEntityId() {
		return entityId;
	}
	
	public Character getCategoryGroup() {
		return categoryGroup;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

    /**
     * Count items.
     */
    public int getCountItems() {
		return countItems;
	}
    public void setCountItems(int countItems) {
		this.countItems = countItems;
	}
    
    /**
     * Count alerts.
     */
    public int getCountAlerts() {
		return countAlerts;
	}
    public void setCountAlerts(int countAlerts) {
		this.countAlerts = countAlerts;
	}
    
    /**
     * Count warnings.
     */
    public int getCountWarnings() {
		return countWarnings;
	}
    public void setCountWarnings(int countWarnings) {
		this.countWarnings = countWarnings;
	}
    
    /**
     * Count others.
     */
    public int getCountOthers() {
		return countOthers;
	}
    public void setCountOthers(int countOthers) {
		this.countOthers = countOthers;
	}
    
	public String getCategoryIcon() {
		return categoryIcon;
	}
	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}
	
	public String getScriptItems() {
		return scriptItems;
	}
    public String[] getScriptItemsAsArray() {
    	return StringListUtils.stringToArray(getScriptItems());
	}
    
    public String getCustomProperties() {
		return customProperties;
	}
    public void setCustomProperties(String customProperties) {
		this.customProperties = customProperties;
	}
    
    @JsonIgnore
    public byte[] getContent() {
		return content;
	}
    public void setContent(byte[] content) {
		this.content = content;
	}
    
    @JsonSerialize
    public String getContentAsString() {
    	if (getContent()!=null) {
    		return new String(getContent());
    	}
    	return "";
	}
    public void setContentAsString(String contentAsString) {
    	if (contentAsString!=null) {
    		setContent(contentAsString.getBytes());
    	}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryReadAdapter other = (CategoryReadAdapter) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/* 
	 * @see org.helianto.core.internal.KeyNameAdapter
	 */

	@Override
	public Serializable getKey() {
		return getId();
	}

	@Override
	public String getCode() {
		return getCategoryCode();
	}

	@Override
	public String getName() {
		return getCategoryName();
	}

}
