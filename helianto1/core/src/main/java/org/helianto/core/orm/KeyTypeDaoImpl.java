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

package org.helianto.core.orm;

import org.helianto.core.KeyType;
import org.helianto.core.dao.KeyTypeDao;
import org.helianto.core.hibernate.GenericDaoImpl;



import org.helianto.core.Operator;
/**
 * Default implementation of <code>KeyType</code> data access interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class KeyTypeDaoImpl extends GenericDaoImpl implements KeyTypeDao {
     
    public void persistKeyType(KeyType keyType) {
        if (logger.isDebugEnabled()) {
            logger.debug("Persisting "+keyType);
        }
        persist(keyType);
    }
    
    public KeyType mergeKeyType(KeyType keyType) {
        if (logger.isDebugEnabled()) {
            logger.debug("Merging "+keyType);
        }
        return (KeyType) merge(keyType);
    }
    
    public void removeKeyType(KeyType keyType) {
        if (logger.isDebugEnabled()) {
            logger.debug("Removing "+keyType);
        }
        remove(keyType);
    }
    
    public KeyType findKeyTypeByNaturalId(Operator operator, String keyCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("Finding unique keyType with operator='"+operator+"' and keyCode='"+keyCode+"' ");
        }
        return (KeyType) findUnique(KeyType.getKeyTypeNaturalIdQueryString(), operator, keyCode);
    }
    
    
	static String KEYTYPE_ENTITY_QRY = "select keyType from KeyType keyType "+
	    "where keyType.entity = ? ";
    
}
