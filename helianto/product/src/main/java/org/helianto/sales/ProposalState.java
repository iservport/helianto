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

package org.helianto.sales;

/**
 * An enumeration to supply char types for 
 * {@link org.helianto.sales.Proposal#proposalState}.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: ProposalState.java 3 2006-03-02 19:44:41 -0300 (Qui, 02 Mar 2006) iserv $
 */
public enum ProposalState {
    
    OPEN('O'),
    APPROVED('A'),
    REJECTED('R');
    
    private char value;
    
    private ProposalState(char value) {
        this.value = value;
    }
    public char getValue() {
        return this.value;
    }

}
