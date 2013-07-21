package org.helianto.core.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Programmable;
import org.helianto.core.def.CategoryGroup;
import org.helianto.core.def.HumanReadable;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Entity;
import org.helianto.core.test.DomainTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>Category</code> domain tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CategoryTests {
    
    /**
     * Test <code>Category</code> static factory method.
     */
	@Test
    public void constructor() {
        assertSame(entity, category.getEntity());
        assertEquals(CategoryGroup.INSTRUMENT.getValue(), category.getCategoryGroup());
        assertEquals("CODE", category.getCategoryCode());
        assertEquals('0', category.getPriority());
        
    }
    /**
     * Test <code>Category</code> equals() method.
     */
	@Test
    public void categoryEquals() {
        Category copy = (Category) DomainTestSupport.minimalEqualsTest(category);
        
        copy.setEntity(null);
        copy.setCategoryGroup(' ');
        copy.setCategoryCode("CODE");
        assertFalse(category.equals(copy));

        copy.setEntity(entity);
        copy.setCategoryGroupAsEnum(CategoryGroup.INSTRUMENT);
        copy.setCategoryCode(null);
        assertFalse(category.equals(copy));

        copy.setEntity(entity);
        copy.setCategoryGroup(' ');
        copy.setCategoryCode("CODE");
        assertFalse(category.equals(copy));

        copy.setEntity(entity);
        copy.setCategoryGroupAsEnum(CategoryGroup.INSTRUMENT);
        copy.setCategoryCode("CODE");

        assertTrue(category.equals(copy));
    }
	
	@Test
	public void humanReadable() {
		HumanReadable humanReadable = category;
		category.setContent("TESTE".getBytes());
		assertEquals("TESTE", humanReadable.getContentAsString());
		humanReadable.setContentAsString("CONTENT");
		assertEquals("CONTENT", humanReadable.getContentAsString());
		assertEquals("iso_88591", humanReadable.getEncoding());
		category.setEncoding("iso_88592");
		assertEquals("iso_88592", humanReadable.getEncoding());
		assertEquals("text/plain", humanReadable.getMultipartFileContentType());
		assertTrue(humanReadable.isText());
		assertFalse(humanReadable.isHtml());
		category.setMultipartFileContentType("text/html");
		assertEquals("text/html", humanReadable.getMultipartFileContentType());
		assertTrue(humanReadable.isText());
		assertTrue(humanReadable.isHtml());
		category.setMultipartFileContentType("image/gif");
		assertEquals("image/gif", humanReadable.getMultipartFileContentType());
		assertFalse(humanReadable.isText());
		assertFalse(humanReadable.isHtml());
		logger.debug("Category satisfies HumanReadable interface");
	}
	
	@Test
	public void programmble() {
		Programmable programmable = category;
		category.setScriptItems(" ");
		assertEquals(" ", programmable.getScriptItems());
		assertArrayEquals(new String[]{}, programmable.getScriptItemsAsArray());
		category.setScriptItems("A");
		assertEquals("A", programmable.getScriptItems());
		assertArrayEquals(new String[]{"A"}, programmable.getScriptItemsAsArray());
		category.setScriptItems("A, B");
		assertEquals("A, B", programmable.getScriptItems());
		assertArrayEquals(new String[]{"A","B"}, programmable.getScriptItemsAsArray());
		List<String> scriptList = new ArrayList<String>();
		category.setScriptList(scriptList);
		assertSame(scriptList, programmable.getScriptList());
		logger.debug("Category satisfies Programmable interface");
	}
	
	private Entity entity;
	private Category category;
	
	@Before
	public void setUp() {
        entity = new Entity();
        category = new Category(entity, CategoryGroup.INSTRUMENT, "CODE");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryTests.class);

}
    
    
