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

package org.helianto.partner.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.helianto.core.dao.ProvinceDao;
import org.helianto.core.test.OperatorTestSupport;

import junit.framework.TestCase;

/**
 * @author Mauricio Fernandes de Castro
 */
public class PartnerMgrImplTests extends TestCase {
    
    private PartnerMgrImpl partnerMgrImpl;
    
    public void testFindProvinceByOperator() {
        Operator operator = OperatorTestSupport.createOperator();
        List<Province> provinceList = new ArrayList<Province>();
        
        expect(provinceDao.findProvinceByOperator(operator))
            .andReturn(provinceList);
        replay(provinceDao);
        
        assertSame(provinceList, partnerMgrImpl.findProvinceByOperator(operator));
        verify(provinceDao);
    }
    
    private ProvinceDao provinceDao;
    
    @Override
    public void setUp() {
        partnerMgrImpl = new PartnerMgrImpl();
        provinceDao = createMock(ProvinceDao.class);
        partnerMgrImpl.setProvinceDao(provinceDao);
    }
    
    @Override
    public void tearDown() {
        reset(provinceDao);
    }

}
