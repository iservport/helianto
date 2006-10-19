package org.helianto.process;
// Generated 24/09/2006 12:54:26 by Hibernate Tools 3.1.0.beta4

import java.util.List;
import org.helianto.core.Entity;


/**
 * 			
 * <p>
 * A part.
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 * 		
 */

public class Part extends org.helianto.process.Document implements java.io.Serializable {


    // Fields    

     private boolean hasDrawing;
     private MaterialType materialType;


    // Constructors

    /** default constructor */
    public Part() {
    }

	/** minimal constructor */
    public Part(Entity entity, String docCode, boolean hasDrawing) {
        super(entity, docCode);        
        this.hasDrawing = hasDrawing;
    }
    

   
    // Property accessors

    public boolean isHasDrawing() {
        return this.hasDrawing;
    }
    
    public void setHasDrawing(boolean hasDrawing) {
        this.hasDrawing = hasDrawing;
    }

    public MaterialType getMaterialType() {
        return this.materialType;
    }
    
    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
   








}
