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

package org.helianto.core;

import java.util.Locale;

/**
 * Abstract class to provide <code>Home</code> subclass with 
 * a read only <code>Locale</code> non persistent field.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractHome {
    
    public Locale getLocale() {
        Locale locale = null;
        try {
            locale = new Locale(getLanguage(), getCountry());
        } catch (Exception e) {
            locale = Locale.getDefault();
        }
        return locale;
    }
    
    public abstract String getLanguage();
    public abstract String getCountry();

}
