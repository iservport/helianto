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

package org.helianto.core.creation;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.PersonalData;
import org.helianto.core.User;
import org.helianto.core.UserGroup;

/**
 * A factory method pattern interface to <code>User</code>
 * related domain objects.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public interface UserCreator {

    public PersonalData personalDataFactory();

    public Identity identityFactory(String principal, String optionalAlias);

    public Credential credentialFactory(Identity identity);

    public User userFactory(Entity entity, Identity identity) throws NullEntityException;

    public UserGroup userGroupFactory(Entity entity, Identity identity) throws NullEntityException;

    public User userFactory(UserGroup parent, Identity identity);

    public String generatePassword(int size);

}