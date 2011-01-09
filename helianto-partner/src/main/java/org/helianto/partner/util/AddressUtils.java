package org.helianto.partner.util;

import org.helianto.core.Province;
import org.helianto.partner.AbstractAddress;
import org.helianto.partner.PrivateEntity;

/**
 * Helper class to handle addresses.
 * 
 * @author mauriciofernandesdecastro
 */
public class AddressUtils {

	/**
	 * Static convenience method to copy address to private entity.
	 * 
	 * @param fromAddress
	 * @param privateEntity
	 */
	public static void copyAddress(AbstractAddress fromAddress, PrivateEntity privateEntity) {
		privateEntity.setAddress1(fromAddress.getAddress1());
		privateEntity.setAddress2(fromAddress.getAddress2());
		privateEntity.setAddress3(fromAddress.getAddress3());
		privateEntity.setAddressDetail(fromAddress.getAddressDetail());
		privateEntity.setAddressNumber(fromAddress.getAddressNumber());
		privateEntity.setCityName(fromAddress.getCityName());
		privateEntity.setPostalCode(fromAddress.getPostalCode());
		privateEntity.setPostOfficeBox(fromAddress.getPostOfficeBox());
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
