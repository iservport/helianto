/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.process.Document;
import org.helianto.process.MaterialType;
import org.helianto.process.Part;
import org.helianto.process.Tree;
import org.helianto.process.Unit;
import org.helianto.process.type.AssociationType;
import org.springframework.util.Assert;

/**
 * Default implementation of <code>PartCreator</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class PartCreatorImpl extends AbstractDocumentCreator implements PartCreator {

    public Part partWithDrawingFactory(Entity entity, String drawingNumber, String drawingName) {
        Assert.notNull(entity);
    	return partFactory(entity, drawingNumber, drawingName, true);
    }

    public Part partFactory(Entity entity, String partNumber, String partName) {
        Assert.notNull(entity);
        return partFactory(entity, partNumber, partName, false);
    }

    /*
     * internal part factory.
     */
    private Part partFactory(Entity entity, String partNumber, String partName, boolean hasDrawing) {
        Assert.notNull(entity);
        Part part = (Part) documentFactory(Part.class, entity, partNumber);
        part.setHasDrawing(hasDrawing);
        return part;
    }

	public MaterialType materialTypeFactory(Unit materialUnit, String materialName) {
		MaterialType materialType = new MaterialType();
		materialType.setEntity(materialUnit.getEntity());
		materialType.setMaterialUnit(materialUnit);
		materialType.setMaterialName(materialName);
		materialType.setInternalNumber(0);
		return materialType;
	}

	public Part materialFactory(MaterialType materialType, String materialNumber, String materialName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Part componentFactory(Part parent, String drawingNumber, String drawingName, double coefficient) {
		Part child = partFactory(parent.getEntity(), drawingNumber, drawingName);
		Tree tree = associationFactory(parent, child, coefficient);
		tree.setAssociationType(AssociationType.PART_PART.getValue());
		return child;
	}
	
	/*
	 * Internal tree factory
	 */
	private Tree associationFactory(Document parent, Document child, double coefficient) {
        Assert.notNull(parent);
        Assert.notNull(child);
		Tree tree = new Tree();
		tree.setParent(parent);
		tree.setChild(child);
		parent.getChildren().add(tree);
		return tree;
	}

}
