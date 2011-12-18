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

package org.helianto.core.def;

/**
 * Phone types.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum PhoneType {
	
    PERSONAL('P', true),
    MOBILE('M', true),
    HOME('H', true),
    WORK('W', true),
    BRANCH('B', true),
    FAX('F', false),
    MAIN('1', true);
    
    private char value;
    private boolean voice;

    private PhoneType(char value, boolean voice) {
        this.value= value;
        this.voice= voice;
    }

    public char getValue() {
        return value;
    }
    
    public boolean isVoice() {
        return voice;
    }
    

}
