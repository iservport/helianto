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

package org.helianto.bootstrap;

import java.util.List;

import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.springframework.core.io.Resource;

/**
 * Context installation tasks.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ContextInstallationMgr {

	/**
	 * Install an Operator, if does not exist.
	 * 
	 * <p>
	 * Automatically associate two basic services: (1) ADMIN, (2) USER.
	 * </p>
	 * 
	 * @param defaultOperatorName
	 * @param reinstall
	 */
	public Operator installOperator(String defaultOperatorName, boolean reinstall);
	
	/**
	 * Install provinces from a xml resource.
	 * 
	 * <p>Resource must contain a list of provinces:</p>
	 * <pre>
	 * &lt;provinces>
	 *     &lt;province provinceCode="XX" provinceName="xxxx" />
	 *     &lt;province provinceCode="YY" provinceName="yyyy" />
	 *     ...
	 * &lt;/provinces>
	 * </pre>
	 * 
	 * @param defaultOperator
	 * @param rs
	 */
	public void installProvinces(Operator defaultOperator, Resource rs);
	
	/**
	 * Install provinces from a province list.
	 * 
	 * @param defaultOperator
	 * @param provinceList
	 */
	public void installProvinces(Operator defaultOperator, List<Province> provinceList);

	/**
	 * Install a KeyType, if does not exist.
	 * 
	 * @param defaultOperator
	 * @param keyCode
	 */
	public KeyType installKey(Operator defaultOperator, String keyCode);

}
