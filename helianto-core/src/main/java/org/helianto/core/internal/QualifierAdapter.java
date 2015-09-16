package org.helianto.core.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.helianto.core.domain.Category;
import org.helianto.core.repository.CategoryReadAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Qualifier adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class QualifierAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(QualifierAdapter.class);

	private KeyNameAdapter qualifier;
	
	private Integer entityId;
	
	private int countItems = 0;
	
	private int countAlerts = 0;
	
	private int countWarnings = 0;
	
	private int countOthers = 0;
	
	/**
	 * Constructor.
	 */
	public QualifierAdapter() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param qualifier
	 */
	public QualifierAdapter(KeyNameAdapter qualifier) {
		this();
		setQualifier(qualifier);
	}

	/**
	 * Construtor.
	 * 
	 * @param entityId
	 * @param qualifier
	 */
	public QualifierAdapter(Integer entityId, KeyNameAdapter qualifier) {
		this(qualifier);
		setEntityId(entityId);
	}

	/**
	 * Qualifier.
	 */
	@JsonIgnore
	public KeyNameAdapter getQualifier() {
		return qualifier;
	}
	public void setQualifier(KeyNameAdapter qualifier) {
		this.qualifier = qualifier;
	}
	
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 * Qualifier value.
	 */
	public Serializable getQualifierValue() {
		if (qualifier==null) {
			return new Integer(0);
		}
		return qualifier.getKey();
	}
	
	/**
	 * Qualifier name.
	 */
	public String getQualifierName() {
		if (qualifier==null) {
			return null;
		}
		return qualifier.getName();
	}
	
	/**
	 * Item count.
	 */
	public int getCountItems() {
		return countItems;
	}
	public void setCountItems(int countItems) {
		this.countItems = countItems;
	}

	@JsonIgnore
	public QualifierAdapter setCountItems(List<SimpleCounter> counterList) {
		for (SimpleCounter counter: counterList) {
			if (match(counter)) {
				setCountItems((int) counter.getItemCount());
				logger.debug("Found {} items.", counter.getItemCount());
				break;
			}
		}
		return this;
	}
	
	/**
	 * True if base class equals qualifier.
	 * 
	 * @param counter
	 */
	protected boolean match(SimpleCounter counter) {
		return  (counter.getBaseClass()!=null 
					&& counter.getBaseClass().equals(getQualifierValue())) 
				||
				(counter.getBaseClass()==null 
					&& getQualifierValue() instanceof Integer
					&& getQualifierValue().equals(new Integer(0)))
				||
				(counter.getBaseClass()==null 
					&& getQualifierValue() instanceof Character
					&& getQualifierValue().equals(new Character('_')));
	}
	
	public static List<QualifierAdapter> qualifierAdapterList(KeyNameAdapter[] qualifierArray) {
		List<QualifierAdapter> qualifierAdapterList = new ArrayList<QualifierAdapter>();
		for (KeyNameAdapter qualifier: qualifierArray) {
			qualifierAdapterList.add(new QualifierAdapter(qualifier));
		}
		return qualifierAdapterList;
	}
	
	public static List<QualifierAdapter> qualifierAdapterList(List<Category> categoryList) {
		return qualifierAdapterList(categoryList, true);
	}
	
	public static List<QualifierAdapter> qualifierAdapterList(List<Category> categoryList, boolean addEmpty) {
		List<QualifierAdapter> qualifierAdapterList = new ArrayList<QualifierAdapter>();
		for (Category qualifier: categoryList) {
			qualifierAdapterList.add(new QualifierAdapter(qualifier));
		}
		if (addEmpty) {
			qualifierAdapterList.add(new QualifierAdapter());
		}
		return qualifierAdapterList;
	}
	
	public static List<QualifierAdapter> categoryAdapterList(List<CategoryReadAdapter> categoryAdapterList, boolean addEmpty) {
		List<QualifierAdapter> qualifierAdapterList = new ArrayList<QualifierAdapter>();
		for (CategoryReadAdapter qualifier: categoryAdapterList) {
			qualifierAdapterList.add(new QualifierAdapter(qualifier.getEntityId() , qualifier));
		}
		if (addEmpty) {
			qualifierAdapterList.add(new QualifierAdapter());
		}
		return qualifierAdapterList;
	}
	
	public int getCountAlerts() {
		return countAlerts;
	}
	public void setCountAlerts(int countAlerts) {
		this.countAlerts = countAlerts;
	}

	@JsonIgnore
	public QualifierAdapter setCountAlerts(List<SimpleCounter> counterList) {
		for (SimpleCounter counter: counterList) {
			if (match(counter)) {
				setCountAlerts((int) counter.getItemCount());
				logger.debug("Found {} alerts.", counter.getItemCount());
				break;
			}
		}
		return this;
	}
	
	public int getCountWarnings() {
		return countWarnings;
	}
	public void setCountWarnings(int countWarnings) {
		this.countWarnings = countWarnings;
	}
	
	@JsonIgnore
	public QualifierAdapter setCountWarnings(List<SimpleCounter> counterList) {
		for (SimpleCounter counter: counterList) {
			if (match(counter)) {
				setCountWarnings((int) counter.getItemCount());
				logger.debug("Found {} warnings.", counter.getItemCount());
				break;
			}
		}
		return this;
	}
	
	public int getCountOthers() {
		return countOthers;
	}
	public void setCountOthers(int countOthers) {
		this.countOthers = countOthers;
	}
	
	@JsonIgnore
	public QualifierAdapter setCountOthers(List<SimpleCounter> counterList) {
		for (SimpleCounter counter: counterList) {
			if (counter.getBaseClass()==getQualifierValue()) {
				setCountOthers((int) counter.getItemCount());
				logger.debug("Found {} others.", counter.getItemCount());
				break;
			}
		}
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((qualifier == null) ? 0 : qualifier.hashCode());
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
		if (!(obj instanceof QualifierAdapter)) {
			return false;
		}
		QualifierAdapter other = (QualifierAdapter) obj;
		if (qualifier == null) {
			if (other.qualifier != null) {
				return false;
			}
		} else if (!qualifier.equals(other.qualifier)) {
			return false;
		}
		return true;
	}

}
