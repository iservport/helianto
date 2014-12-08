package org.helianto.core.repository;


/**
 * Feature read adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class FeatureReadAdapter {

	private int id;
	
	private int contextId;
	
	private String featureCode;

	private String featureName;

	private Character featureType;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param contextId
	 * @param featureCode
	 * @param featureName
	 * @param featureType
	 */
	public FeatureReadAdapter(int id, int contextId, String featureCode,
			String featureName, Character featureType) {
		super();
		this.id = id;
		this.contextId = contextId;
		this.featureCode = featureCode;
		this.featureName = featureName;
		this.featureType = featureType;
	}
	
	public int getId() {
		return id;
	}

	public int getContextId() {
		return contextId;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public String getFeatureName() {
		return featureName;
	}

	public Character getFeatureType() {
		return featureType;
	}
	
}
