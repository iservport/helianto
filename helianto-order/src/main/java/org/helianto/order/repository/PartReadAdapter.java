package org.helianto.order.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.order.domain.Currency;
import org.helianto.order.domain.Part;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Part adapter.
 *
 * @author mauriciofernandesdecastro
 */
public class PartReadAdapter
	implements Serializable
{

	private static final long serialVersionUID = 1L;

	protected Integer id;

	protected Part adaptee;

	protected Integer entityId;

	protected Integer categoryId;

	protected String categoryCode = "";
    
	protected String categoryName = "";
    
	protected Integer ownerId;

	protected Integer currencyId;

	protected Date issueDate;

	protected String docCode = "";

	protected String docName = "";

	protected String docAbstract = "";

	protected Character activityState;

	protected Boolean docFlag;

	protected BigDecimal docValue;

	protected String template = "";
    
    /**
     * Constructor.
     */
    public PartReadAdapter() {
		super();
	}
	
	/**
	 * Constructor.
	 *
	 * @param id
	 * @param entityId
	 * @param categoryId
	 * @param ownerId
	 * @param currencyId
	 */
	public PartReadAdapter(
		Integer entityId
		, Integer categoryId
		, Integer ownerId
		, Integer currencyId
		) {
		this();
		this.entityId = entityId;
		this.categoryId = categoryId;
		this.ownerId = ownerId; 
		this.currencyId = currencyId;
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 * @param entityId
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 * @param ownerId
	 * @param currencyId
	 * @param issueDate
	 * @param docCode
	 * @param docName
	 * @param docAbstract
	 * @param activityState
	 * @param docFlag
	 * @param docValue
	 * @param template
	 */
	public PartReadAdapter(
		Integer id
		, Integer entityId
		, Integer categoryId
		, String categoryCode
		, String categoryName
		, Integer ownerId
		, Integer currencyId
		, Date issueDate
		, String docCode
		, String docName
		, String docAbstract
		, Character activityState
		, Boolean docFlag
		, BigDecimal docValue
	    , String template
		) {
		this(entityId, categoryId, ownerId, currencyId);
		this.id = id; 
		this.categoryCode = categoryCode; 
		this.categoryName = categoryName;
		this.issueDate = issueDate;
		this.docCode = docCode;
		this.docName = docName;
		this.docAbstract = docAbstract;
		this.activityState = activityState;
		this.docFlag = docFlag;
		this.docValue = docValue;
		this.template = template;
	}

	/**
	 * Adaptee builder.
	 *
	 * @param entity
	 * @param category
	 * @param owner
	 * @param currency
	 */
	public PartReadAdapter build(Entity entity, Category category, Identity owner, Currency currency){
		if (entity==null) throw new UnsupportedOperationException("Entity required.");
		if (category==null) throw new UnsupportedOperationException("Category required.");
		if (owner==null) throw new UnsupportedOperationException("Identity required.");
		if (currency==null) throw new UnsupportedOperationException("Currency required.");
		if (adaptee==null) {
			adaptee = new Part(entity, getCategoryCode());
			this.entityId = entity.getId();
		}
		adaptee.setCategory(category);
		this.categoryId = category.getId();
		this.categoryCode = category.getCategoryCode();
		this.categoryName = category.getCategoryName();
		this.template = category.getScriptItems();
		adaptee.setOwner(owner);
		this.ownerId = owner.getId();
		adaptee.setCurrency(currency);
		this.currencyId = currency.getId();
		return this;
	}

	/**
	 * Adaptee merger.
	 */
	public PartReadAdapter merge() {
		if (adaptee==null) throw new UnsupportedOperationException("Part required.");
		adaptee.setId(this.id);
		adaptee.setDocCode(this.docCode);
		adaptee.setIssueDate(this.issueDate);
		adaptee.setDocCode(this.docCode);
		adaptee.setDocName(this.docName);
		adaptee.setDocAbstract(this.docAbstract);
		adaptee.setActivityState(this.activityState);
		adaptee.setDocFlag(this.docFlag);
		adaptee.setDocValue(this.docValue);
		return this;
	}

	/**
	 * Adaptee merger.
	 *
	 * @param adaptee
	 */
	public PartReadAdapter merge(Part adaptee) {
		if (adaptee==null) throw new UnsupportedOperationException("Part required.");
		this.adaptee = adaptee;
		this.id = adaptee.getId();
		this.docCode = adaptee.getDocCode();
		this.issueDate = adaptee.getIssueDate();
		this.docName = adaptee.getDocName();
		this.docAbstract = adaptee.getDocAbstract();
		this.activityState = adaptee.getActivityState();
		this.docFlag = adaptee.isDocFlag();
		this.docValue = adaptee.getDocValue();
		return this;
	}

	@JsonIgnore
	public Part getAdaptee() {
		return adaptee;
	}
	public PartReadAdapter setAdaptee(Part adaptee) {
		this.adaptee = adaptee;
		return this;
	}

	public Integer getId() {
		return id;
	}
	public PartReadAdapter setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public Integer getOwnerId() {
		return ownerId;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public String getDocCode() {
		return docCode;
	}

	public String getDocName() {
		return docName;
	}

	public String getDocAbstract() {
		return docAbstract;
	}

	public Character getActivityState() {
		return activityState;
	}

	public Boolean getDocFlag() {
		return docFlag;
	}

	public BigDecimal getDocValue() {
		return docValue;
	}
	
	public String getTemplate() {
		return template;
	}

	@Override
	public int hashCode() {
		return 31 + ((id == null) ? 0 : id.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		PartReadAdapter other = (PartReadAdapter) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

}

