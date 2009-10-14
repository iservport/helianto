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


package org.helianto.controller;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Base class to validate target forms.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractValidator<T> implements Validator, Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		if (clazz.isAssignableFrom(AbstractTargetForm.class)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void validate(Object target, Errors errors) {
		doValidate(((AbstractTargetForm<T>) target).getTarget(), errors);
	}
	
	protected abstract void doValidate(T target, Errors errors);
	
	protected final static Log logger = LogFactory.getLog(AbstractValidator.class);

}
