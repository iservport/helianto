//Created on 16/08/2005
package org.helianto.core.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Credential;
import org.helianto.core.dao.GenericDao;
import org.helianto.core.hibernate.CollaboratorTestFactory;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
import org.springframework.dao.DataRetrievalFailureException;

import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.dao.UsernameNotFoundException;

public class CustomDaoImplTests extends MockObjectTestCase {

    private final Log logger = LogFactory.getLog(CustomDaoImplTests.class);

    private Mock sessionFactory;

    CustomDaoImpl customDao = new CustomDaoImpl();

    private CollaboratorTestFactory factory = new CollaboratorTestFactory();

    public void setUp() {
        sessionFactory = new Mock(SessionFactory.class);
        customDao.setSessionFactory((SessionFactory) sessionFactory.proxy());
    }

    @SuppressWarnings("unchecked")
    public void testLoadUserByUsername_Success() {
        logger.info("test success");

        List<Credential> list = new ArrayList();
        Credential cred = (Credential) factory
                .getCollaborator(Credential.class);
        list.add(cred);
        String username = cred.getPrincipal();
        
        Query query = null;

        sessionFactory.expects(once()).method("createQuery").with(
                stringContains(CustomDaoImpl.CREDENTIAL_QUERY)).will(
                returnValue(list));

        UserDetails ud = customDao.loadUserByUsername(username);
        assertEquals(username, ud.getUsername());
        assertEquals(cred.getPassword(), ud.getPassword());
        
        //cast to user adapter
        
        UserAdapter ua = (UserAdapter) ud;
        assertTrue(ua.isAccountNonExpired());
        assertTrue(ua.isAccountNonLocked());
        assertTrue(ua.isCredentialsNonExpired());
        assertTrue(ua.isEnabled());
        assertSame(cred.getPersonalData(), ua.getPersonalData());

    }

    @SuppressWarnings("unchecked")
    public void testLoadUserByUsername_NotFound() {
        logger.info("test credential not found");

        String username = "test";

//        genericDao.expects(once()).method("find").with(
//                stringContains(CustomDaoImpl.query), same(username)).will(
//                returnValue(new ArrayList()));

        try {
            customDao.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            // Expected
            return;
        }
        fail("Expected exception");

    }

    @SuppressWarnings("unchecked")
    public void testLoadUserByUsername_Found_more_than_one() {
        logger.info("test found duplicate");

        List<Credential> list = new ArrayList();
        Credential cred = (Credential) factory
                .getCollaborator(Credential.class);
        list.add(cred);
        list.add(cred);
        String username = cred.getPrincipal();

//        genericDao.expects(once()).method("find").with(
//                stringContains(CustomDaoImpl.query), same(username)).will(
//                returnValue(list));

        try {
            customDao.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            // Expected
            return;
        }
        fail("Expected exception");
    }

    @SuppressWarnings("unchecked")
    public void testLoadUserByUsername_Exception() {
        logger.info("test exception");

        String username = "test";

//        genericDao.expects(once()).method("find")
//           .with(stringContains(CustomDaoImpl.query), same(username))
//           .will(throwException(new DataRetrievalFailureException("test")));

        try {
            customDao.loadUserByUsername(username);
        } catch (DataRetrievalFailureException e) {
            // Expected
            return;
        }
        fail("Expected exception");

    }

}
