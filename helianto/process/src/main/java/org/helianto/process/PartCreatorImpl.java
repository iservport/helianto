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

package org.helianto.process;

import org.helianto.core.Entity;

public class PartCreatorImpl  implements PartCreator {

    public Part partWithDrawingFactory(Entity entity, String drawingNumber, String drawingName) {
    	return partFactory(entity, drawingNumber, drawingName, true);
    }

    public Part partFactory(Entity entity, String partNumber, String partName) {
        return partFactory(entity, partNumber, partName, false);
    }

    private Part partFactory(Entity entity, String partNumber, String partName, boolean hasDrawing) {
        Part part = new Part();
        part.setEntity(entity);
        part.setDocCode(partNumber);
        part.setDocName(partName);
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

}
