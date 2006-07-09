package org.helianto.core;
// Generated 09/07/2006 19:17:05 by Hibernate Tools 3.1.0.beta4



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

     private Identity identity;


    // Constructors

    /** default constructor */
    public Individual() {
    }

	/** minimal constructor */
    public Individual(Home home, String alias) {
        super(home, alias);        
    }
    
    /** full constructor */
    public Individual(Home home, String alias, String entityAddress1, String entityAddress2, String entityAddress3, String entityCityName, Province province, String entityPostalCode, Identity identity) {
        super(home, alias, entityAddress1, entityAddress2, entityAddress3, entityCityName, province, entityPostalCode);        
        this.identity = identity;
    }
    

   
    // Property accessors

    public Identity getIdentity() {
        return this.identity;
    }
    
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
   








}
