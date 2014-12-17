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

package org.helianto.partner.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.internal.AbstractEntityIdFilterAdapter;
import org.helianto.partner.form.PrivateEntityForm;

/**
 * Partner registry filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class PrivateEntityFormFilterAdapter 
	extends AbstractEntityIdFilterAdapter<PrivateEntityForm> 
{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public PrivateEntityFormFilterAdapter(PrivateEntityForm form) {
		super(form);
	}

	public boolean isSelection() {
		return super.isSelection() && getForm().getEntityAlias()!=null && getForm().getEntityAlias().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("entityAlias",getForm(). getEntityAlias(), mainCriteriaBuilder);
	}

	@Override
	protected String[] getFieldNames() {
		return new String[] { "entityAlias", "entityName" };
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("entityName", getForm().getEntityName(), mainCriteriaBuilder);
		appendStartLikeFilter("postalCode", getForm().getPostalCode(), mainCriteriaBuilder);
		if (getForm().getProvince()!=null) {
			appendEqualFilter("province.id", getForm().getProvince().getId(), mainCriteriaBuilder);
		}
		appendLikeFilter("cityName", getForm().getCityName(), mainCriteriaBuilder);
		appendLocateFilter("nature", getForm().getPartnerType(), mainCriteriaBuilder);
	}

    /**
     * Locate appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendLocateFilter(String fieldName, char fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=0 && fieldContent!=' ' && fieldContent!='_') {
    		criteriaBuilder.appendAnd().append("locate(")
    		.append(getForm().getPartnerType())
    		.append(",")
            .appendWithPrefix(fieldName)
    		.append(") != 0");
    		criteriaBuilder.addSegmentCount(1);
        }
    }
    
    @Override
    public String getOrderByString() {
    	return "entityAlias";
    }
    
}
