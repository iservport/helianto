package org.helianto.message;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.Service;

/**
 * Define notification events per service.
 * 
 * <p>
 * Some services may require one or more recipients to be notified on the occurrence of an event.
 * This class should provide a list of valid events to be associated to such recipients.
 * </p>
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="msg_event",
	   uniqueConstraints = {@UniqueConstraint(columnNames={"serviceId", "eventCode"})}
)
public class ServiceEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int version;
	private Service service;
	private String eventCode;
	
	/**
	 * Empty constructor.
	 */
	public ServiceEvent() {
		setEventCode("");
	}
	
	/**
	 * Service constructor.
	 * 
	 * @param service
	 */
	public ServiceEvent(Service service) {
		this();
		setService(service);
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param service
	 * @param eventCode
	 */
	public ServiceEvent(Service service, String eventCode) {
		this(service);
		setEventCode(eventCode);
	}
	
    /**
     * Chave primária.
     */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Versão do registro na base de dados.
     */
	@Version
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * Service where notifications come from.
     */
    @ManyToOne
    @JoinColumn(name="serviceId")
    public Service getService() {
		return service;
	}
    public void setService(Service service) {
		this.service = service;
	}
    
    /**
     * Convenience to read the service name.
     */
    @Transient
    public String getServiceName() {
		if (getService()!=null) {
			return getService().getServiceName();
		}
		return "";
	}
    
    /**
     * Notification code.
     */
    @Column(length=20)
    public String getEventCode() {
		return eventCode;
	}
    public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
    
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("service").append("='").append(getService()).append("' ");
        buffer.append("eventCode").append("='").append(getEventCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

    /**
     * equals
     */
    @Override
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof ServiceEvent) ) return false;
        ServiceEvent castOther = (ServiceEvent) other; 
         
        return ((this.getService()==castOther.getService()) 
        		|| ( this.getService()!=null && castOther.getService()!=null 
        				&& this.getService().equals(castOther.getService()) ))
            && ((this.getEventCode()==castOther.getEventCode()) 
            		|| ( this.getEventCode()!=null && castOther.getEventCode()!=null 
            				&& this.getEventCode().equals(castOther.getEventCode()) ));
    }
   
    /**
     * hashCode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + ( getService() == null ? 0 : this.getService().hashCode() );
        result = 37 * result + ( getEventCode() == null ? 0 : this.getEventCode().hashCode() );
        return result;
    }

}
