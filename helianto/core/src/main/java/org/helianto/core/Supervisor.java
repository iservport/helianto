package org.helianto.core;
// Generated Mar 13, 2006 12:21:12 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * A domain object to represent a supervisor, unique by a short name.
 * </p>
 * <p>
 * Supervisors are the source for comunication between server and
 * e-mail recipients, so they hold the information necessary to connect to 
 * external servers. They can be configured to represent a geographical
 * limit, bound to a language or country, or according to any territory
 * arrangement.
 * </p>
 * <p>
 * At least one default (root) supervisor must be defined per installation.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
 */

public class Supervisor  implements java.io.Serializable {


    // Fields    

     private int id;
     private Supervisor parent;
     private String supervisorName;
     private String httpAddress;
     private String supervisorDesc;
     private Date added;
     private Locale locale;
     private MailTransportData mailTransportData;
     private MailAccessData mailAccessData;


    // Constructors

    /** default constructor */
    public Supervisor() {
    }

	/** minimal constructor */
    public Supervisor(String supervisorName) {
        this.supervisorName = supervisorName;
    }
    
    /** full constructor */
    public Supervisor(Supervisor parent, String supervisorName, String httpAddress, String supervisorDesc, Date added, Locale locale, MailTransportData mailTransportData, MailAccessData mailAccessData) {
        this.parent = parent;
        this.supervisorName = supervisorName;
        this.httpAddress = httpAddress;
        this.supervisorDesc = supervisorDesc;
        this.added = added;
        this.locale = locale;
        this.mailTransportData = mailTransportData;
        this.mailAccessData = mailAccessData;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Supervisor getParent() {
        return this.parent;
    }
    
    public void setParent(Supervisor parent) {
        this.parent = parent;
    }

    public String getSupervisorName() {
        return this.supervisorName;
    }
    
    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getHttpAddress() {
        return this.httpAddress;
    }
    
    public void setHttpAddress(String httpAddress) {
        this.httpAddress = httpAddress;
    }

    public String getSupervisorDesc() {
        return this.supervisorDesc;
    }
    
    public void setSupervisorDesc(String supervisorDesc) {
        this.supervisorDesc = supervisorDesc;
    }

    public Date getAdded() {
        return this.added;
    }
    
    public void setAdded(Date added) {
        this.added = added;
    }

    public Locale getLocale() {
        return this.locale;
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public MailTransportData getMailTransportData() {
        return this.mailTransportData;
    }
    
    public void setMailTransportData(MailTransportData mailTransportData) {
        this.mailTransportData = mailTransportData;
    }

    public MailAccessData getMailAccessData() {
        return this.mailAccessData;
    }
    
    public void setMailAccessData(MailAccessData mailAccessData) {
        this.mailAccessData = mailAccessData;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Supervisor) ) return false;
		 Supervisor castOther = ( Supervisor ) other; 
         
		 return ( (this.getSupervisorName()==castOther.getSupervisorName()) || ( this.getSupervisorName()!=null && castOther.getSupervisorName()!=null && this.getSupervisorName().equals(castOther.getSupervisorName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         
         result = 37 * result + ( getSupervisorName() == null ? 0 : this.getSupervisorName().hashCode() );
         
         
         
         
         
         
         return result;
   }   





}
