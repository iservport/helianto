package org.helianto.process;

import org.helianto.core.Entity;

public interface PartCreator {
	
    public Part createPartWithDrawing(String drawingNumber, String drawingName);
    
    public Part createPartWithDrawing(Entity entity, String drawingNumber, String drawingName);
    
	public Part createPart(String partNumber, String partName);
	
	public Part createPart(Entity entity, String partNumber, String partName);

}
