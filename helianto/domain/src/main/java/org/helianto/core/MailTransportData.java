package org.helianto.core;
// Generated 05/06/2006 11:48:03 by Hibernate Tools 3.1.0.beta4



/**
 * MailTransportData generated by hbm2java
 */

public class MailTransportData  implements java.io.Serializable {


    // Fields    

     private String smtpHost;
     private String smtpUser;
     private String smtpPassword;


    // Constructors

    /** default constructor */
    public MailTransportData() {
    }

    
    /** full constructor */
    public MailTransportData(String smtpHost, String smtpUser, String smtpPassword) {
        this.smtpHost = smtpHost;
        this.smtpUser = smtpUser;
        this.smtpPassword = smtpPassword;
    }
    

   
    // Property accessors

    public String getSmtpHost() {
        return this.smtpHost;
    }
    
    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpUser() {
        return this.smtpUser;
    }
    
    public void setSmtpUser(String smtpUser) {
        this.smtpUser = smtpUser;
    }

    public String getSmtpPassword() {
        return this.smtpPassword;
    }
    
    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }
   








}
