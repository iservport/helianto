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

package org.helianto.process.dao;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Setup;

public interface ProcessDao extends MaterialDao {
    
    public void persist(Part part);

    public void persist(Process process);

    public void persist(Operation operation);

    public void persist(Setup setup);
    
    //

    public List<Part> findPartByEntity(Entity entity);

    public List<Process> findProcessByEntity(Entity entity);
    
    public List<Operation> findOperationByProcess(Entity entity);

    public List<Setup> findSetupByEntity(Entity entity);

}
