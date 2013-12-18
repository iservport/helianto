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

package org.helianto.document.filter.internal;

import java.util.Date;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to filters that require a <code>Controllable</code> form.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractRecordFilterAdapter <T extends Record> extends AbstractOccurrenceFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Form constructor.
	 * 
	 * @param form
	 */
	public AbstractRecordFilterAdapter(T form) {
		super(form);
	}
	
	@Override
	public boolean isSelection() {
		return super.isSelection() && getForm().getInternalNumber()>0;
	}
	
	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entity", getForm().getEntity().getId(), mainCriteriaBuilder);
		appendEqualFilter("internalNumber", getForm().getInternalNumber(), mainCriteriaBuilder);		
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("complete", getForm().getComplete(), true, mainCriteriaBuilder);
	}

	/**
	 * Field name.
	 */
	public String getDateFieldName() {
		logger.debug("Date field name set to nextCheckDate");
		return "nextCheckDate";
	}
	
	/**
	 * Next check date.
	 */
	@Override
	public Date getToDate() {
		return getForm().getNextCheckDate();
	}
	
	 private static Logger logger = LoggerFactory.getLogger(AbstractRecordFilterAdapter.class);
	
}
