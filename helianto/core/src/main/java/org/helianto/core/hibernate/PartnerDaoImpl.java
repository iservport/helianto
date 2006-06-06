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

package org.helianto.core.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Agent;
import org.helianto.core.Bank;
import org.helianto.core.Customer;
import org.helianto.core.Division;
import org.helianto.core.Entity;
import org.helianto.core.Manufacturer;
import org.helianto.core.Supplier;
import org.helianto.core.dao.PartnerDao;

/**
 * Default implementation for <code>PartnerDao</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PartnerDaoImpl extends GenericDaoImpl implements PartnerDao {

    public void persistCustomer(Customer customer) {
        merge(customer);
    }

    public void persistSupplier(Supplier supplier) {
        merge(supplier);
    }

    public void persistDivision(Division division) {
        merge(division);
    }

    public void persistAgent(Agent agent) {
        merge(agent);
    }

    public void persistBank(Bank bank) {
        merge(bank);
    }

    public void persistManufacturer(Manufacturer manufacturer) {
        merge(manufacturer);
    }

    public List<Customer> findCustomerByEntity(Entity entity) {
        return (ArrayList<Customer>) find(CUSTOMER_QRY, entity);
    }

    public List<Supplier> findSupplierByEntity(Entity entity) {
        return (ArrayList<Supplier>) find(SUPPLIER_QRY, entity);
    }

    public List<Division> findDivisionByEntity(Entity entity) {
        return (ArrayList<Division>) find(DIVISION_QRY, entity);
    }

	public Division findCurrentDivision(Entity entity) {
		// TODO see CURRENTDIVISION_QRY
		List<Division> divisionList = findDivisionByEntity(entity);
		for (Division d: divisionList) {
			if (d.getRelated()!=null && d.getRelated().equals(entity)) {
				return d;
			}
		}
		return null;
	}
    public List<Agent> findAgentByEntity(Entity entity) {
        return (ArrayList<Agent>) find(AGENT_QRY, entity);
    }

    public List<Bank> findBankByEntity(Entity entity) {
        return (ArrayList<Bank>) find(BANK_QRY, entity);
    }

    public List<Manufacturer> findManufacturerByEntity(Entity entity) {
        return (ArrayList<Manufacturer>) find(MANUFATURER_QRY, entity);
    }
    
    static String CUSTOMER_QRY = "from Customer customer " +
        "where customer.entity = ?";

    static String SUPPLIER_QRY = "from Supplier supplier " +
        "where supplier.entity = ?";

    static String DIVISION_QRY = "from Division division " +
        "where division.entity = ?";

    static String CURRENTDIVISION_QRY = "from Division division " +
    	"where division.entity = ? and division.related = division.entity";

    static String AGENT_QRY = "from Agent agent " +
        "where agent.entity = ?";

    static String BANK_QRY = "from Bank bank " +
        "where bank.entity = ?";

    static String MANUFATURER_QRY = "from Manufacturer manufaturer " +
        "where manufaturer.entity = ?";

}
