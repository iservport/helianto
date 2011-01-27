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

package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.Service;

/**
 * Class to support <code>ServiceDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServiceTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Service</code>.
     * @param operator optional Operator 
     * @param serviceName optional String 
     */
    public static Service createService(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = OperatorTestSupport.createOperator();
        }
        String serviceName;
        try {
            serviceName = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            serviceName = DomainTestSupport.getNonRepeatableStringValue(testKey++, 12);
        }
        Service service = new Service(operator, serviceName);
        return service;
    }

    /**
     * Test support method to create a <code>Service</code> list.
     *
     * @param serviceListSize
     */
    public static List<Service> createServiceList(int serviceListSize) {
        return createServiceList(serviceListSize, 1);
    }

    /**
     * Test support method to create a <code>Service</code> list.
     *
     * @param serviceListSize
     * @param operatorListSize
     */
    public static List<Service> createServiceList(int serviceListSize, int operatorListSize) {
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(operatorListSize);

        return createServiceList(serviceListSize, operatorList);
    }

    /**
     * Test support method to create a <code>Service</code> list.
     *
     * @param serviceListSize
     * @param operatorList
     */
    public static List<Service> createServiceList(int serviceListSize, List<Operator> operatorList) {
        List<Service> serviceList = new ArrayList<Service>();
        for (Operator operator: operatorList) {
	        for (int i=0;i<serviceListSize;i++) {
    	        serviceList.add(createService(operator));
        	}
        }
        return serviceList;
    }

}
