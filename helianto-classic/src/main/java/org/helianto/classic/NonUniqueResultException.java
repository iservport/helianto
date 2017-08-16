package org.helianto.classic;

import java.util.List;

/**
 * Exception thrown when zero or one instances are expected but more than one is found.
 * 
 * @author mauriciofernandesdecastro
 */
public class NonUniqueResultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private List<?> list;
	
	/**
	 * Default constructor.
	 */
	public NonUniqueResultException() {
		super();
	}

	/**
	 * Message constructor.
	 * 
	 * @param message
	 */
	public NonUniqueResultException(String message) {
		super(message);
	}

	/**
	 * List constructor.
	 * 
	 * @param message
	 * @param list
	 */
	public NonUniqueResultException(String message, List<?> list) {
		this(message);
		setList(list);
	}
	
	/**
	 * The resulting list.
	 */
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}

}
