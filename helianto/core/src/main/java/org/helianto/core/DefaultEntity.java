package org.helianto.core;
// Generated Mar 13, 2006 12:21:10 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 					
 * <p>
 * Domain object to provide a default <code>Entity</code> where only
 * one (or few) entities are required.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
 */

public class DefaultEntity extends org.helianto.core.Supervisor implements java.io.Serializable {


    // Fields    

     private Entity defaultEntity;
     private int priority;


    // Constructors

    /** default constructor */
    public DefaultEntity() {
    }

	/** minimal constructor */
    public DefaultEntity(String supervisorName, int priority) {
        super(supervisorName);        
        this.priority = priority;
    }
    
    /** full constructor */
    public DefaultEntity(Supervisor parent, String supervisorName, String httpAddress, String supervisorDesc, Date added, Locale locale, MailTransportData mailTransportData, MailAccessData mailAccessData, Entity defaultEntity, int priority) {
        super(parent, supervisorName, httpAddress, supervisorDesc, added, locale, mailTransportData, mailAccessData);        
        this.defaultEntity = defaultEntity;
        this.priority = priority;
    }
    

   
    // Property accessors

    public Entity getDefaultEntity() {
        return this.defaultEntity;
    }
    
    public void setDefaultEntity(Entity defaultEntity) {
        this.defaultEntity = defaultEntity;
    }

    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
   








}
