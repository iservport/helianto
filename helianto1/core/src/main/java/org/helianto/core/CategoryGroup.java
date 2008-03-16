package org.helianto.core;

/**
 * Category groups.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum CategoryGroup {
	
	NOT_DEFINED(-1),
	UNIT(0),
	INSTRUMENT(1);
	
	private CategoryGroup(int value) {
		this.value = value;
	}
	
	private int value;
	
	public int getValue() {
		return value;
	}

}
