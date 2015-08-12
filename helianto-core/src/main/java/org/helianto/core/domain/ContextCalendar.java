package org.helianto.core.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Context calendar.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_calendar",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextId", "calendarCode"})}
)
public class ContextCalendar 
	extends AbstractCalendar
{

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contextId", nullable=true)
    private Operator context;
    
    /**
     * Constructor.
     */
    public ContextCalendar() {
		super();
	}

    /**
     * Constructor.
     * 
     * @param context
     * @param calendarCode
     */
    public ContextCalendar(Operator context, String calendarCode) {
		this();
		setContext(context);
		setCalendarCode(calendarCode);
	}

    /**
     * Context defining the calendar.
     */
	public Operator getContext() {
		return context;
	}
	public void setContext(Operator context) {
		this.context = context;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result
				+ ((getCalendarCode() == null) ? 0 : getCalendarCode().hashCode());
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
		ContextCalendar other = (ContextCalendar) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (getCalendarCode() == null) {
			if (other.getCalendarCode() != null)
				return false;
		} else if (!getCalendarCode().equals(other.getCalendarCode()))
			return false;
		return true;
	}
    
}
