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
import org.helianto.core.hibernate.GenericDaoImpl;
import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.helianto.process.ResourceParameter;
import org.helianto.process.ResourceParameterValue;
import org.helianto.process.dao.ResourceDao;
import org.helianto.process.type.ResourceType;

/**
 * <p>
 * Hibernate implementation for <code>ResourceDao</code> interface.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ResourceDaoImpl extends GenericDaoImpl implements ResourceDao {

    public void persistResourceGroup(ResourceGroup resourceGroup) {
        merge(resourceGroup);
    }
    
    public void persistResourceParameter(ResourceParameter resourceParameter) {
    	merge(resourceParameter);
    }
    
    public void persistResourceParameterValue(ResourceParameterValue resourceParameterValue) {
    	merge(resourceParameterValue);
    }
    
    public void removeResourceParameter(ResourceParameter resourceParameter) {
        remove(resourceParameter);
    }

    public void removeResourceParameterValue(ResourceParameterValue resourceParameterValue) {
        remove(resourceParameterValue);
    }

    //
    
    public List<ResourceGroup> findResourceAndGroupByEntity(Entity entity) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY, entity);
        return resourceList;
    }
    
    public List<Resource> findResourceByEntity(Entity entity) {
        List<Resource> resourceList = (ArrayList<Resource>) 
            find(RESOURCE_QRY, entity);
        return resourceList;
    }
    
    public List<ResourceGroup> findResourceByParent(ResourceGroup resourceGroup) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY_BYPARENT, resourceGroup);
        return resourceList;
    }
    
    public List<ResourceGroup> findRootResourceByEntity(Entity entity) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY+RESOURCEROOT_FILTER, entity);
        return resourceList;
    }

    public List<ResourceGroup> findResourceAndGroupByEntityAndType(Entity entity, ResourceType resourceType) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY+RESOURCETYPE_FILTER, entity, resourceType.getValue());
        return resourceList;
    }

    public List<Resource> findResourceByEntityAndType(Entity entity, ResourceType resourceType) {
        List<Resource> resourceList = (ArrayList<Resource>) 
            find(RESOURCE_QRY+RESOURCETYPE_FILTER, entity, resourceType.getValue());
        return resourceList;
    }

    public List<ResourceGroup> findRootResourceByEntityAndType(Entity entity, ResourceType resourceType) {
        List<ResourceGroup> resourceList = (ArrayList<ResourceGroup>) 
            find(RESOURCEGROUP_QRY+RESOURCEROOT_FILTER+RESOURCETYPE_FILTER, entity, resourceType.getValue());
        return resourceList;
    }

    public ResourceGroup findResourceByEntityAndCode(Entity entity, String resourceCode) {
        ResourceGroup resourceGroup = (ResourceGroup)
            findUnique(RESOURCEGROUP_QRY+RESOURCECODE_FILTER, entity, resourceCode);
        return resourceGroup;
    }
    
    static String RESOURCEGROUP_QRY = "from ResourceGroup resource " +
        "where resource.entity = ?";

    static String RESOURCE_QRY = "from Resource resource " +
        "where resource.entity = ?";

    static String RESOURCEGROUP_QRY_BYPARENT = "from ResourceGroup resource " +
        "where resource.parent = ?";

    static String RESOURCEROOT_FILTER = " and resource.parent = null";
    
    static String RESOURCETYPE_FILTER = " and resource.resourceType = ?";

    static String RESOURCECODE_FILTER = " and resource.resourceCode = ?";

    public List<ResourceParameter> findResourceParameterByEntity(Entity entity) {
    	List<ResourceParameter> resourceParameterList = 
    		(ArrayList<ResourceParameter>) find(RESOURCEPARAM_QRY, entity);
    	return resourceParameterList;
    }
    
    public List<ResourceParameter> findResourceParameterByParent(ResourceParameter parent) {
    	List<ResourceParameter> resourceParameterList = 
    		(ArrayList<ResourceParameter>) find(RESOURCEPARAM_QRY_BYPARENT, parent);
    	return resourceParameterList;
    }
    
    public ResourceParameter findResourceParameterByEntityAndCode(Entity entity, String resourceParameterCode) {
        return (ResourceParameter) findUnique(RESOURCEPARAM_QRY+RESOURCEPARAMCODE_FILTER, entity, resourceParameterCode);
    }

    public List<ResourceParameterValue> findResourceParameterValueByResource(ResourceGroup resourceGroup) {
    	List<ResourceParameterValue> resourceParameterValueList = 
    		(ArrayList<ResourceParameterValue>) find(RESOURCEPARAMVALUE_QRY, resourceGroup);
    	return resourceParameterValueList;
    }
    
    static String RESOURCEPARAM_QRY = "from ResourceParameter param " +
    	"where param.entity = ?";

    static String RESOURCEPARAM_QRY_BYPARENT = "from ResourceParameter param " +
        "where param.parent = ?";

    static String RESOURCEPARAMVALUE_QRY = "from ResourceParameterValue paramValue " +
		"where paramValue.resource = ?";

    static String RESOURCEPARAMCODE_FILTER = " and param.parameterCode = ?";

    public void removeResourceGroup(ResourceGroup resourceGroup) {
        remove(resourceGroup);
    }

}
