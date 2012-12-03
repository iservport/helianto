package org.helianto.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.service.strategy.ProvinceResourceParserStrategy;
import org.helianto.core.service.strategy.ProvinceResourceParserStrategyImpl;
import org.helianto.core.test.OperatorTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProvinceResourceParserStrategyImplTests {
	
	@Test
	public void parse1() {
		String data = "<provinces><province provinceCode=\"C1\" provinceName=\"NAME1\" /></provinces>";
		Resource rs = new ByteArrayResource(data.getBytes());
		List<Province> provinceList = provinceResourceParserStrategy.parseProvinces(operator, rs);
		assertEquals(1, provinceList.size());
		for (Province province: provinceList) {
			assertSame(operator, province.getOperator());
			assertEquals("C1", province.getProvinceCode());
			assertEquals("NAME1", province.getProvinceName());
		}
	}
	
	@Test
	public void parse2() {
		String data = "<provinces>" +
			"<province provinceCode=\"C1\" provinceName=\"NAME1\" />" +
			"<province provinceCode=\"C2\" provinceName=\"NAME2\" />" +
				"</provinces>";
		Resource rs = new ByteArrayResource(data.getBytes());
		List<Province> provinceList = provinceResourceParserStrategy.parseProvinces(operator, rs);
		assertEquals(2, provinceList.size());
		for (Province province: provinceList) {
			assertSame(operator, province.getOperator());
		}
	}
	
	// locals
	
	private ProvinceResourceParserStrategy provinceResourceParserStrategy;
	Operator operator;
	
	@Before
	public void setUp() {
		provinceResourceParserStrategy = new ProvinceResourceParserStrategyImpl();
		operator = OperatorTestSupport.createOperator();
	}

}
