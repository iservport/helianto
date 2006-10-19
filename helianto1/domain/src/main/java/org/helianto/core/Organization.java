package org.helianto.core;
// Generated 18/10/2006 20:52:47 by Hibernate Tools 3.1.0.beta5



/**
 * 				
 * <p>
 * Domain object to extend <code>Entity</code> as an
 * organization.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 			
 */
public class Organization extends org.helianto.core.Entity implements java.io.Serializable {

    // Fields    

     private String businessName;

     // Constructors

    /** default constructor */
    public Organization() {
    }

	/** minimal constructor */
    public Organization(Operator operator, String alias) {
        super(operator, alias);        
    }
    /** full constructor */
    public Organization(Operator operator, String alias, String businessName) {
        super(operator, alias);        
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


