package org.helianto.process;

import org.helianto.core.Entity;

public interface ProductCreator {
	
	public Part createPartWithDrawing(String drawingNumber, String drawingName);
	
	public Part createPart(String partNumber, String partName);
	
	public Part createPart(Entity entity, String partNumber, String partName);

	public Product createProductWithDrawing(String drawingNumber, String drawingName);
	
	public Product createProduct(String productNumber, String productName);
	
	public Product createProduct(Entity entity, String productNumber, String productName);

}
