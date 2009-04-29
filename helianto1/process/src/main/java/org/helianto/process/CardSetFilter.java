package org.helianto.process;

import javax.persistence.Transient;

import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * Card set filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CardSetFilter extends AbstractUserBackedCriteriaFilter {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static CardSetFilter cardSetFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(CardSetFilter.class, user);
	}

	private static final long serialVersionUID = 1L;
    private long internalNumber;
	private Process process;
    private char cardType;
    
    /**
     * Default constructor.
     */
    public CardSetFilter() {
    	super();
    	setCardType(' ');
    }

	public void reset() {
		setCardType(' ');
	}
	
    @Override
	public boolean isSelection() {
		return getInternalNumber()>0;
	}

	/**
	 * Internal number filter
	 */
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

	/**
	 * Process filter
	 */
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	
	/**
	 * Process code filter.
	 */
	@Transient
	public String getProcessCode() {
		if (process!=null) {
			return process.getDocCode();
		}
		return "";
	}

	/**
	 * Card type filter.
	 */
	public char getCardType() {
		return this.cardType;
	}
	public void setCardType(char cardType) {
		this.cardType = cardType;
	}

}
