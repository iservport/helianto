package org.helianto.process;
// Generated 24/09/2006 12:54:25 by Hibernate Tools 3.1.0.beta4

import java.util.List;
import org.helianto.core.Entity;
import org.helianto.core.Unit;


/**
 * 			
 * <p>
 * A process characteristic specification.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Specification extends org.helianto.process.Document implements java.io.Serializable {


    // Fields    

     private Unit unit;
     private SpecificationLimit specificationLimit;


    // Constructors

    /** default constructor */
    public Specification() {
    }

    // Property accessors

    public Unit getUnit() {
        return this.unit;
    }
    
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public SpecificationLimit getSpecificationLimit() {
        return this.specificationLimit;
    }
    
    public void setSpecificationLimit(SpecificationLimit specificationLimit) {
        this.specificationLimit = specificationLimit;
    }
   








}
