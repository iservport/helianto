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


package org.helianto.core.service;

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.ProvinceFilter;

/**
 * Operator management interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface OperatorMgr {

    /**
     * <p>Find <code>Operator</code> list.</p>
     */
    public List<Operator> findOperator();

    /**
     * <p>Find <code>Operator</code> by name.</p>
     */
    public Operator findOperatorByName(String operatorName);

	/**
	 * Prepare a new <code>Province</code> to the presentation layer.
	 */
	public Province prepareNewProvince(Entity entity);
	
    /**
     * Store <code>Operator</code>.
     */
    public Operator storeOperator(Operator operator);

    /**
     * Find <code>Province</code>s.
     */
	public List<Province> findProvinces(ProvinceFilter provinceFilter);
	
    /**
     * Store <code>Province</code> to the data store.
     */
	public Province storeProvince(Province province);

}
