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


package org.helianto.web.controller;

import org.helianto.controller.AbstractValidator;
import org.helianto.core.Entity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * Validate entity forms.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("entityFormValidator")
public class EntityFormValidator extends AbstractValidator<Entity> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doValidate(Entity target, Errors errors) {
		if (logger.isDebugEnabled()) {
			logger.debug("Validating alias");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "target.alias", "field.required", "REQUIRED");
	}

}
