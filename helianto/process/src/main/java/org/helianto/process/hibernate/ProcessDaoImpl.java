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

package org.helianto.process.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.MaterialType;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Setup;
import org.helianto.process.Unit;
import org.helianto.process.dao.ProcessDao;

public class ProcessDaoImpl extends GenericDaoImpl implements ProcessDao {

    public void persist(Unit unit) {
        merge(unit);
    }

    public void persist(MaterialType materialType) {
        merge(materialType);
    }

    public void persist(Part part) {
        merge(part);
    }

    public void persist(Process process) {
        merge(process);
    }

    public void persist(Operation operation) {
        merge(operation);
    }

    public void persist(Setup setup) {
        merge(setup);
    }

    public Unit loadUnit(int key) {
        return (Unit) load(Unit.class, key);
    }

    public MaterialType loadMaterialType(long key) {
        return (MaterialType) load(MaterialType.class, key);
    }

    public Part loadPart(long key) {
        return (Part) load(Part.class, key);
    }

    public Process loadProcess(long key) {
        return (Process) load(Process.class, key);
    }

    public Operation loadOperation(long key) {
        return (Operation) load(Operation.class, key);
    }

    public Setup loadSetup(long key) {
        return (Setup) load(Setup.class, key);
    }

    public List<Unit> findUnitByEntity(Entity entity) {
        return (ArrayList<Unit>) find(UNIT_QRY, entity);
    }

    public List<MaterialType> findMaterialTypeByEntity(Entity entity) {
        return (ArrayList<MaterialType>) find(MATERIALTYPE_QRY, entity);
    }

    public List<Part> findPartByEntity(Entity entity) {
        return (ArrayList<Part>) find(PART_QRY, entity);
    }

    public List<Process> findProcessByEntity(Entity entity) {
        return (ArrayList<Process>) find(PROCESS_QRY, entity);
    }

    public List<Operation> findOperationByProcess(Entity entity) {
        return (ArrayList<Operation>) find(OPERATION_QRY, entity);
    }

    public List<Setup> findSetupByEntity(Entity entity) {
        return (ArrayList<Setup>) find(SETUP_QRY, entity);
    }

    static final String UNIT_QRY = "from Unit unit " +
            "where unit.entity = ? ";
    static final String MATERIALTYPE_QRY = "from MaterialType materialType " +
            "where materialType.entity = ? ";
    static final String PART_QRY = null;
    static final String PROCESS_QRY = null;
    static final String OPERATION_QRY = null;
    static final String SETUP_QRY = null;
    
    static final String TREE_QRY = 
        "from Tree tree where tree.parent.id = ? " +
        "and tree.child = ?";



}
