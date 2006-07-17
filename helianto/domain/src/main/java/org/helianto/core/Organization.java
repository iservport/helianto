package org.helianto.core;
// Generated 16/07/2006 09:41:43 by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Domain object to extend <code>AddressableEntity</code> as an
 * organization.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 				
 */

public class Organization extends org.helianto.core.AddressableEntity implements java.io.Serializable {


    // Fields    

     private String businessName;


    // Constructors

    /** default constructor */
    public Organization() {
    }

	/** minimal constructor */
    public Organization(Home home, String alias) {
        super(home, alias);        
    }
    
    /** full constructor */
    public Organization(Home home, String alias, String entityAddress1, String entityAddress2, String entityAddress3, String entityCityName, Province province, String entityPostalCode, String businessName) {
        super(home, alias, entityAddress1, entityAddress2, entityAddress3, entityCityName, province, entityPostalCode);        
        this.businessName = businessName;
    }
    

   
    // Property accessors

    public String getBusinessName() {
        return this.businessName;
    }
    
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
   








}
