package org.helianto.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.def.ActivityState;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.internal.InterpretableCategory;
import org.helianto.document.Documentable;
import org.helianto.document.internal.AbstractDocument;
import org.helianto.user.domain.User;


/**
 * A part.
 * 
 * @author mauriciofernandesdecastro
 */

@javax.persistence.Entity
@Table(name = "ord_part", 
	uniqueConstraints = { @UniqueConstraint(columnNames = {"entityId", "docCode"})}
)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.CHAR
)
@DiscriminatorValue("A")

public class Part
	extends AbstractDocument
	implements 
	  Documentable
	, InterpretableCategory
	, Comparable<Part>

{

	private static final long serialVersionUID = 1L;
	
    @ManyToOne
    @JoinColumn(name="categoryId", nullable=true)
    private Category category;
    
    @Column(length=512)
    private String parsedContent;
    
	@ManyToOne
	@JoinColumn(name="tokenId")
	private Token token;
	
	private char activityState = 'T';
	
    @ManyToOne
    @JoinColumn(name="currencyId", nullable=true)
    private Currency currency;
    
    private BigDecimal docValue;
    
    private Boolean docFlag;
    
	public String getDiscriminator() {
		return "A";
	}

	/**
	 * Default constructor.
	 */
	public Part() {
		super();
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param docCode
	 */
	public Part(Entity entity, String docCode) {
		this();
		setEntity(entity);
		setDocCode(docCode);
	}
	
	/**
	 * Form constructor.
	 * 
	 * @param user
	 */
	public Part(User user) {
		this(user.getEntity(), "");
		setOwner(user.getIdentity());
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	protected final boolean isCategoryForScript() {
		return getCategory()!=null 
				&& getCategory().getScriptItems()!=null 
				&& getCategory().getScriptItems().trim().length()>0;
	}
	
	@Override
	public String getScriptItems() {
		if (isCategoryForScript()) {
			return getCategory().getScriptItems();
		}
		return "";
	}

	@Override
	public String[] getScriptItemsAsArray() {
		if (isCategoryForScript()) {
			return getCategory().getScriptItemsAsArray();
		}
		return new String[] {};
	}

	@Override
	public List<String> getScriptList() {
		if (isCategoryForScript()) {
			return getCategory().getScriptList();
		}
		return new ArrayList<String>();
	}
	
    public String getParsedContent() {
		return parsedContent;
	}
    public void setParsedContent(String parsedContent) {
		this.parsedContent = parsedContent;
	}
    
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	
	public char getActivityState() {
		return activityState;
	}
	public void setActivityState(char activityState) {
		this.activityState = activityState;
	}
	public void setActivityStateAsEnum(ActivityState activityState) {
		setActivityState(activityState.getValue());
	}
	
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public BigDecimal getDocValue() {
		return docValue;
	}
	public void setDocValue(BigDecimal docValue) {
		this.docValue = docValue;
	}
	
	public Boolean isDocFlag() {
		return docFlag;
	}
	public void setDocFlag(Boolean docFlag) {
		this.docFlag = docFlag;
	}
	
	@Override
	public int compareTo(Part other) {
		if (getDocCode()!=null && other!=null) {
			return getDocCode().compareTo(other.getDocCode());
		}
		return 0;
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Part) {
			return super.equals(other);
		}
		return false;
	}

}
