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

/**
 * Base class to forms editing a target.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractTargetForm<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private TypeResolver typeResolver;
	private T target;
    
	/**
	 * Type resolver to this form.
	 */
	public TypeResolver getTypeResolver() {
		return typeResolver;
	}
	public void setTypeResolver(TypeResolver typeResolver) {
		this.typeResolver = typeResolver;
	}

	/**
	 * The exposed target object.
	 */
	public T getTarget() {
		return target;
	}
	public void setTarget(T target) {
		this.target = target;
	}
	public char getTargetType() {
		if (getTypeResolver()!=null && getTarget()!=null) {
			return getTypeResolver().resolveType(getTarget());
		}
		return ' ';
	}
	
}
