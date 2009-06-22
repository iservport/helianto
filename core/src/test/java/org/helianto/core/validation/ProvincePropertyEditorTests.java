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

package org.helianto.core.validation;

import org.helianto.core.Province;
import org.helianto.core.test.AbstractHibernatePropertyEditorTest;
import org.helianto.core.test.AbstractJpaPropertyEditorTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ProvincePropertyEditorTests extends AbstractHibernatePropertyEditorTest<Province, ProvincePropertyEditor> {
	
	@Override
	protected Class<Province> getTargetClazz() {
		return Province.class;
	}

	@Override
	protected Class<ProvincePropertyEditor> getPropertyEditorClazz() {
		return ProvincePropertyEditor.class;
	}

}
