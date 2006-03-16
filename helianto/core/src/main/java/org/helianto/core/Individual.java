package org.helianto.core;
// Generated 16/03/2006 06:29:39 by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Domain object to extend <code>AddressableEntity</code> as an
 * individual.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
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
    public Individual(Supervisor supervisor, String alias, Credential credential) {
        super(supervisor, alias);        
        this.credential = credential;
    }
    
    /** full constructor */
    public Individual(Supervisor supervisor, String alias, String entityAddress1, String entityAddress2, String entityCityName, String entityProvinceName, String entityPostalCode, Credential credential) {
        super(supervisor, alias, entityAddress1, entityAddress2, entityCityName, entityProvinceName, entityPostalCode);        
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
