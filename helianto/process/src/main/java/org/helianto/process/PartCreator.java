package org.helianto.process;

import org.helianto.core.Entity;

public interface PartCreator {
	
    public Part partWithDrawingFactory(String drawingNumber, String drawingName);
    
    public Part partWithDrawingFactory(Entity entity, String drawingNumber, String drawingName);
    
	public Part partFactory(String partNumber, String partName);
	
	public Part partFactory(Entity entity, String partNumber, String partName);

}
