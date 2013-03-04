package org.helianto.core.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Identity;


/**
 * Class to support <code>CredentialDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CredentialTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Credential</code>.
     * @param identity optional Identity 
     */
    public static Credential createCredential(Object... args) {
        Identity identity;
        try {
            identity = (Identity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            identity = IdentityTestSupport.createIdentity();
        }
        Credential credential = new Credential(identity, DomainTestSupport.getNonRepeatableStringValue(testKey, 20));
        return credential;
    }

    /**
     * Test support method to create a <code>Credential</code> list.
     *
     * @param credentialListSize
     */
    public static List<Credential> createCredentialList(int credentialListSize) {
        List<Identity> identityList = IdentityTestSupport.createIdentityList(credentialListSize);
        return createCredentialList(identityList);
    }

    /**
     * Test support method to create a <code>Credential</code> list.
     *
     * @param credentialListSize
     * @param identityList
     */
    public static List<Credential> createCredentialList(List<Identity> identityList) {
        List<Credential> credentialList = new ArrayList<Credential>();
        for (Identity identity: identityList) {
            credentialList.add(createCredential(identity));
        }
        return credentialList;
    }

}
