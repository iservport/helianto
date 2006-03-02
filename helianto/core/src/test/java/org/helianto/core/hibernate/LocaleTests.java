//Created on 25/04/2005
package org.helianto.core.hibernate;

import org.helianto.core.Locale;
import org.helianto.core.LocaleType;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Locale domain objet life test.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class LocaleTests extends AbstractCoreTest {
    
    public LocaleTests() {
        setBaseQuery("from Locale");
    }
    
    public Object first() {
        Locale locale = (Locale) getCollaborator(Locale.class);
        return locale;
    }

    public Object changeUniqueKey(Object object) {
        String newKey = generateKey();
        logger.info("\n         new key is "+newKey);
        ((Locale) object).setLanguage(newKey);
        ((Locale) object).setCountry(newKey);
        return object;
    }

    public Object third() {
        Locale parent = (Locale) getCollaborator(Locale.class);
        Locale locale = (Locale) getNewCollaborator(Locale.class);
        locale.setLocaleType(LocaleType.COUNTRY.getValue());
        locale.setParent(parent);
        return locale;
    }

}
