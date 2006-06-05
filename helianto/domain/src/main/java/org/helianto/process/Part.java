package org.helianto.process;
// Generated 05/06/2006 20:39:13 by Hibernate Tools 3.1.0.beta4

import java.util.List;
import org.helianto.core.Entity;


/**
 * 				
 * <p>
 * A part.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id: iservport-process1.hbm.xml,v 1.2 2006/03/13 15:29:13 iserv Exp $
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
    
    /** full constructor */
    public Part(Entity entity, String docCode, String docName, String docUrl, List<Tree> children, boolean hasDrawing, MaterialType materialType) {
        super(entity, docCode, docName, docUrl, children);        
        this.hasDrawing = hasDrawing;
        this.materialType = materialType;
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
