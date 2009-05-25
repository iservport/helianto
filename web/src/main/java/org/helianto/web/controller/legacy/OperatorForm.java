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

package org.helianto.web.controller.legacy;

import java.io.Serializable;

import org.helianto.core.Entity;
import org.helianto.core.Operator;

/**
 * A form backing POJO to the operation flow.
 *  
 * @author Mauricio Fernandes de Castro
 */
public class OperatorForm  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Operator operator;
    
    private Entity entity;

    public Entity getEntity() {
        return entity;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

}
