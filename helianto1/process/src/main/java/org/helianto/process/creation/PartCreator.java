package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.process.AssociationType;
import org.helianto.process.MaterialType;
import org.helianto.process.Part;
import org.helianto.process.Tree;
import org.springframework.util.Assert;

/**
 * <Code>Part</code> factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartCreator extends AbstractDocumentCreator {
	
    /**
     * Creates a <code>Part</code> with a drawing.
     */
	public static Part partWithDrawingFactory(Entity entity, String drawingNumber, String drawingName) {
        Assert.notNull(entity);
        return partFactory(entity, drawingNumber, drawingName, true);
    }
    
    /**
     * Creates a <code>Part</code>.
     */
	public static Part partFactory(Entity entity, String partNumber, String partName) {
        Assert.notNull(entity);
        return partFactory(entity, partNumber, partName, false);
    }
    
    /**
     * Creates a <code>Part</code>.
     */
    public static Part partFactory(Entity entity, String partNumber, String partName, boolean hasDrawing) {
        Assert.notNull(entity);
        Part part = (Part) documentFactory(Part.class, entity, partNumber);
        part.setHasDrawing(hasDrawing);
        return part;
    }

    /**
     * Creates a <code>MaterialType</code>.
     */
	public static MaterialType materialTypeFactory(Unit materialUnit, String materialName) {
        MaterialType materialType = new MaterialType();
        materialType.setEntity(materialUnit.getEntity());
        materialType.setMaterialUnit(materialUnit);
        materialType.setMaterialName(materialName);
        materialType.setInternalNumber(0);
        return materialType;
    }

	/**
	 * Creates a component.
	 */
    public static Part componentFactory(Part parent, String drawingNumber, String drawingName, double coefficient) {
        Part child = partFactory(parent.getEntity(), drawingNumber, drawingName);
        Tree tree = associationFactory(parent, child, coefficient);
        tree.setAssociationType(AssociationType.PART_PART.getValue());
        return child;
    }
    
}
