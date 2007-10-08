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

package org.helianto.web.view;

import java.io.Serializable;

import org.helianto.core.Credential;

/**
 * <code>Credential</code> form.
 *  
 * @author Mauricio Fernandes de Castro
 */
public class CredentialForm implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public static final char SEND_CURRENT_PASSWORD = 'C';
    public static final char SEND_NEW_PASSWORD = 'N';
    public static final char VERIFY_PASSWORD_ONLINE = 'V';

    private Credential credential;
    private String currentPassword = "";
    private String newPassword = "";
    private String verifyPassword = "";
    private char sendOption = SEND_NEW_PASSWORD;
    
    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

    public char getSendOption() {
        return sendOption;
    }

    public void setSendOption(char sendOption) {
        this.sendOption = sendOption;
    }

    /**
     * toString
     * 
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(
                Integer.toHexString(hashCode())).append(" [");
        buffer.append("credential").append("='").append(getCredential()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

}
