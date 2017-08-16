package org.helianto.core.domain.enums;

/**
 * Context group Types.
 * 
 * @author mauriciofernandesdecastro
 */
public enum ContextGroupType {
	
	/**
	 * System context groups.
	 */
	SYS(true),
	/**
	 * Standard context groups.
	 */
	STD(true),
	/**
	 * Feature context groups.
	 */
	FEAT(false),
	/**
	 * Service context groups.
	 */
	SRV(false);
	
	private boolean autoCreate;
	
	private ContextGroupType(boolean autoCreate) {
		this.autoCreate = autoCreate;
	}

	public boolean isAutoCreate() {
		return autoCreate;
	}
	
}
