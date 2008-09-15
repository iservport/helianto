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

package org.helianto.core.dao;

import org.helianto.core.KeyType;
import org.helianto.core.Operator;

/**
 * <code>KeyType</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface KeyTypeDao extends CommonOrmDao {
     
    /**
     * Persist <code>KeyType</code>.
     */
    public void persistKeyType(KeyType keyType);
    
    /**
     * Merge <code>KeyType</code>.
     */
    public KeyType mergeKeyType(KeyType keyType);
    
    /**
     * Remove <code>KeyType</code>.
     */
    public void removeKeyType(KeyType keyType);
    
    /**
     * Find <code>KeyType</code> by <code>Operator</code> and keyCode.
     */
    public KeyType findKeyTypeByNaturalId(Operator operator, String keyCode);
    
    
    
}
