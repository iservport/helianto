package org.helianto.process;
// Generated 05/05/2006 07:19:31 by Hibernate Tools 3.1.0.beta4



/**
 * 					
 * <p>
 * Specification limits.
 * </p>
 * 	
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core3.hbm.xml,v 1.4 2006/03/28 10:03:12 iserv Exp $
 * 				
 * 				
 */

public class SpecificationLimit  implements java.io.Serializable {


    // Fields    

     private double leftLimt;
     private double rightLimt;
     private double nominalValue;
     private int specificationType;


    // Constructors

    /** default constructor */
    public SpecificationLimit() {
    }

    
    /** full constructor */
    public SpecificationLimit(double leftLimt, double rightLimt, double nominalValue, int specificationType) {
        this.leftLimt = leftLimt;
        this.rightLimt = rightLimt;
        this.nominalValue = nominalValue;
        this.specificationType = specificationType;
    }
    

   
    // Property accessors

    public double getLeftLimt() {
        return this.leftLimt;
    }
    
    public void setLeftLimt(double leftLimt) {
        this.leftLimt = leftLimt;
    }

    public double getRightLimt() {
        return this.rightLimt;
    }
    
    public void setRightLimt(double rightLimt) {
        this.rightLimt = rightLimt;
    }

    public double getNominalValue() {
        return this.nominalValue;
    }
    
    public void setNominalValue(double nominalValue) {
        this.nominalValue = nominalValue;
    }

    public int getSpecificationType() {
        return this.specificationType;
    }
    
    public void setSpecificationType(int specificationType) {
        this.specificationType = specificationType;
    }
   








}
