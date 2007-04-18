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

import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.dao.ResourceDao;
import org.helianto.process.test.ResourceTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ResourceDaoImplTests  extends AbstractProcesssIntegrationTest {
    
    /*
     * ResourceGroup tests 
     */
    
    public void testPersistResourceGroup() {
        //write
        ResourceGroup resourceGroup = ResourceTestSupport.createAndPersistResourceGroup(resourceDao);
        hibernateTemplate.flush();
        //read
        assertEquals(resourceGroup,  resourceDao.findResourceByEntityAndCode(resourceGroup.getEntity(), resourceGroup.getResourceCode()));
    }
    
    public void testFindResourceGroup() {
        // write list
        int i = 10;
        int e = 2;
        List<ResourceGroup> resourceGroupList = ResourceTestSupport.createAndPersistResourceGroupList(hibernateTemplate, i, e);
        assertEquals(i*e, resourceGroupList.size());
        // read
        ResourceGroup resourceGroup = resourceGroupList.get((int) Math.random()*i*e);
        assertEquals(resourceGroup,  resourceDao.findResourceByEntityAndCode(resourceGroup.getEntity(), resourceGroup.getResourceCode()));
        // List<ResourceGroup> findResourceAndGroupByEntity(Entity entity);
        List<ResourceGroup> resourceGroupEntityList = resourceDao.findResourceAndGroupByEntity(resourceGroup.getEntity());
        assertEquals(i, resourceGroupEntityList.size());
        assertEquals(resourceGroup.getEntity(), resourceGroupEntityList.get((int) Math.random()*i).getEntity());
        // TODO
//        List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup);
//        List<ResourceGroup> resourceChildList = resourceDao
//        .findResourceByParent(resourceList.get(10));
//        assertEquals(0, resourceChildList.size());
//        assertEquals(resourceList.get(9), resourceList.get(10).getParent());
    }

    public void testResourceGroupErrors() {
        try {
            resourceDao.persistResourceGroup(null); fail();
       } catch (IllegalArgumentException e) { 
       } catch (Exception e) { fail(); }
       try {
           resourceDao.removeResourceGroup(null); fail();
      } catch (IllegalArgumentException e) { 
      } catch (Exception e) { fail(); }
    }

    public void testResourceGroupDuplicate() {
        // write
        ResourceGroup resourceGroup = ResourceTestSupport.createAndPersistResourceGroup( resourceDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(resourceGroup); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveResourceGroup() {
        // bulk write
        int i = 10;
        int e = 2; //entities
        List<ResourceGroup> resourceGroupList = ResourceTestSupport.createAndPersistResourceGroupList(hibernateTemplate, i, e);
        assertEquals(i*e, resourceGroupList.size());
        // remove
        ResourceGroup resourceGroup = resourceGroupList.get((int) Math.random()*i*e);
        resourceDao.removeResourceGroup(resourceGroup);
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        // read
        List<ResourceGroup> all = (ArrayList<ResourceGroup>) hibernateTemplate.find("from ResourceGroup");
        assertEquals(i*e-1, all.size());
        assertFalse(all.contains(resourceGroup));
    }

//  void persistResource(Resource resource);
//  List<Resource> findResourceByEntity(Entity entity);

// TODO following tests
//  List<ResourceGroup> findResourceAndGroupByEntityAndType(Entity entity, ResourceType resourceType);
//  List<Resource> findResourceByEntityAndType(Entity entity, ResourceType resourceType);
//  List<ResourceGroup> findRootResourceByEntity(Entity entity);
//  List<ResourceGroup> findRootResourceByEntityAndType(Entity entity, ResourceType resourceType);
    
    /*
     * Resource tests 
     */
    
    public void testPersistResource() {
        //write
        Resource resource = ResourceTestSupport.createAndPersistResource(resourceDao);
        hibernateTemplate.flush();
        // TODO read
//        assertEquals(resource,  resourceDao.findResource(resource));
    }
    
    public void testFindResource() {
        // write list
//        int i = 10;
//        int e = 2;
//        int p = 2;
        // TODO integrity violation
//        List<Resource> resourceList = createAndPersistResourceList(hibernateTemplate, i, e, p);
//        assertEquals(i*e*p, resourceList.size());
//        // TODO read
//        Resource resource = resourceList.get((int) Math.random()*i);
//        assertEquals(resource,  resourceDao.findResource(resource);
        // TODO add some more finders
    }

    public void testResourceDuplicate() {
        // write
        Resource resource = ResourceTestSupport.createAndPersistResource( resourceDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(resource); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveResource() {
//        // bulk write
//        int i = 10;
//        int e = 2;
//        int p = 2;
//        List<Resource> resourceList = createAndPersistResourceList(hibernateTemplate, i, e, p);
//        assertEquals(i*e*p, resourceList.size());
//        // remove
//        Resource resource = resourceList.get((int) Math.random()*i*e*p);
//        resourceDao.removeResourceGroup(resource);
//        hibernateTemplate.flush();
//        hibernateTemplate.clear();
//        // read
//        List<Resource> all = (ArrayList<Resource>) hibernateTemplate.find("from Resource");
//        assertEquals(i*e*p-1, all.size());
//        assertFalse(all.contains(resource));
    }

    // collaborators

}
