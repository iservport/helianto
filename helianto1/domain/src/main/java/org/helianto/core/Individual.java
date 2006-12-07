package org.helianto.core;
// Generated 07/12/2006 11:35:10 by Hibernate Tools 3.2.0.beta8



/**
 * 				
 * <p>
 * Domain object to extend <code>Entity</code> as an
 * individual.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 			
 */
public class Individual extends org.helianto.core.Entity implements java.io.Serializable {

    // Fields    

     private Identity identity;

     // Constructors

    /** default constructor */
    public Individual() {
    }

	/** minimal constructor */
    public Individual(Operator operator, String alias) {
        super(operator, alias);        
    }
    /** full constructor */
    public Individual(Operator operator, String alias, Identity identity) {
        super(operator, alias);        
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


