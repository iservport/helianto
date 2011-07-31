package org.helianto.core.utils;

import org.helianto.core.Province;
import org.helianto.core.base.AbstractAddress;

/**
 * Helper class to handle addresses.
 * 
 * @author mauriciofernandesdecastro
 */
public class AddressUtils {

	/**
	 * Static convenience method to copy addresses.
	 * 
	 * @param fromAddress
	 * @param toAddress
	 */
	public static void copyAddress(AbstractAddress fromAddress, AbstractAddress toAddress) {
		toAddress.setAddress1(fromAddress.getAddress1());
		toAddress.setAddress2(fromAddress.getAddress2());
		toAddress.setAddress3(fromAddress.getAddress3());
		toAddress.setAddressDetail(fromAddress.getAddressDetail());
		toAddress.setAddressNumber(fromAddress.getAddressNumber());
		toAddress.setCityName(fromAddress.getCityName());
		toAddress.setPostalCode(fromAddress.getPostalCode());
		toAddress.setPostOfficeBox(fromAddress.getPostOfficeBox());
	}
	
	/**
	 * Static convenience method to create an address.
	 * 
	 * <p>
	 * Address segments must be provided in the following order:
	 * </p>
	 * <ol>
	 * <li>main address (Avenue, Street, etc.),</li>
	 * <li>address number,</li>
	 * <li>address detail, like apartment, office number, OR county, and</li>
	 * <li>county, if a detail was already supplied.</li>
	 * </ol>
	 * 
	 * @param province
	 * @param addressSegment
	 */
	@SuppressWarnings("serial")
	public static AbstractAddress createAddress(Province province, String... addressSegment) {
		AbstractAddress address = new AbstractAddress(province) {};
		if (addressSegment.length>0) {
			address.setAddress1(addressSegment[0]);
		}
		if (addressSegment.length>1) {
			address.setAddressNumber(addressSegment[1]);
		}
		if (addressSegment.length>2) {
			address.setAddress2(addressSegment[2]);
		}
		if (addressSegment.length>3) {
			address.setAddressDetail(addressSegment[2]);
			address.setAddress2(addressSegment[3]);
		}
		return address;
	}
	
}
