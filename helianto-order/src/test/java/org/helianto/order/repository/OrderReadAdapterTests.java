package org.helianto.order.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class OrderReadAdapterTests {

	@Test
	public void constructor() {
		OrderReadAdapter adapter = new OrderReadAdapter(
				1
				, 1000L
				, 5
				, "CODE"
				, "NAME"
				, new DateTime(2001, 01, 01, 12, 30).toDate()
				, 20
				, "PERSON"
				, "IMAGE"
				, "T"
				, new DateTime(2001, 01, 02, 12, 30).toDate()
				, 30
				, "CAT"
				, "CATEGORY"
				, "REMARKS"
				, 40
				, new BigDecimal(10)
				);
		assertEquals(1, (int) adapter.getId());
		assertEquals(1000L, (long) adapter.getInternalNumber());
		assertEquals(5, (int) adapter.getPartId());
		assertEquals("CODE", adapter.getDocCode());
		assertEquals("NAME", adapter.getDocName());
		assertEquals(new DateTime(2001, 01, 01, 12, 30).toDate(), adapter.getIssueDate());
		assertEquals(20, (int) adapter.getOwnerId());
		assertEquals("PERSON", adapter.getOwnerDisplayName());
		assertEquals("IMAGE", adapter.getOwnerImageUrl());
		assertEquals(new DateTime(2001, 01, 02, 12, 30).toDate(), adapter.getCheckOutTime());
		assertEquals(30, adapter.getCategoryId());
		assertEquals("CAT", adapter.getCategoryCode());
		assertEquals("CATEGORY", adapter.getCategoryName());
		assertEquals("REMARKS", adapter.getRemarks());
		assertEquals(40, (int) adapter.getCurrencyId());
		assertEquals(new BigDecimal(10), adapter.getFaceValue());
	}

}
