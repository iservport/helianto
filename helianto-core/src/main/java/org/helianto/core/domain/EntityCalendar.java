package org.helianto.core.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity calendar.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_entityCalendar",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "calendarCode"})}
)
public class EntityCalendar 
	extends AbstractCalendar
{

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="entityId", nullable=true)
    private Entity entity;
    
    /**
     * Constructor.
     */
    public EntityCalendar() {
		super();
	}

    /**
     * Constructor.
     * 
     * @param entity
     * @param calendarCode
     */
    public EntityCalendar(Entity entity, String calendarCode) {
		this();
		setEntity(entity);
		setCalendarCode(calendarCode);
	}

    /**
     * Entity defining the calendar.
     */
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
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
		EntityCalendar other = (EntityCalendar) obj;
		if (entity == null) {
			if (other.entity != null)
				return false;
		} else if (!entity.equals(other.entity))
			return false;
		if (getCalendarCode() == null) {
			if (other.getCalendarCode() != null)
				return false;
		} else if (!getCalendarCode().equals(other.getCalendarCode()))
			return false;
		return true;
	}
    
}
