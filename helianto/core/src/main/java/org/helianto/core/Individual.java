package org.helianto.core;
// Generated Apr 7, 2006 6:49:53 AM by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Domain object to extend <code>AddressableEntity</code> as an
 * individual.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core1.hbm.xml,v 1.7 2006/03/25 11:12:58 iserv Exp $
 * 				
 * 				
 */

public class Individual extends org.helianto.core.AddressableEntity implements java.io.Serializable {


    // Fields    

     private Credential credential;


    // Constructors

    /** default constructor */
    public Individual() {
    }

	/** minimal constructor */
    public Individual(Home home, String alias) {
        super(home, alias);        
    }
    
    /** full constructor */
    public Individual(Home home, String alias, String entityAddress1, String entityAddress2, String entityCityName, String entityProvinceName, String entityPostalCode, Credential credential) {
        super(home, alias, entityAddress1, entityAddress2, entityCityName, entityProvinceName, entityPostalCode);        
        this.credential = credential;
    }
    

   
    // Property accessors

    public Credential getCredential() {
        return this.credential;
    }
    
    public void setCredential(Credential credential) {
        this.credential = credential;
    }
   








}
