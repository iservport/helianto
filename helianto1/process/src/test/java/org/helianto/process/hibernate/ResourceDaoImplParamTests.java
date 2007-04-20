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

package org.helianto.process.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.creation.ResourceCreatorImpl;
import org.helianto.process.dao.ResourceDao;
import org.helianto.process.junit.AbstractMaterialTest;
import org.helianto.process.test.ResourceTestSupport;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ResourceDaoImplParamTests extends AbstractProcesssIntegrationTest {
    
    /*
     * ResourceParameter tests 
     */
    
    public static ResourceParameter createAndPersistResourceParameter(ResourceDao resourceDao) {
        Unit unit = AbstractMaterialTest.createAndPersistUnit(null);
        Entity entity = EntityTestSupport.createEntity();
        ResourceParameter resourceParameter = ResourceCreatorImpl.resourceParameterFactory(entity, generateKey(20), unit);
        if (resourceDao!=null) {
            resourceDao.persistResourceParameter(resourceParameter);
        }
        return resourceParameter;
    }

    public static List<ResourceParameter> createAndPersistResourceParameterList(HibernateTemplate hibernateTemplate, int i, int e, int c) {
        List<ResourceParameter> resourceParameterList = createResourceParameterList(i, e, c);
        for (ResourceParameter x: resourceParameterList) {
            hibernateTemplate.merge(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return resourceParameterList;
    }
    
    public static List<ResourceParameter> createResourceParameterList(int size, int entityListSize, int childListSize) {
        Unit unit = AbstractMaterialTest.createAndPersistUnit(null);
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        List<ResourceParameter> resourceParameterList = new ArrayList<ResourceParameter>();
        for (Entity e: entityList) {
            for (int i=0;i<size;i++) {
                String uniqueIn1stLevel = generateKey(20, i*(childListSize+1));
                ResourceParameter parent = ResourceCreatorImpl.resourceParameterFactory(e, uniqueIn1stLevel, unit);
                resourceParameterList.add(parent);
                for (int j=0;j<childListSize;j++) {
                    String uniqueIn2ndLevel = generateKey(20, i*(childListSize+1)+j+1);
                    ResourceParameter child = ResourceCreatorImpl.resourceParameterFactory(e, uniqueIn2ndLevel, unit);
                    child.setParent(parent);
                    resourceParameterList.add(child);
                }
            }
        }
        return resourceParameterList ;
    }

    
    
    public void testPersistResourceParameter() {
        //write
        ResourceParameter resourceParameter = createAndPersistResourceParameter(resourceDao);
        hibernateTemplate.flush();
        //read
        assertEquals(resourceParameter,  resourceDao.findResourceParameterByEntityAndCode(resourceParameter.getEntity(), resourceParameter.getParameterCode()));
    }
    
    public void testFindResourceParameter() {
//        // write list
//        int i = 10;
//        int e = 2; //entities
//        int c = 2; //children
//        int size = i*e*(c+1);
//        List<ResourceParameter> resourceParameterList = createAndPersistResourceParameterList(hibernateTemplate, i, e, c);
//        assertEquals(size, resourceParameterList.size());
//        // read
//        ResourceParameter resourceParameter = resourceParameterList.get((int) Math.random()*size);
//        assertEquals(resourceParameter,  resourceDao.findResourceParameterByEntityAndCode(resourceParameter.getEntity(), resourceParameter.getParameterCode()));
//        List<ResourceParameter> resourceParameterEntityList = 
//            resourceDao.findResourceParameterByEntity(resourceParameter.getEntity());
//        assertEquals(size/e, resourceParameterEntityList.size());
//        assertEquals(resourceParameter.getEntity(), resourceParameterEntityList.get((int) Math.random()*size/e).getEntity());
//        List<ResourceParameter> resourceParameterChildrenList = 
//            resourceDao.findResourceParameterByParent(resourceParameter);
//        assertEquals(c, resourceParameterChildrenList.size());
//        assertEquals(resourceParameter, resourceParameterChildrenList.get((int) Math.random()*c).getParent());
    }

    public void testResourceParameterErrors() {
        try {
            resourceDao.persistResourceParameter(null); fail();
       } catch (IllegalArgumentException e) { 
       } catch (Exception e) { fail(); }
       try {
           resourceDao.removeResourceParameter(null); fail();
      } catch (IllegalArgumentException e) { 
      } catch (Exception e) { fail(); }
    }

    public void testResourceParameterDuplicate() {
//        // write
//        ResourceParameter resourceParameter = createAndPersistResourceParameter( resourceDao);
//        hibernateTemplate.clear();
//        // duplicate
//        try {
//            hibernateTemplate.save(resourceParameter); fail();
//        } catch (DataIntegrityViolationException e) { 
//        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveResourceParameter() {
//        // bulk write
//        int i = 10;
//        int e = 2;
//        int c = 2; //children
//        int size = i*e*(c+1);
//        List<ResourceParameter> resourceParameterList = createAndPersistResourceParameterList(hibernateTemplate, i, e, c);
//        assertEquals(size, resourceParameterList.size());
//        // remove
//        ResourceParameter resourceParameter = resourceParameterList.get((int) Math.random()*size);
//        resourceDao.removeResourceParameter(resourceParameter);
//        hibernateTemplate.flush();
//        hibernateTemplate.clear();
//        // read
//        List<ResourceParameter> all = (ArrayList<ResourceParameter>) hibernateTemplate.find("from ResourceParameter");
//        assertEquals(size-1, all.size());
//        assertFalse(all.contains(resourceParameter));
    }

    /*
     * ResourceParameterValue tests 
     */
    
    public static ResourceParameterValue createAndPersistResourceParameterValue(ResourceDao resourceDao) {
        ResourceGroup resourceGroup = ResourceTestSupport.createAndPersistResourceGroup(null);
        ResourceParameter resourceParameter = createAndPersistResourceParameter(null);
        ResourceParameterValue resourceParameterValue = ResourceCreatorImpl.resourceParameterValueFactory(resourceGroup, resourceParameter);
        if (resourceDao!=null) {
            resourceDao.persistResourceParameterValue(resourceParameterValue);
        }
        return resourceParameterValue;
    }

    public static List<ResourceParameterValue> createAndPersistResourceParameterValueList(HibernateTemplate hibernateTemplate, int i, int e) {
        // TODO needs initialization ?
        List<ResourceParameterValue> resourceParameterValueList = createResourceParameterValueList(i, e);
        hibernateTemplate.saveOrUpdateAll(resourceParameterValueList);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return resourceParameterValueList;
    }
    
    public static List<ResourceParameterValue> createResourceParameterValueList(int size, int entityListSize) {
        // TODO needs initialization ?
//        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        List<ResourceParameterValue> resourceParameterValueList = new ArrayList<ResourceParameterValue>();
//        for (Entity e: entityList) {
//            for (int i=0;i<size;i++) {
//                resourceParameterValueList.add(ResourceCreatorImpl.resourceParameterValueFactory(e, generateKey(20, i)));
//            }
//        }
        return resourceParameterValueList;
    }

    
    
    public void testPersistResourceParameterValue() {
//        //write
//        ResourceParameterValue resourceParameterValue = createAndPersistResourceParameterValue(resourceDao);
//        hibernateTemplate.flush();
//        //read
//        assertEquals(resourceParameterValue,  resourceDao.findResourceParameterValueByResource(resourceParameterValue.getResource()));
    }
    
    public void testFindResourceParameterValue() {
//        // write list
//        int i = 10;
//        List<ResourceParameterValue> resourceParameterValueList = createAndPersistResourceParameterValueList(hibernateTemplate, i);
//        assertEquals(i, resourceParameterValueList.size());
//        // read
//        ResourceParameterValue resourceParameterValue = resourceParameterValueList.get((int) Math.random()*i);
//        assertEquals(resourceParameterValue,  resourceDao.findResourceParameterValue(resourceParameterValue);
//        // TODO add some more finders
    }

    public void testResourceParameterValueErrors() {
        try {
             resourceDao.persistResourceParameterValue(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             resourceDao.removeResourceParameterValue(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testResourceParameterValueDuplicate() {
//        // write
//        ResourceParameterValue resourceParameterValue = createAndPersistResourceParameterValue(resourceDao);
//        hibernateTemplate.clear();
//        // duplicate
//        try {
//            hibernateTemplate.save(resourceParameterValue); fail();
//        } catch (DataIntegrityViolationException e) { 
//        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveResourceParameterValue() {
//        // bulk write
//        int i = 10;
//        List<ResourceParameterValue> resourceParameterValueList = createAndPersistResourceParameterValueList(hibernateTemplate, i);
//        assertEquals(i, resourceParameterValueList.size());
//        // remove
//        ResourceParameterValue resourceParameterValue = resourceParameterValueList.get((int) Math.random()*i);
//        resourceDao.removeResourceParameterValue(resourceParameterValue);
//        hibernateTemplate.flush();
//        hibernateTemplate.clear();
//        // read
//        List<ResourceParameterValue> all = (ArrayList<ResourceParameterValue>) hibernateTemplate.find("from ResourceParameterValue");
//        assertEquals(i-1, all.size());
//        assertFalse(all.contains(resourceParameterValue));
    }

    
}
