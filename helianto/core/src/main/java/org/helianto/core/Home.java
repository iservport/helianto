package org.helianto.core;
// Generated 20/04/2006 07:09:21 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * 				
 * <p>
 * A domain object to represent a home, unique by a short name.
 * </p>
 * <p>
 * Home locations are the source for comunication between server and
 * e-mail recipients, so they hold the information necessary to connect to 
 * external servers. They can be configured to represent a geographical
 * limit, bound to a language or country, or according to any territory
 * arrangement.
 * </p>
 * <p>
 * At least one default (root) home must be defined per installation.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core0.hbm.xml 55 2006-04-07 07:04:17 -0300 (Sex, 07 Abr 2006) iserv $
 * 				
 * 		
 */

public class Home extends AbstractHome implements java.io.Serializable {


    // Fields    

     private int id;
     private Home parent;
     private String homeName;
     private String language;
     private String country;
     private String httpAddress;
     private String homeDesc;
     private Date added;
     private char serverMode;
     private MailTransportData mailTransportData;
     private MailAccessData mailAccessData;


    // Constructors

    /** default constructor */
    public Home() {
    }

	/** minimal constructor */
    public Home(String homeName, String language, String country, char serverMode) {
        this.homeName = homeName;
        this.language = language;
        this.country = country;
        this.serverMode = serverMode;
    }
    
    /** full constructor */
    public Home(Home parent, String homeName, String language, String country, String httpAddress, String homeDesc, Date added, char serverMode, MailTransportData mailTransportData, MailAccessData mailAccessData) {
        this.parent = parent;
        this.homeName = homeName;
        this.language = language;
        this.country = country;
        this.httpAddress = httpAddress;
        this.homeDesc = homeDesc;
        this.added = added;
        this.serverMode = serverMode;
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

    public Home getParent() {
        return this.parent;
    }
    
    public void setParent(Home parent) {
        this.parent = parent;
    }

    public String getHomeName() {
        return this.homeName;
    }
    
    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    public String getHttpAddress() {
        return this.httpAddress;
    }
    
    public void setHttpAddress(String httpAddress) {
        this.httpAddress = httpAddress;
    }

    public String getHomeDesc() {
        return this.homeDesc;
    }
    
    public void setHomeDesc(String homeDesc) {
        this.homeDesc = homeDesc;
    }

    public Date getAdded() {
        return this.added;
    }
    
    public void setAdded(Date added) {
        this.added = added;
    }

    public char getServerMode() {
        return this.serverMode;
    }
    
    public void setServerMode(char serverMode) {
        this.serverMode = serverMode;
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
		 if ( !(other instanceof Home) ) return false;
		 Home castOther = ( Home ) other; 
         
		 return ( (this.getHomeName()==castOther.getHomeName()) || ( this.getHomeName()!=null && castOther.getHomeName()!=null && this.getHomeName().equals(castOther.getHomeName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         
         result = 37 * result + ( getHomeName() == null ? 0 : this.getHomeName().hashCode() );
         
         
         
         
         
         
         
         
         return result;
   }   





}
