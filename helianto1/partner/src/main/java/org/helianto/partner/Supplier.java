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

import org.helianto.partner.Partner;
import org.helianto.partner.PartnerRegistry;

/**
 * <p>
 * Represents a relationship to a Supplier. 
 * </p>
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_supplier")
public class Supplier extends Partner implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public Supplier() {
    }

    /**
     * <code>Supplier</code> factory.
     * 
     * @param partnerRegistry
     * @param sequence
     */
    public static Supplier supplierFactory(PartnerRegistry partnerRegistry, int sequence) {
        Supplier supplier = new Supplier();
        supplier.setPartnerRegistry(partnerRegistry);
        supplier.setSequence(sequence);
        partnerRegistry.getPartners().add(supplier);
        return supplier;
    }

    /**
     * <code>Supplier</code> natural id query.
     */
    @Transient
    public static String getSupplierNaturalIdQueryString() {
        return "select supplier from Supplier supplier where supplier.partnerRegistry = ? and supplier.sequence = ? ";
    }

}
