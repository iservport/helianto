package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.KeyType;
import org.helianto.core.Operator;

/**
 * Class to support <code>KeyTypeDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class KeyTypeTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>KeyType</code>.
     * @param operator optional Operator 
     * @param keyCode optional String 
     */
    public static KeyType createKeyType(Object... args) {
        Operator operator;
        try {
            operator = (Operator) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            operator = OperatorTestSupport.createOperator();
        }
        String keyCode;
        try {
            keyCode = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            keyCode = DomainTestSupport.getNonRepeatableStringValue(testKey++, 20);
        }
        KeyType keyType = KeyType.keyTypeFactory(operator, keyCode);
        return keyType;
    }

    /**
     * Test support method to create a <code>KeyType</code> list.
     *
     * @param keyTypeListSize
     */
    public static List<KeyType> createKeyTypeList(int keyTypeListSize) {
        return createKeyTypeList(keyTypeListSize, 1);
    }

    /**
     * Test support method to create a <code>KeyType</code> list.
     *
     * @param keyTypeListSize
     * @param operatorListSize
     */
    public static List<KeyType> createKeyTypeList(int keyTypeListSize, int operatorListSize) {
        List<Operator> operatorList = OperatorTestSupport.createOperatorList(operatorListSize);

        return createKeyTypeList(keyTypeListSize, operatorList);
    }

    /**
     * Test support method to create a <code>KeyType</code> list.
     *
     * @param keyTypeListSize
     * @param operatorList
     */
    public static List<KeyType> createKeyTypeList(int keyTypeListSize, List<Operator> operatorList) {
        List<KeyType> keyTypeList = new ArrayList<KeyType>();
        for (Operator operator: operatorList) {
	        for (int i=0;i<keyTypeListSize;i++) {
    	        keyTypeList.add(createKeyType(operator));
        	}
        }
        return keyTypeList;
    }

}
