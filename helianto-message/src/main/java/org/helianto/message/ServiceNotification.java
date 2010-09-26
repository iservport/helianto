package org.helianto.message;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.Service;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="msg_address",
	   uniqueConstraints = {@UniqueConstraint(columnNames={"serviceNotificationId", "notificationAddress"})}
)
public class ServiceNotification implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int version;
	private ServiceEvent serviceNotification;
	private String notificationAddress;
	private char notificationAddressType;
	private char priority;
	
	/**
	 * Empty constructor.
	 */
	public ServiceNotification() {
		setNotificationAddressType(NotificationAddressType.EMAIL);
		setPriority('0');
	}
	
	/**
	 * Service constructor.
	 * 
	 * @param service
	 */
	public ServiceNotification(Service service) {
		this();
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param service
	 * @param notificationAddress
	 */
	public ServiceNotification(Service service, String notificationAddress) {
		this(service);
	}
	
    /**
     * Primary key.
     */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * ORM version control.
     */
	@Version
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * Service notifications.
     */
    @ManyToOne
    @JoinColumn(name="serviceNotificationId")
    public ServiceEvent getServiceNotification() {
		return serviceNotification;
	}
    public void setServiceNotification(ServiceEvent serviceNotification) {
		this.serviceNotification = serviceNotification;
	}
    
    /**
     * Address to notify.
     */
    @Column(length=40)
    public String getNotificationAddress() {
		return notificationAddress;
	}
    public void setNotificationAddress(String notificationAddress) {
		this.notificationAddress = notificationAddress;
	}
    
    /**
     * Notification types.
     */
    public char getNotificationAddressType() {
		return notificationAddressType;
	}
    public void setNotificationAddressType(char notificationAddressType) {
		this.notificationAddressType = notificationAddressType;
	}
    public void setNotificationAddressType(NotificationAddressType notificationAddressType) {
		this.notificationAddressType = notificationAddressType.getValue();
	}
    
    /**
     * Priority.
     */
    public char getPriority() {
		return priority;
	}
    public void setPriority(char priority) {
		this.priority = priority;
	}
    
}
