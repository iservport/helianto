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

import java.util.Locale;

import org.helianto.core.ActivityState;
import org.helianto.core.Credential;
import org.helianto.core.Encription;
import org.helianto.core.Identity;
import org.helianto.core.OperationMode;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Server;
import org.helianto.core.ServerType;

/**
 * Operator required classes factory methods.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OperatorCreator extends CreatorSupport {
    
    /**
     * Default <code>Operator</code> creator.
     *  
     * @param operatorName
     * @param operationMode if null, default is OperationMode.LOCAL
     * @param locale if null, default is Locale.getDefault()
     * 
     * @see OperationMode
     */
    public static Operator operatorFactory(String operatorName, OperationMode operationMode, Locale locale) {
        Operator operator = new Operator();
        
        operator.setOperatorName(operatorName);
        if (operationMode==null) {
            operator.setOperationMode(OperationMode.LOCAL.getValue());
        } else {
            operator.setOperationMode(operationMode.getValue());
        }
        if (locale==null) {
            operator.setLocale(Locale.getDefault());
        } else {
            operator.setLocale(locale);
        }
        operator.setOperatorSourceMailAddress("operator@helianto.org");
        operator.setDefaultEncoding("ISO-8859-1");
        operator.setOperatorHostAddress("http://www.helianto.org");
        if (logger.isDebugEnabled()) {
            logger.debug("Created: "+operator);
        }
        return operator;
    }

}
