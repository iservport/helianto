package org.helianto.partner.domain.enums;

import org.helianto.core.internal.KeyNameAdapter;

import java.io.Serializable;

/**
 * Partner types.
 *
 * @author mauriciofernandesdecastro.
 */
public enum PartnerType {

	CUSTOMER('C', 0),
	SUPPLIER('S', 0),
	MANUFACTURER('M', 0),
	LABORATORY('L', 0),

	AGENT('A', 1),
	DEVELOPER('V', 1),
	DIVISION('D', 1),
	EDUCATION('E', 1),
	PRODUCER('R', 1),
	TRANSPORT('T', 1);

	private char value;

	private int priority;

	private PartnerType(char value, int priority) {
		this.value= value;
		this.priority= priority;
	}

	public int getPriority() {
		return priority;
	}

	public char getValue() {
		return this.value;
	}

}
