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

package org.helianto.sales.service;

import java.util.List;

import org.helianto.core.Customer;
import org.helianto.process.Product;
import org.helianto.process.service.ProcessMgr;
import org.helianto.sales.Proposal;

/**
 * Base sales service interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id: SalesMgr.java 3 2006-03-02 19:44:41 -0300 (Qui, 02 Mar 2006) iserv $
 */
public interface SalesMgr extends ProcessMgr {
    
    /**
     * Proposal factory method.
     */
    public Proposal proposalFactory(Customer customer, Product product, String salesCode);
    
    /**
     * Persist proposal method.
     */
    public void persistProposal(Proposal proposal);
    
    /**
     * Proposal load method.
     */
    public Proposal loadProposal(Long key);
    
    /**
     * Find Proposal by Customer.
     */
    public List<Proposal> findProposalByCustomer(Customer customer);

}
