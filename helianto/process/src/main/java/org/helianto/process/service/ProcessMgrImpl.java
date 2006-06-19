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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.service.PartnerMgrImpl;
import org.helianto.process.MaterialType;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.Unit;
import org.helianto.process.creation.ProcessCreator;
import org.helianto.process.dao.ProcessDao;

public class ProcessMgrImpl extends PartnerMgrImpl  implements ProcessMgr {

    public Unit createUnit(Entity entity) {
        // TODO Auto-generated method stub
        return null;
    }

    public MaterialType createMaterialType(Unit unit) {
        // TODO Auto-generated method stub
        return null;
    }

    public Part createPart(Entity entity, boolean hasDrawing) {
        // TODO Auto-generated method stub
        return null;
    }

    public void associateParts(Part parent, Part child, double coefficient, int sequence) {
        // TODO Auto-generated method stub
        
    }

    public Process createProcess(Entity entity) {
        // TODO Auto-generated method stub
        return null;
    }

    public Operation createOperation(Process process) {
        // TODO Auto-generated method stub
        return null;
    }

    public Setup createSetupFactory(Operation operation, Resource resource) {
        // TODO Auto-generated method stub
        return null;
    }

    public void persistUnit(Unit unit) {
        processDao.persist(unit);
    }

    public void persistMaterial(MaterialType MaterialType) {
        processDao.persist(MaterialType);
    }

    public void persistPart(Part part) {
        processDao.persist(part);
    }

    public void persistProcess(Process process) {
        if (process.getInternalNumber()==0) {
            //FIXME internal number should return long ...
//            process.setInternalNumber(new Long(currentNumber(process.getEntity(), "PROC")));
        }
        processDao.persist(process);
    }

    public void persistOperation(Operation operation) {
        processDao.persist(operation);
    }

    public void persistSetup(Setup setup) {
        processDao.persist(setup);
    }

    public Unit loadUnit(Serializable key) {
        if (key instanceof String) {
            return processDao.loadUnit(Integer.parseInt((String) key)); 
        }
        return processDao.loadUnit(new Integer(key.toString()).intValue());
    }

    //FIXME
    public MaterialType loadMaterial(Serializable key) {
        return processDao.loadMaterialType(0);
    }

    //FIXME
    public Part loadPart(Serializable key) {
        return  processDao.loadPart(0);
    }

    //FIXME
    public Process loadProcess(Serializable key) {
        return processDao.loadProcess(0);
    }

    //FIXME
    public Operation loadOperation(Serializable key) {
        return processDao.loadOperation(0);
    }

    //FIXME
    public Setup loadSetup(Serializable key) {
        return processDao.loadSetup(0);
    }

    public List<Unit> findUnitByEntity(Entity entity) {
        return processDao.findUnitByEntity(entity);
    }

//    public 
    
    public static final Log logger = LogFactory.getLog(ProcessMgrImpl.class);

    private ProcessDao processDao;

    private ProcessCreator processCreator;

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setProcessCreator(ProcessCreator processCreator) {
        this.processCreator = processCreator;
    }

}
