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

import org.helianto.core.Unit;
import org.helianto.process.MaterialType;
import org.helianto.process.dao.MaterialDao;

public class MaterialMgrImpl implements MaterialMgr {
    
    private MaterialDao materialDao;

    public MaterialType createMaterialType(Unit unit) {
        // TODO Auto-generated method stub
        return null;
    }

    //

    public void setMaterialDao(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

}
