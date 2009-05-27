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
package org.helianto.partner.validation;

import org.helianto.core.validation.AbstractJpaPropertyEditor;
import org.helianto.partner.Partner;
import org.springframework.stereotype.Component;

/**
 * Default <code>EntityManager</code> backed <code>Partner</code> property
 * editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("partnerPropertyEditor")
public class PartnerPropertyEditor extends AbstractJpaPropertyEditor {

	@Override
	public String getAsText() {
		Partner partner = (Partner) getValue();
        return String.valueOf(partner.getPartnerRegistry().getPartnerAlias());
	}

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		setAsText(id, Partner.class);
	}

}
