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

package org.helianto.process.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.service.AbstractGenericService;
import org.helianto.core.service.PartnerMgrImpl;
import org.helianto.process.Document;
import org.helianto.process.MaterialType;
import org.helianto.process.Operation;
import org.helianto.process.creation.OperationType;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.Tree;
import org.helianto.process.Unit;
import org.helianto.process.creation.AssociationType;
import org.helianto.process.creation.ResourceType;
import org.helianto.process.dao.ProcessDao;

public class ProcessMgrImpl extends PartnerMgrImpl  implements ProcessMgr {

    public static final Log logger = LogFactory.getLog(ProcessMgrImpl.class);

    public Resource resourceGroupFactory(Entity entity) {
        Resource resource = new Resource();
        resource.setEntity(entity);
//        resource.setResourceType(ResourceType.GROUP.getValue());
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of Resource (group)"+resource);
        }
        return resource;
    }
    
    public Resource resourceFactory(Resource parent) {
        Resource resource = new Resource();
        resource.setEntity(parent.getEntity());
        resource.setResourceType(ResourceType.EQUIPMENT.getValue());
        resource.setParent(parent);
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of Resource "+resource);
        }
        return resource;
    }
    
    public Unit unitFactory(Entity entity, String unitCode, String unitName) {
        Unit unit = new Unit();
        unit.setEntity(entity);
        unit.setUnitCode(unitCode);
        unit.setUnitName(unitName);
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of Unit "+unit);
        }
        return unit;
    }

    public Unit unitFactory(Unit parent, String unitCode, String unitName) {
        Unit unit = unitFactory(parent.getEntity(), unitCode, unitName);
        unit.setParent(parent);
        return unit;
    }

    public MaterialType materialFactory(Unit unit, String materialName) {
        MaterialType MaterialType = new MaterialType();
        MaterialType.setEntity(unit.getEntity());
        MaterialType.setMaterialUnit(unit);
        MaterialType.setMaterialName(materialName);
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of MaterialType "+MaterialType);
        }
        return MaterialType;
    }

    public MaterialType materialFactory(MaterialType parent, Unit unit, String materialName) {
        MaterialType MaterialType = materialFactory(unit, materialName);
        MaterialType.setParent(parent);
        return MaterialType;
    }

    private void initDocument(Document doc, Entity entity, String code) {
        doc.setEntity(entity);
        doc.setDocCode(code);
        doc.setChildren(new ArrayList());
    }
    
