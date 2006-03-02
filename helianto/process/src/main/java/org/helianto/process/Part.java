package org.helianto.process;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Entity;


/** 
 * 				
 * <p>
 * A part.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
*/
public class Part extends Document implements Serializable {

    /** persistent field */
    private boolean hasDrawing;

    /** nullable persistent field */
    private org.helianto.process.Material material;

    /** full constructor */
    public Part(String docCode, String docName, Entity entity, List children, boolean hasDrawing, org.helianto.process.Material material) {
        super(docCode, docName, entity, children);
        this.hasDrawing = hasDrawing;
        this.material = material;
    }

    /** default constructor */
    public Part() {
    }

    /** minimal constructor */
    public Part(String docCode, List children, boolean hasDrawing) {
      super(docCode, children);
        this.hasDrawing = hasDrawing;
    }

    public boolean isHasDrawing() {
        return this.hasDrawing;
    }

    public void setHasDrawing(boolean hasDrawing) {
        this.hasDrawing = hasDrawing;
    }

    public org.helianto.process.Material getMaterial() {
        return this.material;
    }

    public void setMaterial(org.helianto.process.Material material) {
        this.material = material;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
