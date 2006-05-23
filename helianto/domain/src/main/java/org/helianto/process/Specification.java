package org.helianto.process;
// Generated 23/05/2006 20:22:15 by Hibernate Tools 3.1.0.beta4

import java.util.List;
import org.helianto.core.Entity;


/**
 * 				
 * <p>
 * A process characteristic specification.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process1.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
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

	/** minimal constructor */
    public Specification(Entity entity, String docCode, SpecificationLimit specificationLimit) {
        super(entity, docCode);        
        this.specificationLimit = specificationLimit;
    }
    
    /** full constructor */
    public Specification(Entity entity, String docCode, String docName, String docUrl, List<Tree> children, Unit unit, SpecificationLimit specificationLimit) {
        super(entity, docCode, docName, docUrl, children);        
        this.unit = unit;
        this.specificationLimit = specificationLimit;
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
