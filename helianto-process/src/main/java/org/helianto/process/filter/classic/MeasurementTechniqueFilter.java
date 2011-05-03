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

package org.helianto.process.filter.classic;

import org.helianto.core.Unit;
import org.helianto.core.User;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;

/**
 * Measurement technique filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MeasurementTechniqueFilter extends AbstractUserBackedCriteriaFilter {
	
	/**
	 * Factory method.
	 * 
	 * @param user
	 */
	public static MeasurementTechniqueFilter measurementTechniqueFilterFactory(User user) {
		MeasurementTechniqueFilter filter = new MeasurementTechniqueFilter();
		filter.setUser(user);
		return filter;
	}

	private static final long serialVersionUID = 1L;
	private String measurementTechniqueCode;
	private String measurementTechniqueName;
    private Unit unit;
	
	/**
	 * Default constructor.
	 */
	public MeasurementTechniqueFilter() {
		setMeasurementTechniqueCode("");
		setMeasurementTechniqueName("");
	}

	/**
	 * Reset.
	 */
	public void reset() {
		setMeasurementTechniqueCode("");
		setMeasurementTechniqueName("");
	}
	
	public boolean isSelection() {
		return getMeasurementTechniqueCode().length()>0;
	}

	@Override
	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getUnit()!=null) {
			appendEqualFilter("unit.id", getUnit().getId(), mainCriteriaBuilder);
		}
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("measurementTechniqueCode", getMeasurementTechniqueCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("measurementTechniqueName", getMeasurementTechniqueName(), mainCriteriaBuilder);
		appendOrderBy("measurementTechniqueCode", mainCriteriaBuilder);
	}

	/**
	 * Measurement technique code filter.
	 */
	public String getMeasurementTechniqueCode() {
		return this.measurementTechniqueCode;
	}
	public void setMeasurementTechniqueCode(String measurementTechniqueCode) {
		this.measurementTechniqueCode = measurementTechniqueCode;
	}

	/**
	 * Measurement technique name filter.
	 */
	public String getMeasurementTechniqueName() {
		return this.measurementTechniqueName;
	}
	public void setMeasurementTechniqueName(String measurementTechniqueName) {
		this.measurementTechniqueName = measurementTechniqueName;
	}

	/**
	 * Unit filter.
	 */
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
}
