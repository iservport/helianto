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

import java.util.Date;
import java.util.List;

import org.helianto.core.Customer;
import org.helianto.process.Product;
import org.helianto.process.service.ProcessMgrImpl;
import org.helianto.sales.Proposal;
import org.helianto.sales.ProposalState;

/**
 * Default implementation of the 
 * <code>SalesMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class SalesMgrImpl extends ProcessMgrImpl implements SalesMgr {

    public Proposal proposalFactory(Customer customer, Product product, String salesCode) {
        Proposal proposal = new Proposal();
        proposal.setCustomer(customer);
        proposal.setProduct(product);
        proposal.setSalesCode(salesCode);
        proposal.setProposalState(ProposalState.OPEN.getValue());
        proposal.setDateProposed(new Date());
        if (logger.isDebugEnabled()) {
            logger.debug("\n         New instance of Proposal "+proposal);
        }
        return proposal;
    }

    public void persistProposal(Proposal proposal) {
        // TODO Add internal number to proposal and make unique to the entity
        getGenericDao().merge(proposal);
    }

    public Proposal loadProposal(Long key) {
        return (Proposal) getGenericDao().load(Proposal.class, key);
    }

    @SuppressWarnings("unchecked")
    public List<Proposal> findProposalByCustomer(Customer customer) {
        return (List<Proposal>) getGenericDao().find(PROPOSAL_QRY_BY_CUSTOMER, customer.getId());
    }
    
    static final String PROPOSAL_QRY_BY_CUSTOMER = "from Proposal prop " +
            "where prop.customer.id = ?"; 

}
