package org.helianto.core.orm;

import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.dao.CredentialDao;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.CredentialTestSupport;
import org.springframework.dao.DataIntegrityViolationException;
/**
 * <code>CredentialDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class CredentialDaoImplTests extends AbstractIntegrationTest {
    
    private CredentialDao credentialDao;
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/dataSource.xml",
                "deploy/sessionFactory.xml",
                "deploy/transaction.xml",
                "deploy/support.xml",
                "deploy/core.xml",
                "deploy/org.helianto.core.xml"
                };
    }
    
    /*
     * Hook to persist one <code>Credential</code>.
     */  
    protected Credential writeCredential() {
        Credential credential = CredentialTestSupport.createCredential();
        credentialDao.persistCredential(credential);
        credentialDao.flush();
        credentialDao.clear();
        return credential;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneCredential() {
        Credential credential = writeCredential();

        assertEquals(credential,  credentialDao.findCredentialByNaturalId(credential.getIdentity()));
    }
    
    /**
     * Find by principal.
     */  
    public void testFindOneCredentialByPrincipal() {
        Credential credential = writeCredential();

        assertEquals(credential,  credentialDao.findCredentialByPrincipal(credential.getIdentity().getPrincipal()));
    }
    
    /*
     * Hook to persist a <code>Credential</code> list.
     */  
    protected List<Credential> writeCredentialList() {
        int credentialListSize = 10;
        List<Credential> credentialList = CredentialTestSupport.createCredentialList(credentialListSize);
        assertEquals(credentialListSize, credentialList.size());
        for (Credential credential: credentialList) {
            credentialDao.persistCredential(credential);
        }
        credentialDao.flush();
        credentialDao.clear();
        return credentialList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListCredential() {
        List<Credential> credentialList = writeCredentialList();

        Credential credential = credentialList.get((int) (Math.random()*credentialList.size()));
        assertEquals(credential,  credentialDao.findCredentialByNaturalId(credential.getIdentity()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testCredentialDuplicate() {
        Credential credential =  writeCredential();
        Credential credentialCopy = CredentialTestSupport.createCredential(credential.getIdentity());

        try {
            credentialDao.mergeCredential(credentialCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveCredential() {
        List<Credential> credentialList = writeCredentialList();
        Credential credential = credentialList.get((int) (Math.random()*credentialList.size()));
        credentialDao.removeCredential(credential);

        assertNull(credentialDao.findCredentialByNaturalId(credential.getIdentity()));
    }

    //- setters

    public void setCredentialDao(CredentialDao credentialDao) {
        this.credentialDao = credentialDao;
    }
    
}

