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

package org.helianto.core;

import java.io.Serializable;

import org.helianto.core.Category;
import org.helianto.core.User;
import org.helianto.core.filter.UserBackedFilter;

/**
 * Unit filter.
 * 
 * @author Maurício Fernandes de Castro
 */
public class UnitFilter implements UserBackedFilter, Serializable {
	
	private static final long serialVersionUID = 1L;
	private User user;
	private Category category;
	private String unitCode;
	private String unitNameLike;
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static UnitFilter unitFilterFactory(User user) {
		UnitFilter unitFilter = new UnitFilter();
		unitFilter.setUser(user);
		return unitFilter;
	}
	
	public void reset() {
		setUnitCode("");
		setUnitNameLike("");
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Category.
	 */
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Unit code.
	 */
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * Unit name like.
	 */
	public String getUnitNameLike() {
		return unitNameLike;
	}
	public void setUnitNameLike(String unitNameLike) {
		this.unitNameLike = unitNameLike;
	}

}
