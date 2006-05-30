package org.helianto.core;
// Generated 30/05/2006 12:01:27 by Hibernate Tools 3.1.0.beta4



/**
 * MailAccessData generated by hbm2java
 */

public class MailAccessData  implements java.io.Serializable {


    // Fields    

     private String storeType;
     private String host;
     private String user;
     private String password;


    // Constructors

    /** default constructor */
    public MailAccessData() {
    }

    
    /** full constructor */
    public MailAccessData(String storeType, String host, String user, String password) {
        this.storeType = storeType;
        this.host = host;
        this.user = user;
        this.password = password;
    }
    

   
    // Property accessors

    public String getStoreType() {
        return this.storeType;
    }
    
    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getHost() {
        return this.host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
   








}
