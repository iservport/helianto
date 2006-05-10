package org.helianto.core;
// Generated 09/05/2006 16:42:41 by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Domain object to extend <code>Entity</code> with address
 * information.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core1.hbm.xml 75 2006-04-17 21:45:55 -0300 (Seg, 17 Abr 2006) iserv $
 * 				
 * 			
 */

public class AddressableEntity extends org.helianto.core.Entity implements java.io.Serializable {


    // Fields    

     private String entityAddress1;
     private String entityAddress2;
     private String entityAddress3;
     private String entityCityName;
     private Province province;
     private String entityPostalCode;


    // Constructors

    /** default constructor */
    public AddressableEntity() {
    }

	/** minimal constructor */
    public AddressableEntity(Home home, String alias) {
        super(home, alias);        
    }
    
    /** full constructor */
    public AddressableEntity(Home home, String alias, String entityAddress1, String entityAddress2, String entityAddress3, String entityCityName, Province province, String entityPostalCode) {
        super(home, alias);        
        this.entityAddress1 = entityAddress1;
        this.entityAddress2 = entityAddress2;
        this.entityAddress3 = entityAddress3;
        this.entityCityName = entityCityName;
        this.province = province;
        this.entityPostalCode = entityPostalCode;
    }
    

   
    // Property accessors

    public String getEntityAddress1() {
        return this.entityAddress1;
    }
    
    public void setEntityAddress1(String entityAddress1) {
        this.entityAddress1 = entityAddress1;
    }

    public String getEntityAddress2() {
        return this.entityAddress2;
    }
    
    public void setEntityAddress2(String entityAddress2) {
        this.entityAddress2 = entityAddress2;
    }

    public String getEntityAddress3() {
        return this.entityAddress3;
    }
    
    public void setEntityAddress3(String entityAddress3) {
        this.entityAddress3 = entityAddress3;
    }

    public String getEntityCityName() {
        return this.entityCityName;
    }
    
    public void setEntityCityName(String entityCityName) {
        this.entityCityName = entityCityName;
    }

    public Province getProvince() {
        return this.province;
    }
    
    public void setProvince(Province province) {
        this.province = province;
    }

    public String getEntityPostalCode() {
        return this.entityPostalCode;
    }
    
    public void setEntityPostalCode(String entityPostalCode) {
        this.entityPostalCode = entityPostalCode;
    }
   








}
