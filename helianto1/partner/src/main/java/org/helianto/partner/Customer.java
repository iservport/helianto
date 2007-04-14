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

package org.helianto.partner;

import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * <p>
 * Represents a relationship to a Customer. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="prtnr_customer")
public class Customer extends Partner implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public Customer() {
    }

    /**
     * <code>Customer</code> factory.
     * 
     * @param partnerAssociation
     * @param sequence
     */
    public static Customer customerFactory(PartnerAssociation partnerAssociation, int sequence) {
        Customer customer = new Customer();
        customer.setPartnerAssociation(partnerAssociation);
        customer.setSequence(sequence);
        partnerAssociation.getPartners().add(customer);
        return customer;
    }

    /**
     * <code>Customer</code> natural id query.
     */
    @Transient
    public static String getCustomerNaturalIdQueryString() {
        return "select customer from Customer customer where customer.partnerAssociation = ? and customer.sequence = ? ";
    }
}


