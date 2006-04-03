package org.helianto.core;
// Generated 03/04/2006 06:42:28 by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Domain object to extend <code>AddressableEntity</code> as an
 * organization.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core1.hbm.xml,v 1.7 2006/03/25 11:12:58 iserv Exp $
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
    public Organization(Home home, String alias, String entityAddress1, String entityAddress2, String entityCityName, String entityProvinceName, String entityPostalCode, String businessName) {
        super(home, alias, entityAddress1, entityAddress2, entityCityName, entityProvinceName, entityPostalCode);        
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
