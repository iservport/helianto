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

package org.helianto.core.hibernate;

import org.helianto.core.Credential;

public class CredentialDaoImpl extends GenericDaoImpl implements CredentialDao {

    public void persistCredential(Credential credential) {
        merge(credential);
    }

    public void removeCredential(Credential credential) {
        remove(credential);
    }

    public Credential findCredentialByPrincipal(String principal) {
        return (Credential) findUnique(CREDENTIAL_QRY, principal);
    }

    static final String CREDENTIAL_QRY = 
        "from Credential credential " +
        "where credential.principal = ?";

}
