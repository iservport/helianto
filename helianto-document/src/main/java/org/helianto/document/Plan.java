package org.helianto.document;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Plan interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Plan extends Serializable {

    /**
     * Scheduled start date.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getScheduledStartDate();
    
    /**
     *Scheduled end date.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getScheduledEndDate();
    
}
