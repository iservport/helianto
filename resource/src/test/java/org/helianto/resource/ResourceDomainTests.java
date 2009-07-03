package org.helianto.resource;

import java.math.BigDecimal;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.resource.ResourceGroup;
import org.helianto.resource.ResourceParameter;
import org.helianto.resource.ResourceParameterValue;
import org.helianto.resource.ResourceType;

import junit.framework.TestCase;

public class ResourceDomainTests extends TestCase {
	
	private ResourceGroup resourceGroup;
	private ResourceParameter resourceParameter;
	private ResourceParameterValue resourceParameterValue;
	private Unit unit;
	
	private Entity entity;
	
	public void testUnit() {
		unit = new Unit();
		unit.setEntity(new Entity());
		unit.setId(Integer.MAX_VALUE);
		unit.setId(Integer.MIN_VALUE);
		unit.setUnitCode("");
		unit.setUnitName("");
	}
	
	public void testUnitEquals() {
		Entity e = new Entity();
		e.setAlias("E");
		unit = new Unit();
		unit.setEntity(e);
		unit.setUnitCode("123");

        assertTrue(unit.equals(unit));
        assertFalse(unit.equals(null));
        assertFalse(unit.equals(new Object()));
        
        Unit copy = new Unit();
        assertFalse(unit.equals(copy));
        
        copy.setEntity(e);
        copy.setUnitCode("");
        assertFalse(unit.equals(copy));

        copy.setEntity(e);
        copy.setUnitCode("123");
        assertTrue(unit.equals(copy));
    }
	
   public void tesResourceGroupt() {
        // ResourceGroup
        resourceGroup = new ResourceGroup();
        entity = new Entity();
        resourceGroup.setEntity(entity);
        resourceGroup.setResourceCode("");
        resourceGroup.setParent(null);
        resourceGroup.setParent(new ResourceGroup());
        resourceGroup.setResourceName("");
        resourceGroup.setResourceType(ResourceType.EQUIPMENT.getValue());
        resourceGroup.setResourceType(ResourceType.FIXTURE.getValue());
        resourceGroup.setResourceType(ResourceType.TOOL.getValue());
        
        assertTrue(resourceGroup.equals(resourceGroup));
        assertFalse(resourceGroup.equals(null));
        assertFalse(resourceGroup.equals(new Object()));
        ResourceGroup rg = new ResourceGroup();
        assertFalse(resourceGroup.equals(rg));
        resourceGroup.setResourceCode("123");
        rg.setEntity(null);
        rg.setResourceCode(null);
        assertFalse(resourceGroup.equals(rg));
        rg.setEntity(entity);
        assertFalse(resourceGroup.equals(rg));
        rg.setResourceCode("123");
        assertTrue(resourceGroup.equals(rg));
        

    }

	public void testResourceParameter() {
		resourceParameter = new ResourceParameter();
		resourceParameter.setEntity(entity);
		resourceParameter.setId(Integer.MAX_VALUE);
		resourceParameter.setId(Integer.MIN_VALUE);
		resourceParameter.setParamDesc("");
		resourceParameter.setParameterCode("");
		resourceParameter.setParamName("");
		resourceParameter.setParent(new ResourceParameter());
		resourceParameter.setTextOnly(true);
		resourceParameter.setTextOnly(false);
		resourceParameter.setUnit(new Unit());
	}

	public void testResourceParameterEquals() {
		resourceParameter = new ResourceParameter();
		resourceParameter.setEntity(entity);
		resourceParameter.setParameterCode("123");

        assertTrue(resourceParameter.equals(resourceParameter));
        assertFalse(resourceParameter.equals(null));
        assertFalse(resourceParameter.equals(new Object()));
        
        ResourceParameter copy = new ResourceParameter();
        assertFalse(resourceParameter.equals(copy));
        
        copy.setEntity(entity);
        copy.setParameterCode("");
        assertFalse(resourceParameter.equals(copy));

        copy.setEntity(entity);
        copy.setParameterCode("123");
        assertTrue(resourceParameter.equals(copy));
    }
	
	public void testResourceParamValue() {
		resourceParameterValue = new ResourceParameterValue();
		resourceParameterValue.setId(Integer.MAX_VALUE);
		resourceParameterValue.setId(Integer.MIN_VALUE);
		resourceParameterValue.setParameter(new ResourceParameter());
		resourceParameterValue.setParameterNumericValue(BigDecimal.ZERO);
		resourceParameterValue.setParameterTextValue("");
		resourceParameterValue.setResource(new ResourceGroup());
		resourceParameterValue.setSuppressed(false);
		resourceParameterValue.setSuppressed(true);
	}

	public void testResourceParameterValueEquals() {
		ResourceGroup rg = new ResourceGroup();
		rg.setEntity(new Entity());
		ResourceParameter rp = new ResourceParameter();
		rp.setEntity(new Entity());
		resourceParameterValue = new ResourceParameterValue();
		resourceParameterValue.setResource(rg);
		resourceParameterValue.setParameter(rp);

        assertTrue(resourceParameterValue.equals(resourceParameterValue));
        assertFalse(resourceParameterValue.equals(null));
        assertFalse(resourceParameterValue.equals(new Object()));
        
        ResourceParameterValue copy = new ResourceParameterValue();
        assertFalse(resourceParameterValue.equals(copy));
        
        copy.setResource(rg);
        copy.setParameter(new ResourceParameter());
        assertFalse(resourceParameterValue.equals(copy));

        copy.setResource(rg);
        copy.setParameter(rp);
        assertTrue(resourceParameterValue.equals(copy));
    }
	
}
