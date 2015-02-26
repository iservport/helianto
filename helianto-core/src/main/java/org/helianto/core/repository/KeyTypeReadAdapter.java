package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;

/**
 * Key type read adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class KeyTypeReadAdapter 
	implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private KeyType adaptee;

    private Integer id;
    
    private Integer contextId;
    
    private String keyCode = "";
    
    private Character keyGroup = 'A';
    
    private String keyName = "";
    
    private String purpose = "";

    private String synonyms = "";
    
    /**
     * Constructor.
     */
    public KeyTypeReadAdapter() {
		super();
	}
    
	/**
	 * Constructor.
	 * 
	 * @param adaptee
	 */
	public KeyTypeReadAdapter(KeyType adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
    /**
     * Constructor.
     * 
     * @param id
     * @param contextId
     * @param keyCode
     * @param keyGroup
     * @param keyName
     * @param purpose
     * @param synonyms
     */
    public KeyTypeReadAdapter(Integer id
			, Integer contextId
			, String keyCode
			, Character keyGroup
			, String keyName
			, String purpose
			, String synonyms
			) {
		this();
		this.id = id;
		this.contextId = contextId;
		this.keyCode = keyCode;
		this.keyGroup = keyGroup;
		this.keyName = keyName;
		this.purpose = purpose;
		this.synonyms = synonyms;
	}

	public KeyType getAdaptee() {
		return adaptee;
	}

	public Integer getId() {
		return id;
	}

	public Integer getContextId() {
		return contextId;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public Character getKeyGroup() {
		return keyGroup;
	}

	public String getKeyName() {
		return keyName;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getSynonyms() {
		return synonyms;
	}
	
	public String[] getSynonymsAsArray() {
		if (getSynonyms()!=null) {
			return getSynonyms().split(",");
		}
		return new String[0];
	}

	@Override
	public String toString() {
		return "KeyTypeReadAdapter [id=" + id + ", contextId=" + contextId
				+ ", keyCode=" + keyCode + ", keyGroup=" + keyGroup
				+ ", keyName=" + keyName + ", purpose=" + purpose
				+ ", synonyms=" + synonyms + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		KeyTypeReadAdapter other = (KeyTypeReadAdapter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    
}
