package org.helianto.inventory.filter.classic;

import javax.persistence.Transient;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;
import org.helianto.process.Process;
import org.helianto.user.domain.User;

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

	public boolean isSelection() {
		return false;
	}

	public String getObjectAlias() {
		return "card";
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
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
	 * Código do processo.
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
