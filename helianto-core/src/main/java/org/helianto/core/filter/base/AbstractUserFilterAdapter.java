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

package org.helianto.core.filter.base;

import org.helianto.core.Entity;
import org.helianto.core.TrunkEntity;
import org.helianto.core.User;

/**
 * Base class to filters that requires an <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractUserFilterAdapter<T extends TrunkEntity> extends AbstractTrunkFilterAdapter<T>  {
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	/**
	 * User constructor.
	 */
	protected AbstractUserFilterAdapter(T filter, User user) {
		super(filter);
		this.user = user;
	}
	
	/**
	 * The entity.
	 */
	@Override
	public Entity getEntity() {
		return getUser().getEntity();
	}
    
    /**
     * User providing the current entity.
     */
    public User getUser() {
        return user;
    }
    
}
