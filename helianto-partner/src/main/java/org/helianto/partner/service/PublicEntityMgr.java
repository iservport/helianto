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

package org.helianto.partner.service;

import java.util.List;
import java.util.Map;

import org.helianto.partner.PublicAddress;
import org.helianto.partner.PublicAddressFilter;
import org.helianto.partner.PublicEntity;
import org.helianto.partner.PublicEntityFilter;
import org.helianto.partner.PublicEntityKey;

/**
 * Public entity service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface PublicEntityMgr {

	/**
     * Find <code>PublicAddress</code> list.
     * 
     * @param filter
     */
	public List<PublicAddress> findPublicAddress(PublicAddressFilter filter);
	
    /**
     * Write <code>PublicAddress</code> to the datastore.
     * 
     * @param publicAddress
     */
	public PublicAddress storePublicAddress(PublicAddress publicAddress);
	
    /**
     * Remove <code>PublicAddress</code> from the datastore.
     * 
     * @param publicAddress
     */
	public void removePublicAddress(PublicAddress publicAddress);
	
	/**
     * Find <code>PublicEntity</code> list.
     * 
     * @param publicEntityFilter
     */
	public List<? extends PublicEntity> findPublicEntities(PublicEntityFilter publicEntityFilter);
	
    /**
     * Write <code>PublicEntity</code> to the datastore.
     * 
     * <p>
     * Should be able to detect if the associated <code>Entity</code> is
     * already installed and trigger installation accordingly.
     * </p>
     * 
     * @param publicEntity
     */
    public PublicEntity storePublicEntity(PublicEntity publicEntity);

    /**
     * Remove <code>PublicEntity</code> from the datastore.
     * 
     * @param publicEntity
     */
    public void removePublicEntity(PublicEntity publicEntity);

    /**
     * Load <code>PublicEntityKey</code> map keyed with the KeyCode.
     * 
     * @param publicEntity
     */
	public Map<String, PublicEntityKey> loadPublicEntityKeyMap(PublicEntity publicEntity);
	
    /**
     * Write <code>PublicEntityKey</code> to the datastore.
     * 
     * @param publicEntityKey
     */
    public PublicEntityKey storePublicEntityKey(PublicEntityKey publicEntityKey);

    /**
     * Remove <code>PublicEntityKey</code> from the datastore.
     * 
     * @param publicEntityKey
     */
    public void removePublicEntityKey(PublicEntityKey publicEntityKey);

}
