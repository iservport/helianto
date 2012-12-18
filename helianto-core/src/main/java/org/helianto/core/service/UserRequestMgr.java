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

package org.helianto.core.service;

import java.util.List;

import org.helianto.user.domain.UserRequest;
import org.helianto.user.form.UserRequestForm;

/**
 * User request service interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface UserRequestMgr {
	
	/**
	 * Find a <code>UserRequest</code> list.
	 * 
	 * @param form
	 */
	public List<UserRequest> findUserRequests(UserRequestForm form);
	
	/**
	 * Store <code>UserRequest</code>.
	 * 
	 * @param userRequest
	 */
	public UserRequest storeUserRequest(UserRequest userRequest);
	
}
