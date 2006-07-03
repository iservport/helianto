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

package org.helianto.core.legacy;

import org.helianto.core.Credential;

/**
 * An enumeration to supply char types for 
 * {@link Credential#credentialType}.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: CredentialType.java,v 1.1 2006/03/02 22:44:27 iserv Exp $
 */
public enum CredentialType {
    
    NOT_ADDRESSABLE('N'),
    ORGANIZATIONAL_EMAIL('O'),
    PERSONAL_EMAIL('P');
    
    private char value;
    
    private CredentialType(char value) {
        this.value = value;
    }
    public char getValue() {
        return this.value;
    }

}
