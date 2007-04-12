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

import org.helianto.core.Credential;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.Server;
import org.helianto.core.Service;
import org.helianto.core.creation.OperatorCreator;
import org.helianto.core.dao.OperatorDao;
import org.helianto.core.type.OperationMode;
import org.helianto.core.type.ActivityState;
import org.helianto.core.type.ServerType;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class OperatorTestSupport extends AbstractHibernateIntegrationTest {

    private static int testKey = 1;

    /*
     * Operator tests 
     */
    
    public static Operator createOperator(Object... args) {
        String operatorName;
        try {
            operatorName = (String) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operatorName = DomainTestSupport.getNonRepeatableStringValue(testKey++, 12);
        }
        Operator operator = OperatorCreator.operatorFactory(operatorName, OperationMode.LOCAL, Locale.getDefault());
        return operator;
    }

    public static Operator createAndPersistOperator(OperatorDao operatorDao) {
        Operator operator = createOperator();
        if (operatorDao!=null) {
            operatorDao.persistOperator(operator);
        }
        return operator;
    }

    public static List<Operator> createOperatorList(int size) {
        List<Operator> operatorList = new ArrayList<Operator>();
        for (int i=0;i<size;i++) {
            operatorList.add(createOperator());
        }
        return operatorList;
    }

    public static List<Operator> createAndPersistOperatorList(HibernateTemplate hibernateTemplate, int i) {
        List<Operator> operatorList = createOperatorList(i);
        hibernateTemplate.saveOrUpdateAll(operatorList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return operatorList;
    }
    
    /*
     * KeyType tests 
     */
    
    public static KeyType createKeyType(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = createOperator();
        }
        KeyType keyType = OperatorCreator.keyTypeFactory(operator, generateKey(20, testKey++));
        return keyType;
    }

    public static KeyType createAndPersistKeyType(OperatorDao operatorDao) {
        KeyType keyType = createKeyType();
        if (operatorDao!=null) {
            operatorDao.persistKeyType(keyType);
        }
        return keyType;
    }

    public static List<KeyType> createKeyTypeList(int size, int operatorListSize) {
        List<Operator> operatorList = createOperatorList(operatorListSize);
        List<KeyType> keyTypeList = new ArrayList<KeyType>();
        for (Operator x: operatorList) {
            for (int i=0;i<size;i++) {
                keyTypeList.add(createKeyType(x));
            }
        }
        return keyTypeList;
    }

    public static List<KeyType> createAndPersistKeyTypeList(HibernateTemplate hibernateTemplate, int i, int o) {
        List<KeyType> keyTypeList = createKeyTypeList(i, o);
        for (KeyType k: keyTypeList) {
            hibernateTemplate.merge(k);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return keyTypeList;
    }
    
    /*
     * Server tests 
     */
    
    public static Server createServer(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = createOperator();
        }
        Credential credential;
        try {
            credential = (Credential) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            credential = CredentialTestSupport.createCredential();
        }
        ServerType serverType = ServerType.values()[testKey % 3];
        Server server = OperatorCreator.serverFactory(operator, generateKey(20, testKey++), serverType, credential);
        logger.info("+++ "+server);
        return server;
    }

    public static Server createAndPersistServer(OperatorDao operatorDao) {
        Server server = createServer();
        if (operatorDao!=null) {
            operatorDao.persistServer(server);
        }
        return server;
    }

    public static List<Server> createServerList(int size, int operatorListSize) {
        List<Operator> operatorList = createOperatorList(operatorListSize);
        return createServerList(size, operatorList);
    }

    public static List<Server> createServerList(int size, List<Operator> operatorList) {
        List<Server> serverList = new ArrayList<Server>();
        Credential credential = CredentialTestSupport.createCredential();
        for (Operator o: operatorList) {
            for (int i=0;i<size;i++) {
                Server server = createServer(o, credential);
                server.setPriority((byte) (Math.random()*size));  //random priority
                server.setServerState(ActivityState.values()[(int) Math.random()*ActivityState.values().length].getValue());  //random state
                serverList.add(server);
            }
        }
        return serverList;
    }

    public static List<Server> createAndPersistServerList(HibernateTemplate hibernateTemplate, int i, int o) {
        List<Server> serverList = createServerList(i, o);
        for (Server s: serverList) {
            hibernateTemplate.merge(s);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return serverList;
    }
    
    /*
     * Province tests 
     */
    
    public static Province createProvince(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = createOperator();
        }
        Province province = OperatorCreator.provinceFactory(operator, generateKey(20, testKey++), generateKey(20));
        return province;
    }

    public static Province createAndPersistProvince(OperatorDao operatorDao) {
        Province province = createProvince();
        if (operatorDao!=null) {
            operatorDao.persistProvince(province);
        }
        return province;
    }

    public static List<Province> createProvinceList(int size, int operatorListSize) {
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(operatorListSize);
        return createProvinceList(size, operatorList);
    }

    public static List<Province> createProvinceList(int size, List<Operator> operatorList) {
        List<Province> provinceList = new ArrayList<Province>();
        for (Operator x: operatorList) {
            for (int i=0;i<size;i++) {
            	Province province = createProvince(x);
                provinceList.add(province);
            }
        }
        return provinceList;
    }

    public static List<Province> createAndPersistProvinceList(HibernateTemplate hibernateTemplate, int i, int operatorListSize) {
        List<Province> provinceList = createProvinceList(i, operatorListSize);
        for (Province x: provinceList) {
            hibernateTemplate.merge(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return provinceList;
    }
    
    /*
     * Service tests 
     */
    
    public static Service createService(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = createOperator();
        }
        Service service = OperatorCreator.serviceFactory(operator, generateKey(20, testKey++));
        return service;
    }

    public static Service createAndPersistService(OperatorDao operatorDao) {
        Service service = createService();
        if (operatorDao!=null) {
            operatorDao.persistService(service);
        }
        return service;
    }

    public static List<Service> createServiceList(int size, int operatorListSize) {
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(operatorListSize);
        return createServiceList(size, operatorList);
    }

    public static List<Service> createServiceList(int size, List<Operator> operatorList) {
        List<Service> serviceList = new ArrayList<Service>();
        for (Operator x: operatorList) {
            for (int i=0;i<size;i++) {
                serviceList.add(createService(x));
            }
        }
        return serviceList;
    }

    public static List<Service> createAndPersistServiceList(HibernateTemplate hibernateTemplate, int i, int operatorListSize) {
        List<Service> serviceList = createServiceList(i, operatorListSize);
        for (Service x: serviceList) {
            hibernateTemplate.merge(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return serviceList;
    }

}
