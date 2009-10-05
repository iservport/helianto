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

import org.helianto.core.Service;
import org.helianto.core.test.AbstractHibernatePropertyEditorTest;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ServicePropertyEditorTests extends AbstractHibernatePropertyEditorTest<Service, ServicePropertyEditor> {
	
	@Override
	protected Class<Service> getTargetClazz() {
		return Service.class;
	}

	@Override
	protected Class<ServicePropertyEditor> getPropertyEditorClazz() {
		return ServicePropertyEditor.class;
	}

}
