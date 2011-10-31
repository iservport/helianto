package org.helianto.document;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Interface para planos.
 * 
 * @author mauriciofernandesdecastro
 */
public interface Plan extends Serializable {

    /**
     * Data programada para início.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getScheduledStartDate();
    
    /**
     * Data programada para fim.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getScheduledEndDate();
    
}
