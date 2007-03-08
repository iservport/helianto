package org.helianto.process;
// Generated 08/03/2007 19:38:51 by Hibernate Tools 3.2.0.beta8


import java.util.List;
import java.util.Set;
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

     private int partType;
     private boolean hasDrawing;
     private MaterialType materialType;
     private float weight;

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
    public Part(Entity entity, String docCode, String docName, Set<Tree> parentAssociations, List<Tree> childAssociations, int partType, boolean hasDrawing, MaterialType materialType, float weight) {
        super(entity, docCode, docName, parentAssociations, childAssociations);        
       this.partType = partType;
       this.hasDrawing = hasDrawing;
       this.materialType = materialType;
       this.weight = weight;
    }
   
    // Property accessors
    public int getPartType() {
        return this.partType;
    }
    
    public void setPartType(int partType) {
        this.partType = partType;
    }
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
    public float getWeight() {
        return this.weight;
    }
    
    public void setWeight(float weight) {
        this.weight = weight;
    }




}


