package org.helianto.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Abstract calendar.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractCalendar 
	implements Serializable
{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column(length=12)
    private String calendarCode = "0101/";
    
    @Column(length=64)
    private String calendarName = "";
    
    private int duration = 1;
    
    private Character calendarType = 'H';
    
    /**
     * Default constructor.
     */
    public AbstractCalendar() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCalendarCode() {
		return calendarCode;
	}
	public void setCalendarCode(String calendarCode) {
		this.calendarCode = calendarCode;
	}

	public String getCalendarName() {
		return calendarName;
	}
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Character getCalendarType() {
		return calendarType;
	}
	public void setCalendarType(Character calendarType) {
		this.calendarType = calendarType;
	}
    
}
