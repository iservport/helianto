package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.domain.Operator;
import org.helianto.order.domain.Currency;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Currency adapter.
 *
 * @author mauriciofernandesdecastro
 */
public class CurrencyReadAdapter
	implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Currency adaptee;

	private Integer contextId;

	private String currencyCode;

	private String currencyName;

	/**
	 * Construtor.
	 *
	 * @param id
	 * @param contextId;
	 * @param currencyCode;
	 * @param currencyName;
	 */
	public CurrencyReadAdapter(
		Integer id
		, Integer contextId
		, String currencyCode
		, String currencyName
		) {
		super();
		this.id = id;
		this.contextId = contextId;
		this.currencyCode = currencyCode;
		this.currencyName = currencyName;
	}

	/**
	 * Adaptee contructor.
	 *
	 * @param adaptee
	 */
	public CurrencyReadAdapter(Currency currency) {
		super();
		this.adaptee = currency;
	}

	/**
	 * Adaptee builder.
	 *
	 * @param context
	 */
	public CurrencyReadAdapter build(Operator context){
		if (adaptee==null) {
			throw new RuntimeException("Null currency cannot be persisted.");
		}
		return new CurrencyReadAdapter(adaptee.getId() 
		, context.getId()
		, getCurrencyCode()
		, getCurrencyName()
		);
	}

	@JsonIgnore
	public Currency getAdaptee() {
		return adaptee;
	}
	public void setAdaptee(Currency adaptee) {
		this.adaptee = adaptee;
		this.id = adaptee.getId();
		this.contextId = adaptee.getContext().getId();
		this.currencyCode = adaptee.getCurrencyCode();
		this.currencyName = adaptee.getCurrencyName();
	}

	public Integer getId() {
		return id;
	}

	public Integer getContextId() {
		return contextId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
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
		CurrencyReadAdapter other = (CurrencyReadAdapter) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

}
