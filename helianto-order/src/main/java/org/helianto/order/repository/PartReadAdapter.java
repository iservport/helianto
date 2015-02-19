package org.helianto.order.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.order.domain.Currency;
import org.helianto.order.domain.Part;


/**
 * Part adapter.
 *
 * @author mauriciofernandesdecastro
 */
public class PartReadAdapter
	implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Part adaptee;

	private Integer entityId;

	private Integer categoryId;

    private String categoryCode = "";
    
    private String categoryName = "";
    
	private Integer ownerId;

	private Integer currencyId;

	private Date issueDate;

	private String docCode = "";

	private String docName = "";

	private String docAbstract = "";

	private Character activityState;

	private Boolean docFlag;

	private BigDecimal docValue;

    private String template = "";
    
    /**
     * Constructor.
     */
    public PartReadAdapter() {
		super();
	}
	
	/**
	 * Construtor.
	 *
	 * @param id
	 * @param entityId
	 * @param categoryId
	 * @param categoryCode
	 * @param categoryName
	 */
	public PartReadAdapter(
		Integer id
		, Integer entityId
		, Integer categoryId
		, String categoryCode
		, String categoryName
		) {
		this();
		this.id = id;
		this.entityId = entityId;
		this.categoryId = categoryId;
		this.categoryCode = categoryCode; 
		this.categoryName = categoryName;
	}

	/**
	 * Construtor.
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
		this(id, entityId, categoryId, categoryCode, categoryName);
		this.ownerId = ownerId;
		this.currencyId = currencyId;
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
	 * Adaptee contructor.
	 *
	 * @param adaptee
	 */
	public PartReadAdapter(Part part) {
		super();
		this.adaptee = part;
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
		if (adaptee==null) {
			throw new RuntimeException("Null part cannot be persisted.");
		}
		return new PartReadAdapter(adaptee.getId() 
		, entity.getId()
		, category.getId()
		, category.getCategoryCode()
		, category.getCategoryName()
		, owner.getId()
		, currency.getId()
		, getIssueDate()
		, getDocCode()
		, getDocName()
		, getDocAbstract()
		, getActivityState()
		, getDocFlag()
		, getDocValue()
		, getTemplate()
		);
	}

	public Part getAdaptee() {
		return adaptee;
	}

	public Integer getId() {
		return id;
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

