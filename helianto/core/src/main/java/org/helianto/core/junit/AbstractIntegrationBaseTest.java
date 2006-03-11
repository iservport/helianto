package org.helianto.core.junit;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import junit.framework.TestCase;

/**
 * 
 */
public class AbstractIntegrationBaseTest extends TestCase {
    
    public static final Log logger = LogFactory.getLog(AbstractIntegrationBaseTest.class);

    /**
     * Generate a not repeatable key.
     */
    public String generateKey() {
        return String.valueOf(new Date().getTime());
    }

    /**
     * Generate a not repeatable key of a given size.
     */
    public String generateKey(int size) {
        String localKey = generateKey();
        logger.info("original key "+localKey);
        while (localKey.length()!=size) {
            if (localKey.length() > size) {
                localKey = localKey.substring(localKey.length()-size, localKey.length());
            } else if (localKey.length() < size) {
                localKey = localKey.concat(localKey);
            }
        }
        return localKey;
    }

}
