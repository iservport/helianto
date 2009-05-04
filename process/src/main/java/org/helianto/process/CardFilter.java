package org.helianto.process;

import javax.persistence.Transient;

import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

public class CardFilter extends AbstractUserBackedCriteriaFilter {
	
	private static final long serialVersionUID = 1L;
    private long internalNumber;
	private Process process;
    private char cardState = ' ';

	public static CardFilter cardFilterFactory(User user) {
		CardFilter cardFilter = new CardFilter();
		cardFilter.setUser(user);
		return cardFilter;
	}

	public void reset() {
	}

    /**
	 * @return the internalNumber
	 */
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

	/**
	 * @return the process
	 */
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	
	/**
	 * C�digo do processo.
	 */
	@Transient
	public String getProcessCode() {
		if (process!=null) {
			return process.getDocCode();
		}
		return "";
	}

	/**
	 * @return the cardState
	 */
	public char getCardState() {
		return cardState;
	}
	public void setCardState(char cardState) {
		this.cardState = cardState;
	}

}