//    public Product productFactory(Unit unit, MaterialType MaterialType, String productCode, Process process) {
//        if (process==null) {
//            throw new IllegalArgumentException("At least one primary process is required to build a product");
//        }
//        Product product =  new Product();
//        initDocument((Document) product, unit.getEntity(), productCode);
//        product.setUnit(unit);
//        product.setMaterial(MaterialType);
//        product.setHasDrawing(false);
//        product.setProductType(ProductType.DOMESTIC.getValue());
//        associate((Document) product, (Document) process, 0d, AssociationType.PRIMARY_PRODUCT_TO_PROCESS.getValue(), 0);
//        if (logger.isDebugEnabled()) {
//            logger.debug("\n         New instance of Product "+product);
//        }
//        return product;
//    }
//    
    public Part partFactory(Entity entity, String partCode, boolean hasDrawing) {
        Part part = new Part();
        initDocument((Document) part, entity, partCode);
        part.setHasDrawing(hasDrawing);
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of Part "+part);
        }
        return part;
    }
    
    public Part partFactory(Part parent, String partCode, boolean hasDrawing, double coefficient) {
        Part child = partFactory(parent.getEntity(), partCode, hasDrawing);
        associateParts(parent, child, coefficient, 1);
        return child;
    }
    
    @SuppressWarnings("unchecked")
    private void associate(Document parent, Document child, double coefficient, int associationType, int sequence) {
        Tree tree = new Tree();
        tree.setParent(parent);
        tree.setChild(child);
        tree.setAssociationType(associationType);
        tree.setSequence(sequence);
        tree.setCoefficient(coefficient);
        parent.getChildren().add(tree);
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Association created with Tree instance "+tree);
        }
    }
    
    public void associateParts(Part parent, Part child, double coefficient, int sequence) {
        associate((Document) parent, (Document) child, coefficient, AssociationType.PART_PART.getValue(), sequence);
    }
    
    public Process processFactory(Entity entity, String processCode) {
        Process process = new Process();
        initDocument((Document) process, entity, processCode);
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of Process "+process);
        }
        return process;
    }

    public Operation operationFactory(Process process, int sequence, String operationCode, long execTime) {
        Operation operation = new Operation();
        initDocument((Document) operation, process.getEntity(), operationCode);
        operation.setOperationTime(execTime);
        operation.setOperationType(OperationType.OPERATION.getValue());
        associate((Document) process, (Document) operation, 0d, AssociationType.PROCESS_OPERATION.getValue(), sequence);
        operation.setSetups(new ArrayList());
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of Operation "+operation);
        }
        return operation;
    }

    @SuppressWarnings("unchecked")
    public Setup setupFactory(Operation operation, Resource resource, int priority, long setupTime, long transportTime) {
        Setup setup = new Setup();
        setup.setOperation(operation);
        setup.setResource(resource);
        setup.setPriority(priority);
        setup.setSetupTime(setupTime);
        setup.setTransportTime(transportTime);
        operation.getSetups().add(setup);
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of Setup "+setup);
        }
        return setup;
    }
    
    public void persistResource(Resource resource) {
//        getGenericDao().merge(resource);
    }
    
    public void persistUnit(Unit unit) {
//        getGenericDao().merge(unit);
    }

    public void persistMaterial(MaterialType MaterialType) {
//        getGenericDao().merge(MaterialType);
    }

    public void persistPart(Part part) {
//        getGenericDao().merge(part);
    }

    public void persistProcess(Process process) {
        if (process.getInternalNumber()==0) {
            //FIXME internal number should return long ...
//            process.setInternalNumber(new Long(currentNumber(process.getEntity(), "PROC")));
        }
//        getGenericDao().merge(process);
    }

    public void persistOperation(Operation operation) {
//        getGenericDao().merge(operation);
    }

    public void persistSetup(Setup setup) {
//        getGenericDao().merge(setup);
    }

    public Resource loadResource(Integer key) {
        return (Resource) processDao.load(Resource.class, key);
    }
    
    public Unit loadUnit(Integer key) {
        return (Unit) processDao.load(Unit.class, key);
    }

    public MaterialType loadMaterial(Long key) {
        return (MaterialType) processDao.load(MaterialType.class, key);
    }

    public Part loadPart(Long key) {
        return (Part) processDao.load(Part.class, key);
    }

    public Process loadProcess(Long key) {
        return (Process) processDao.load(Process.class, key);
    }

    public Operation loadOperation(Long key) {
        return (Operation) processDao.load(Operation.class, key);
    }

    public Setup loadSetup(Long key) {
        return (Setup) processDao.load(Setup.class, key);
    }

    public Collection findResources(String entityAlias) {
//        try {
//            Entity entity = findEntityByAlias(entityAlias);
//            if (logger.isDebugEnabled()) {
//                logger.debug("\n         Entity with alias "+entityAlias+" found");
//            }
//            return findResources(entity);
//        } catch (Exception e) {
//            if (logger.isDebugEnabled()) {
//                logger.debug("\n         Entity with alias "+entityAlias+" not found:"+e.toString());
//            }
//        }
        throw new InvalidParameterException("Entity alias "+entityAlias+" not found");
    }

    public Collection findResources(Entity entity) {
        return processDao.find(RESOURCE_QRY, entity.getId());
    }

    public Collection findResourceGroups(Entity entity) {
        return processDao.find(RESOURCE_GROUP_QRY, entity.getId());
    }

    public Collection findResourcesByGroup(Resource resource) {
        return processDao.find(RESOURCE_QRY_BY_GROUP, resource.getId());
    }
    
//    public 
    
    private ProcessDao processDao;

    static final String RESOURCE_QRY = 
        "from Resource res where res.entity.id = ?";
    
    static final String RESOURCE_GROUP_QRY = 
        "from Resource res where res.entity.id = ? and res.parent=null";
    
    static final String RESOURCE_QRY_BY_GROUP = 
        "from Resource res where res.parent.id = ?";

    static final String TREE_QRY = 
        "from Tree tree where tree.parent.id = ? " +
        "and tree.child = ?";

}
