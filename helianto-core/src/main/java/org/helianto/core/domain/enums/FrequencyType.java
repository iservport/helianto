package org.helianto.core.domain.enums;

import java.util.Date;

import org.joda.time.DateMidnight;
import org.joda.time.Period;

/**
 * Frequency type.
 * 
 * @author mauriciofernandesdecastro
 */
public enum FrequencyType {
	
	NOT_REQUIRED("P0D"),
	INITIAL("P0D"),
	DAILY("P1D"),
	WEEKLY("P1W"),
	BIWEEKLY("P2W"),
	MONTHLY("P1M"),
	BIMONTHLY("P2M"),
	QUARTERLY("P3M"),
	SEMIANNUALLY("P6M"),
	ANNUALLY("P1Y"),
	BIENNIALLY("P2Y"),
	TRIENNIALLY("P3Y"),
	QUINQUENNIALLY("P5Y"),
	DECENNIALLY("P9Y");
	
	private String periodString;
	
	private FrequencyType(String periodString) {
		this.periodString = periodString;
	}
	
	/**
	 * Calculate new date.
	 * 
	 * @param checkDate
	 */
	public Date getNextDate(Date checkDate) {
		if (this.equals(NOT_REQUIRED)) {
			return null;
		}
		Period period = Period.parse(periodString);
		return new DateMidnight(checkDate).plus(period).toDate();
	}

}
