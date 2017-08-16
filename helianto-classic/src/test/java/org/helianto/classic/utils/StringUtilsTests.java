package org.helianto.classic.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.helianto.core.utils.StringListUtils;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro

 */
public class StringUtilsTests {

	@SuppressWarnings("unchecked")
	@Test
	public void mapToString() {
		String p1 = null;
		Map<String, Object> propertyMap = StringListUtils.propertiesToMap(p1);
		assertNotNull(propertyMap);
		assertEquals(0, propertyMap.size());
		
		p1 = "";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(0, propertyMap.size());
		
		p1 = ",";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(0, propertyMap.size());
		
		p1 = ",,";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(0, propertyMap.size());
		
		p1 = "=";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(0, propertyMap.size());
		
		p1 = "key";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		Entry<String, Object> entry = propertyMap.entrySet().iterator().next();
		assertEquals("key", entry.getKey());
		assertNull(entry.getValue());
		
		p1 = "key=";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		entry = propertyMap.entrySet().iterator().next();
		assertEquals("key", entry.getKey());
		assertNull(entry.getValue());
		
		p1 = "key=value";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		entry = propertyMap.entrySet().iterator().next();
		assertEquals("key", entry.getKey());
		assertEquals("value", entry.getValue());
		
		p1 = "key=1";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		entry = propertyMap.entrySet().iterator().next();
		assertEquals("key", entry.getKey());
		assertEquals("1", entry.getValue());
		
		p1 = " key=1";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		entry = propertyMap.entrySet().iterator().next();
		assertEquals("key", entry.getKey());
		assertEquals("1", entry.getValue());
		
		p1 = "key1=1,key2=2";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(2, propertyMap.size());
		assertEquals("1", propertyMap.get("key1"));
		assertEquals("2", propertyMap.get("key2"));
		
		p1 = "key1=1, key2=2";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(2, propertyMap.size());
		assertEquals("1", propertyMap.get("key1"));
		assertEquals("2", propertyMap.get("key2"));
		
		p1 = "{ }";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(0, propertyMap.size());
		
		p1 = "{ \"key\":\"1\" }";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		entry = propertyMap.entrySet().iterator().next();
		assertEquals("key", entry.getKey());
		assertEquals("1", entry.getValue());
		
		p1 = "{ \"key\":\"�\" }";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		entry = propertyMap.entrySet().iterator().next();
		assertEquals("key", entry.getKey());
		assertEquals("�", entry.getValue());
		
		p1 = "{ \"key1\":\"1\", \"key2\":\"2\" }";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(2, propertyMap.size());
		assertEquals("1", propertyMap.get("key1"));
		assertEquals("2", propertyMap.get("key2"));
		
		p1 = "{ \"key1\":{ \"key2\":\"2\" } }";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		assertEquals("2", ((Map<String, Object>) propertyMap.get("key1")).get("key2"));
		
		p1 = "{ \"key1\":[ \"1\", \"2\" ] }";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		assertEquals("2", ((List<String>) propertyMap.get("key1")).get(1));
		
		p1 = "{ \"key\" error \"1\" }";
		propertyMap = StringListUtils.propertiesToMap(p1);
		assertEquals(1, propertyMap.size());
		entry = propertyMap.entrySet().iterator().next();
		assertEquals("error", entry.getKey());
		assertTrue(entry.getValue().toString().startsWith("Unexpected character ('e' (code 101)):"));
		
	}

}
