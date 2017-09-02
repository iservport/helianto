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
import java.util.Locale;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Service;

public class OperatorTestSupport {

    private static int testKey = 1;

    public static Operator createOperator(int id) {
        Operator operator = OperatorTestSupport.createOperator();
        operator.setId(id);
        return operator;
    }

    public static Operator createOperator() {
        return OperatorTestSupport.createOperator(DomainTestSupport.getNonRepeatableStringValue(testKey++, 12));
    }

    public static Operator createOperator(String operatorName) {
        Operator operator = new Operator(operatorName, Locale.getDefault());
        return operator;
    }

    public static List<Operator> createOperatorList(int size) {
        List<Operator> operatorList = new ArrayList<Operator>();
        for (int i=0;i<size;i++) {
            operatorList.add(createOperator());
        }
        return operatorList;
    }

}
