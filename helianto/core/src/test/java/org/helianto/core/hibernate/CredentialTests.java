//Created on 25/04/2005
package org.helianto.core.hibernate;

import org.helianto.core.Credential;
import org.helianto.core.mail.JavaMailAdapter;

/**
 * Credential domain objet life test.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CredentialTests extends AbstractCoreTest {
    
    public CredentialTests() {
        setKeyType(Long.class);
        setBaseQuery("from Credential");
    }

    public Object first() {
        Credential obj = (Credential) getCollaborator(Credential.class);
        return obj;
    }

    public Object changeUniqueKey(Object object) {
        ((Credential) object).setPrincipal(generateKey()+"@domain.com");
        return object;
    }

    public Object third() {
        Credential obj = (Credential) getNewCollaborator(Credential.class);
        return obj;
    }

}
