package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.core.creation.NullEntityException;
import org.helianto.process.MaterialType;
import org.helianto.process.Part;
import org.helianto.process.Unit;

public interface PartCreator {
	
    /**
     * Create a <code>Part</code> with a drawing.
     */
	public Part partWithDrawingFactory(Entity entity, String drawingNumber, String drawingName) throws NullEntityException;
    
    /**
     * Create a <code>Part</code>.
     */
	public Part partFactory(Entity entity, String partNumber, String partName) throws NullEntityException;

    /**
     * Create a <code>MaterialType</code>.
     */
	public MaterialType materialTypeFactory(Unit materialUnit, String materialName);

    /**
     * Create a <code>Material</code>.
     */
	public Part materialFactory(MaterialType materialType, String materialNumber, String materialName);
	
	/**
	 * Create a component.
	 */
	public Part componentFactory(Part parent, String drawingNumber, String drawingName, double coefficient);

}
