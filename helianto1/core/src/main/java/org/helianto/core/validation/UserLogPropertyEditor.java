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
package org.helianto.core.validation;

import org.helianto.core.validation.AbstractLoaderPropertyEditor;
import org.helianto.core.validation.PropertyLoader;

import org.helianto.core.UserLog;

/**
 * Default <code>PropertyLoader</code> backed <code>UserLog</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UserLogPropertyEditor extends AbstractLoaderPropertyEditor {
    
    public UserLogPropertyEditor(PropertyLoader propertyLoader) {
        super(propertyLoader);
    }
    
    @Override
    public String getAsText() {
        UserLog userLog = (UserLog) getValue();
        return String.valueOf(userLog.getLastEvent());
    }
    
    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        setAsText(id, UserLog.class);
    }

}


   
/* registry snippet

        PropertyEditor UserLogPropertyEditor = new UserLogPropertyEditor(getPropertyLoader());
    if (logger.isDebugEnabled()) {
        logger.debug("Registering custom editor "+UserLogPropertyEditor);
    }
    registry.registerCustomEditor(UserLog.class, UserLogPropertyEditor);
*/

