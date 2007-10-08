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

import java.util.Date;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.security.PublicUserDetails;

/**
 * Default security service layer interface for the core package.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface SecurityMgr extends UserMgr {
	
    /**
     * Find <core>Credential</core> by <core>Identity</core>.
     */
	public Credential findCredentialByIdentity(Identity identity);
	
    /**
     * Find <core>Credential</core> by principal.
     */
	public Credential findCredentialByPrincipal(String princpal);
	
    /**
     * Find the last <core>UserLog</core> by <core>User</core> list.
     */
	public UserLog findLastUserLog(List<User> users);
	
    /**
     * Write a new <code>UserLog<code> and update the <code>Identity</code>
     * last log date.
     * 
     * @param user
     * @param date
     */
	public void writeUserLog(User user, Date date);
	
    /**
     * Auto-create mode enables a new <code>User</code> creation for the 
     * default <code>Entity</code> if necessary.
     */
	public boolean isAutoCreateEnabled();
	
    /**
     * A hook to allow for automatic <code>User</code> creation.
     * 
     * @param identity
     */
	public User autoCreateUser(Identity identity);
    
    /**
     * <p>Utility method to find <code>PublicUserDetails</code> stored in a security context.</p>
     */
    public PublicUserDetails findSecureUser();

}
