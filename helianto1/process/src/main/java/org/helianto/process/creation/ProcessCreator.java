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

import java.util.ArrayList;

import org.helianto.core.Entity;
import org.helianto.process.Characteristic;
import org.helianto.process.Document;
import org.helianto.process.DocumentDetail;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.Tree;
import org.helianto.process.type.AssociationType;
import org.helianto.process.type.OperationType;

public class ProcessCreator extends AbstractDocumentCreator {

    public static Part partFactory(Entity entity, String partCode, boolean hasDrawing) {
        Part part = (Part) documentFactory(Part.class, entity, partCode);
        part.setHasDrawing(hasDrawing);
        return part;
    }
    
    public static Part partFactory(Part parent, String partCode, boolean hasDrawing, double coefficient) {
        Part child = partFactory(parent.getEntity(), partCode, hasDrawing);
        associateParts(parent, child, coefficient, 1);
        return child;
    }
    
    private static void associate(Document parent, Document child, double coefficient, int associationType, int sequence) {
        Tree tree = new Tree();
        tree.setParent(parent);
        tree.setChild(child);
        tree.setAssociationType(associationType);
        tree.setSequence(sequence);
        tree.setCoefficient(coefficient);
//        parent.getChildren().add(tree);
    }
    
    public static void associateParts(Part parent, Part child, double coefficient, int sequence) {
        associate((Document) parent, (Document) child, coefficient, AssociationType.PART_PART.getValue(), sequence);
    }
    
    public static Process processFactory(Entity entity, String processCode) {
        Process process = (Process) documentFactory(Process.class, entity, processCode);
        return process;
    }

    public static Operation operationFactory(Process process, int sequence, String operationCode, long execTime) {
        Operation operation = (Operation) documentFactory(Operation.class, process.getEntity(), operationCode);
        operation.setOperationTime(execTime);
        operation.setOperationType(OperationType.OPERATION.getValue());
        associate((Document) process, (Document) operation, 0d, AssociationType.PROCESS_OPERATION.getValue(), sequence);
        operation.setSetups(new ArrayList<Setup>());
        return operation;
    }

//    public static Setup setupFactory(Operation operation, Resource resource, int priority, long setupTime, long transportTime) {
//        Setup setup = new Setup();
//        setup.setOperation(operation);
//        setup.setResource(resource);
//        setup.setPriority(priority);
//        setup.setSetupTime(setupTime);
//        setup.setTransportTime(transportTime);
//        operation.getSetups().add(setup);
//        return setup;
//    }
    
//  public Product productFactory(Unit unit, MaterialType MaterialType, String productCode, Process process) {
//  if (process==null) {
//      throw new IllegalArgumentException("At least one primary process is required to build a product");
//  }
//  Product product =  new Product();
//  initDocument((Document) product, unit.getEntity(), productCode);
//  product.setUnit(unit);
//  product.setMaterial(MaterialType);
//  product.setHasDrawing(false);
//  product.setProductType(ProductType.DOMESTIC.getValue());
//  associate((Document) product, (Document) process, 0d, AssociationType.PRIMARY_PRODUCT_TO_PROCESS.getValue(), 0);
//  if (logger.isDebugEnabled()) {
//      logger.debug("\n         New instance of Product "+product);
//  }
//  return product;
//}
//

    public Process processFactory(Part part, String processCode) {
        // TODO Auto-generated method stub
        return null;
    }

    public Process processFactory(Part part, String processCode,
            String processName) {
        // TODO Auto-generated method stub
        return null;
    }

    public Operation operationFactory(Process process, String operationCode) {
        // TODO Auto-generated method stub
        return null;
    }

    public Operation operationFactory(Process process, String operationCode,
            String operationName) {
        // TODO Auto-generated method stub
        return null;
    }

    public DocumentDetail operationDetailFactory(Operation operation) {
        // TODO Auto-generated method stub
        return null;
    }

    public Characteristic characteristicFactory(Operation operation) {
        // TODO Auto-generated method stub
        return null;
    }

}
