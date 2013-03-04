package org.helianto.core.form;


/**
 * Base class to category forms.
 * 
 * @author mauriciofernandesdecastro
 * @deprecated
 */
public class AbstractCategoryForm extends AbstractTrunkForm implements CategoryForm {
	
	private static final long serialVersionUID = 1L;
	private String categoryCode;
	private String categoryName;
	private char categoryGroup;
	
	public void reset() {
		setCategoryGroup(' ');
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public char getCategoryGroup() {
		return categoryGroup;
	}
	public void setCategoryGroup(char categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

}
