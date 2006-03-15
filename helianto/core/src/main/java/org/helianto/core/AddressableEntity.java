package org.helianto.core;
// Generated 14/03/2006 22:23:42 by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Domain object to extend <code>Entity</code> with address
 * information.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
 */

public class AddressableEntity extends org.helianto.core.Entity implements java.io.Serializable {


    // Fields    

     private String entityAddress1;
     private String entityAddress2;
     private String entityCityName;
     private String entityProvinceName;
     private String entityPostalCode;


    // Constructors

    /** default constructor */
    public AddressableEntity() {
    }

	/** minimal constructor */
    public AddressableEntity(Supervisor supervisor, String alias) {
        super(supervisor, alias);        
    }
    
    /** full constructor */
    public AddressableEntity(Supervisor supervisor, String alias, String entityAddress1, String entityAddress2, String entityCityName, String entityProvinceName, String entityPostalCode) {
        super(supervisor, alias);        
        this.entityAddress1 = entityAddress1;
        this.entityAddress2 = entityAddress2;
        this.entityCityName = entityCityName;
        this.entityProvinceName = entityProvinceName;
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

    public String getEntityCityName() {
        return this.entityCityName;
    }
    
    public void setEntityCityName(String entityCityName) {
        this.entityCityName = entityCityName;
    }

    public String getEntityProvinceName() {
        return this.entityProvinceName;
    }
    
    public void setEntityProvinceName(String entityProvinceName) {
        this.entityProvinceName = entityProvinceName;
    }

    public String getEntityPostalCode() {
        return this.entityPostalCode;
    }
    
    public void setEntityPostalCode(String entityPostalCode) {
        this.entityPostalCode = entityPostalCode;
    }
   








}
