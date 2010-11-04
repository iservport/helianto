package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mauriciofernandesdecastro
 */
public class FilterTests {
	
	private AbstractFilter filter;
	private CriteriaBuilder createdCriteriaBuilder, preProcessedCriteriaBuilder, selectionCriteriaBuilder, filterCriteriaBuilder, postProcessedCriteriaBuilder;
	private boolean selection, reset;
	private String orderBy = "";
	
	@Test
	public void inheritance() {
		assertTrue(filter instanceof Serializable);
		assertTrue(filter instanceof Filter);
	}
	
	/**
	 * Criteria builder is created?
	 */
	@Test
	public void mainCriteriaBuilder() {
		filter.createCriteriaAsString();
		assertEquals("TEST_ALIAS", createdCriteriaBuilder.getPrefix());
	}
	
	/**
	 * As selection implementation is dummy, result string is empty?
	 * Does criteria builder get pre-processed?
	 * Does criteria builder go into selection?
	 * Is reset fired?
	 * Does criteria builder go into filter (it should not)?
	 * Does criteria builder get post-processed?
	 */
	@Test
	public void mainCriteriaBuilderSelection() {
		selection = true;
		assertEquals("", filter.createCriteriaAsString());
		assertSame(createdCriteriaBuilder, preProcessedCriteriaBuilder);
		assertSame(createdCriteriaBuilder, selectionCriteriaBuilder);
		assertTrue(reset);
		assertEquals("FILTER", filterCriteriaBuilder.getPrefix());
		assertSame(postProcessedCriteriaBuilder, preProcessedCriteriaBuilder);
	}
	
	@Test
	public void mainCriteriaBuilderFilter() {
		selection = false;
		assertEquals("", filter.createCriteriaAsString());
		assertSame(createdCriteriaBuilder, preProcessedCriteriaBuilder);
		assertEquals("SELECTION", selectionCriteriaBuilder.getPrefix());
		assertFalse(reset);
		assertSame(createdCriteriaBuilder, filterCriteriaBuilder);
		assertSame(postProcessedCriteriaBuilder, preProcessedCriteriaBuilder);
	}
	
	@Test
	public void mainCriteriaBuilderFilterOrderBy() {
		selection = false;
		orderBy = "ORDER";
		assertEquals("order by TEST_ALIAS.ORDER ", filter.createCriteriaAsString());
		assertSame(createdCriteriaBuilder, preProcessedCriteriaBuilder);
		assertEquals("SELECTION", selectionCriteriaBuilder.getPrefix());
		assertFalse(reset);
		assertSame(createdCriteriaBuilder, filterCriteriaBuilder);
		assertSame(postProcessedCriteriaBuilder, preProcessedCriteriaBuilder);
	}
	
	@Test
	public void appendStringEqualFilter() {
		filter.appendEqualFilter("TEST", "CONTENT", createdCriteriaBuilder);
		assertEquals("CREATED.TEST = 'CONTENT' ", createdCriteriaBuilder.getCriteriaAsString());
	}
	
	@Test
	public void appendIntegerEqualFilter() {
		filter.appendEqualFilter("TEST", Integer.MAX_VALUE, createdCriteriaBuilder);
		assertEquals("CREATED.TEST = 2147483647 ", createdCriteriaBuilder.getCriteriaAsString());
	}
	
	@Test
	public void appendLongEqualFilter() {
		filter.appendEqualFilter("TEST", Long.MAX_VALUE, createdCriteriaBuilder);
		assertEquals("CREATED.TEST = 9223372036854775807 ", createdCriteriaBuilder.getCriteriaAsString());
	}
	
	@Test
	public void appendCharEqualFilter() {
		filter.appendEqualFilter("TEST", 'Z', createdCriteriaBuilder);
		assertEquals("CREATED.TEST = 'Z' ", createdCriteriaBuilder.getCriteriaAsString());
	}
	
	@Test
	public void appendLikeFilter() {
		filter.appendLikeFilter("TEST", "content", createdCriteriaBuilder);
		assertEquals("lower(CREATED.TEST) like '%content%' ", createdCriteriaBuilder.getCriteriaAsString());
	}
	
	@Test
	public void appendLikeCaseFilter() {
		filter.appendLikeCaseFilter("TEST", "content", createdCriteriaBuilder);
		assertEquals("CREATED.TEST like '%content%' ", createdCriteriaBuilder.getCriteriaAsString());
	}
	
	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		createdCriteriaBuilder = new CriteriaBuilder("CREATED");
		preProcessedCriteriaBuilder = new CriteriaBuilder("PREPROC");
		selectionCriteriaBuilder = new CriteriaBuilder("SELECTION");
		filterCriteriaBuilder = new CriteriaBuilder("FILTER");
		postProcessedCriteriaBuilder = new CriteriaBuilder("POSTPROC");
		filter = new AbstractFilter() {
			public String getObjectAlias() { return "TEST_ALIAS"; }
			@Override protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
				selectionCriteriaBuilder = mainCriteriaBuilder;
			}
			@Override protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
				filterCriteriaBuilder = mainCriteriaBuilder;
			}
			@Override protected String createCriteriaAsString(CriteriaBuilder mainCriteriaBuilder) {
				createdCriteriaBuilder = mainCriteriaBuilder;
				return super.createCriteriaAsString(mainCriteriaBuilder);
			}
			@Override protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
				preProcessedCriteriaBuilder = mainCriteriaBuilder;
			}
			@Override protected void postProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
				postProcessedCriteriaBuilder = mainCriteriaBuilder;
			}
			@Override public boolean isSelection() { return selection; }
			public void reset() { reset = true; }
			@Override public String getOrderByString() { return orderBy; }
		};
	}

}
