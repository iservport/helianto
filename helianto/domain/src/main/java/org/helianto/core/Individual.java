package org.helianto.core;
// Generated Apr 24, 2006 9:34:24 PM by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Domain object to extend <code>AddressableEntity</code> as an
 * individual.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core1.hbm.xml 75 2006-04-17 21:45:55 -0300 (Seg, 17 Abr 2006) iserv $
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
    public Individual(Home home, String alias, String entityAddress1, String entityAddress2, String entityAddress3, String entityCityName, Province province, String entityPostalCode, Credential credential) {
        super(home, alias, entityAddress1, entityAddress2, entityAddress3, entityCityName, province, entityPostalCode);        
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
