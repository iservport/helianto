package org.helianto.core.repository;

/**
 * Context grou read adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class ContextGroupReadAdapter {

	private int id;
	
	private int contextId;
	
	private String contextGroupCode;

	private String contextGroupName;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextId
	 * @param contextGroupCode
	 * @param contextGroupName
	 */
	public ContextGroupReadAdapter(int id, int contextId, String contextGroupCode,
			String contextGroupName) {
		super();
		this.id = id;
		this.contextId = contextId;
		this.contextGroupCode = contextGroupCode;
		this.contextGroupName = contextGroupName;
	}
	
	public int getId() {
		return id;
	}

	public int getContextId() {
		return contextId;
	}

	public String getContextGroupCode() {
		return contextGroupCode;
	}

	public String getContextGroupName() {
		return contextGroupName;
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
		ContextGroupReadAdapter other = (ContextGroupReadAdapter) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContextGroupReadAdapter [id=" + id + ", contextId=" + contextId
				+ ", contextGroupCode=" + contextGroupCode
				+ ", contextGroupName=" + contextGroupName + "]";
	}
	
}
