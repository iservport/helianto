package org.helianto.core;
// Generated Mar 13, 2006 12:20:57 PM by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Domain object to extend <code>AddressableEntity</code> as an
 * individual
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 				
 */

public class Individual extends org.helianto.core.AddressableEntity implements java.io.Serializable {


    // Fields    

     private String credentialName;


    // Constructors

    /** default constructor */
    public Individual() {
    }

	/** minimal constructor */
    public Individual(Supervisor supervisor, String alias) {
        super(supervisor, alias);        
    }
    
    /** full constructor */
    public Individual(Supervisor supervisor, String alias, String entityAddress1, String entityAddress2, String entityCityName, String entityProvinceName, String entityPostalCode, String credentialName) {
        super(supervisor, alias, entityAddress1, entityAddress2, entityCityName, entityProvinceName, entityPostalCode);        
        this.credentialName = credentialName;
    }
    

   
    // Property accessors

    public String getCredentialName() {
        return this.credentialName;
    }
    
    public void setCredentialName(String credentialName) {
        this.credentialName = credentialName;
    }
   








}
