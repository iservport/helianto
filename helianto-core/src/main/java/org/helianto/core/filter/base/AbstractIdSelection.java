package org.helianto.core.filter.base;

import java.io.Serializable;


/**
 * Base class to use in FilterAdapters to limit the fields returned by a select clause.
 * 
 * @author mauriciofernandesdecastro
 */
@SuppressWarnings("serial")
public abstract class AbstractIdSelection 
	implements Serializable
{

	private int id;

	/**
	 * The id constructor.
	 * 
	 * @param id
	 */
	protected AbstractIdSelection(final int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractIdSelection)) {
			return false;
		}
		AbstractIdSelection other = (AbstractIdSelection) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	

}
