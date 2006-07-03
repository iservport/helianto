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

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Contact;
import org.helianto.core.Credential;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Partner;
import org.helianto.core.Supplier;

/**
 * A factory method pattern interface to <code>Partner</code>
 * related domain objects.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: $
 */
public interface PartnerCreator  extends EntityCreator {

    /**
     * The <code>Customer</code> factory method.
     */
    public Customer customerFactory(Entity entity, String alias) throws NullEntityException;
    
    /**
     * The <code>Supplier</code> factory method.
     */
    public Supplier supplierFactory(Entity entity, String alias) throws NullEntityException;
    
    /**
     * The <code>Division</code> factory method.
     */
    public Division divisionFactory(Entity entity, String alias) throws NullEntityException;
    
    /**
     * The <code>Bank</code> factory method.
     */
    public Bank bankFactory(Entity entity, String alias) throws NullEntityException;
    
    /**
     * The <code>Agent</code> factory method.
     */
    public Agent agentFactory(Entity entity, String alias) throws NullEntityException;
    
    /**
     * The <code>Manufacturer</code> factory method.
     */
    public Manufacturer manufacturerFactory(Entity entity, String alias) throws NullEntityException;
    
    /**
     * The <code>Contact</code> factory method.
     */
    public Contact contactFactory(Partner partner, Identity identity) throws NullEntityException;
    
}
