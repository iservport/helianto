package org.helianto.core.utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class MD5UtilTests {

	@Test
	public void test() {
		String email = "test@helianto.org";
		assertEquals("257dc9126868b6e29c4bfdaee1b71ec8",MD5Util.md5Hex(email));
//		System.out.println(MD5Util.md5Hex(email));
	}

}
