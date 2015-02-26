package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.internal.KeyNameAdapter;

/**
 * Category adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class CategoryAdapter 
	implements KeyNameAdapter
{
	
	private int id;
	
	private String categoryCode;
	
	private String categoryName;

	private int countItems;
	
	private int countAlerts;
	
	private int countWarnings;
	
	private int countOthers;
	
	private String fontIcon;

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param categoryCode
	 * @param categoryName
	 */
	public CategoryAdapter(int id, String categoryCode, String categoryName) {
		super();
		this.id = id;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}

	public int getId() {
		return id;
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
    
	public String getFontIcon() {
		return fontIcon;
	}
	public void setFontIcon(String fontIcon) {
		this.fontIcon = fontIcon;
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
		CategoryAdapter other = (CategoryAdapter) obj;
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
