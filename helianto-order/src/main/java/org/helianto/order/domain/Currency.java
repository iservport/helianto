package org.helianto.order.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.domain.Operator;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Currency.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="core_currency",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextId", "currencyCode"})}
)
public class Currency 
	implements Serializable
{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private Integer version;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="contextId", nullable=true)
    private Operator context;
    
    @Column(length=8)
    private String currencyCode = "";
    
    @Column(length=64)
    private String currencyName = "";
    
    /**
     * Constructor.
     */
    public Currency() {
		super();
	}

    /**
     * Key constructor.
     * 
     * @param context
     * @param currencyCode
     */
	public Currency(Operator context, String currencyCode) {
		this();
		this.context = context;
		this.currencyCode = currencyCode;
	}

    /**
     * Full constructor.
     * 
     * @param context
     * @param currencyCode
     * @param currencyName
     */
	public Currency(Operator context, String currencyCode, String currencyName) {
		this(context, currencyCode);
		this.currencyName = currencyName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Operator getContext() {
		return context;
	}
	public void setContext(Operator context) {
		this.context = context;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((context == null) ? 0 : context.hashCode());
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
		Currency other = (Currency) obj;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		return true;
	}
	
}
