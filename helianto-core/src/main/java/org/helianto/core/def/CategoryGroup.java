package org.helianto.core.def;

import java.io.Serializable;

import org.helianto.core.internal.KeyNameAdapter;

/**
 * Category groups.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum CategoryGroup implements KeyNameAdapter {
	
	/**
	 * Not defined.
	 */
	NOT_DEFINED('_', "NONE"),
	/**
	 * Entity.
	 */
	ENTITY('N', "NONE"),
	/**
	 * Project.
	 */
	PROJECT('J', "PLAN"),
	/**
	 * Planning.
	 */
	PLANNING('R', "PLAN"),
	/**
	 * Action.
	 */
	ACTION('A', "PLAN"),
	/**
	 * Health.
	 */
	HEALTH('H', "PLAN"),
	/**
	 * Document.
	 */
	DOCUMENT('D', "DO"),
	/**
	 * Product.
	 */
	PRODUCT('P', "DO"),
	/**
	 * Service.
	 */
	SERVICE('E', "DO"),
	/**
	 * Process.
	 */
	PROCESS('O', "DO"),
	/**
	 * Order.
	 */
	ORDER('Y', "DO"),
	/**
	 * Instrument.
	 */
	INSTRUMENT('I', "CONTROL"),
	/**
	 * Maintenance.
	 */
	MAINTENANCE('M', "CONTROL"),
	/**
	 * Stock.
	 */
	STOCK('S', "CONTROL"),
	/**
	 * Labor.
	 */
	LABOR('L', "CONTROL"),
	/**
	 * User.
	 */
	USER('Z', "CONTROL"),
	/**
	 * Planning.
	 */
	REQUEST('Q', "LEARN"),
	/**
	 * Action.
	 */
	KNOWLEDGE('K', "LEARN"),
	/**
	 * Competence.
	 */
	COMPETENCE('C', "LEARN"),
	/**
	 * Unit.
	 */
	UNIT('U', "NONE")
	;
	
	private CategoryGroup(char value, String code) {
		this.value = value;
		this.code = code;
	}
	
	private char value;
	
	private String code;
	
	public char getValue() {
		return value;
	}
	
	public Serializable getKey() {
		return value;
	}
	
	@Override
	public String getCode() {
		return code;
	}
	
	@Override
	public String getName() {
		return name();
	}
	
	public static char[] valuesAsArray(){
		CategoryGroup.values();
		String values = "";
		for (CategoryGroup categoryGroup : CategoryGroup.values()) {
			values+=categoryGroup.getCode();
		}
		return values.toCharArray();
	}
}
