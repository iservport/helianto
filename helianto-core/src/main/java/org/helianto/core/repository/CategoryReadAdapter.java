package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.Category;
import org.helianto.core.internal.KeyNameAdapter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Category adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class CategoryReadAdapter 
	implements KeyNameAdapter
{
	
	private Category adaptee;
	
	private int id;
	
	private Character categoryGroup;
	
	private String categoryCode;
	
	private String categoryName;

	private int countItems;
	
	private int countAlerts;
	
	private int countWarnings;
	
	private int countOthers;
	
	private String categoryIcon;
	
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
	public CategoryReadAdapter(Category category) {
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
	 * Builder.
	 */
	public CategoryReadAdapter build() {
		this.id = adaptee.getId();
		this.categoryGroup = adaptee.getCategoryGroup();
		this.categoryCode = adaptee.getCategoryCode();
		this.categoryName = adaptee.getCategoryName();
		this.categoryIcon = adaptee.getCategoryIcon();
		return this;
	}

	/**
	 * Merger.
	 */
	public Category merge() {
		adaptee.setId(id);
		adaptee.setCategoryGroup(categoryGroup);
		adaptee.setCategoryCode(categoryCode);
		adaptee.setCategoryName(categoryName);
		adaptee.setCategoryIcon(categoryIcon);
		return adaptee;
	}

	@JsonIgnore
	public Category getAdaptee() {
		return adaptee;
	}
	public CategoryReadAdapter setAdaptee(Category adaptee) {
		this.adaptee = adaptee;
		return this;
	}
	
	public int getId() {
		return id;
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
