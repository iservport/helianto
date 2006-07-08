/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core.security;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Identity;
import org.helianto.core.Role;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.creation.UserCreator;
import org.helianto.core.creation.UserCreatorImpl;
import org.helianto.core.dao.UserDao;
import org.helianto.core.hibernate.UserDaoImpl;
import org.helianto.core.hibernate.UserDaoImplTests;
import org.springframework.dao.DataRetrievalFailureException;


public class UserDetailsServiceImplTests extends TestCase {
    
    // class under test
    private UserDetailsServiceImpl userDetailsService;
    // collaborators
    private UserDao userDao;
    
    //
    //
    //  TODO * * * W R I T E   T H I S   A G A I N * * *
    //
    //

    public void testGuessUserSuccess() {
//        //prepare
//        List<User> userList = UserDaoImplTests.createUsers(3, 3);
//        assertEquals(9, userList.size());
//        User choice = userList.get(0);
//        Identity identity = choice.getIdentity();
//        String principal = identity.getPrincipal();
//        // init mock
//        // Identity identity = userDao.findIdentityByPrincipal(principal);
//        expect(userDao.findIdentityByPrincipal(principal)).andReturn(choice.getIdentity());
//        replay(userDao);
//        // test
//        User user = userDetailsService.guessUser(principal);
//        assertTrue(userList.contains(user));
//        assertSame(choice.getIdentity(), user.getIdentity());
//        
    }
    
    public void testGuessUserNoCredential() {
//        
//        String principal = "CRED2";
//        try {
//            userDetailsService.guessUser(principal);
//            fail();
//        } catch (UsernameNotFoundException e) {
//            //ok
//        }
    }
    
    public void testGuessUserNoUser() {
//        
//        String principal = "CRED1";
//        try {
//            userDetailsService.guessUser(principal);
//            fail();
//        } catch (UsernameNotFoundException e) {
//            //ok
//        }
    }
    
    public void testGuessUserAuto() {
//        
//        String principal = "CRED1";
//        auto = new User();
//        User user = userDetailsService.guessUser(principal);
//        assertSame(auto, user);
    }
    

    public void testLoadUserByUsernameNoUserLogYet() {
//        
//        List<User> userList = UserDaoImplTests.createUsers(3, 3);
//        User user = userList.get(0);
//        Date loginDate1 = new Date();
//        lastLogin = loginDate1;
//        
//        // test case 1: guess user and create log
//        
//        UserDetails userDetails = userDetailsService.loadUserByUsername("CRED1");
//        assertEquals(userDetails.getUsername(), "CRED1");
//        assertEquals("PASSWORD1", userDetails.getPassword());
//        
//        Set<Role> roles = user.getRoles();
//        GrantedAuthority[] authorities = userDetails.getAuthorities();
//        // all authorities are roles
//        for (GrantedAuthority ga: authorities) {
//            if (logger.isDebugEnabled()) {
//                logger.debug("GrantedAuthority is "+ga);
//            }
//            boolean matchAuthorityToRole = false;
//            for (Role r: roles) {
//                if (logger.isDebugEnabled()) {
//                    logger.debug("Comparing to Role "+r+", "+r.getRoleName());
//                }
//                if (ga.getAuthority().compareTo(r.getRoleName())==0) {
//                    if (logger.isDebugEnabled()) {
//                        logger.debug("Equals");
//                    }
//                    matchAuthorityToRole = true;
//                } 
//            }
//            if (!matchAuthorityToRole) {
//                fail();
//            }
//        }
//        // all roles are authorities
//        for (Role r: roles) {
//            if (logger.isDebugEnabled()) {
//                logger.debug("Role is "+r+", "+r.getRoleName());
//            }
//            boolean matchRoleToAuthority = false;
//            for (GrantedAuthority ga: authorities) {
//                if (logger.isDebugEnabled()) {
//                    logger.debug("Comparing to GrantedAuthority "+ga);
//                }
//                if (r.getRoleName().compareTo(ga.getAuthority())==0) {
//                    matchRoleToAuthority = true;
//                }
//            }
//            if (!matchRoleToAuthority) {
//                fail();
//            }
//        }
//        
//        assertEquals(1, userLogList.size());
//        UserLog userLog = userLogList.get(0);
//        assertSame(user, userLog.getUser());
//        
    }
    
    //FIXME
//    public void testLoadUserByUsernameFromUserLog() {
//
//        userList = createUsers(testIdentity, 1);
//        User user = userList.get(0);
//        Date loginDate1 = new Date();
//        lastLogin = loginDate1;
//        userDao.createAndPersistUserLog(user);
//        assertEquals(1, userLogList.size());
//        
//        PublicUserDetails publicUserDetails = (PublicUserDetails) userDetailsService.loadUserByUsername("CRED1");
//        assertEquals(2, userLogList.size());
//        assertSame(publicUserDetails.getLastLogin(), loginDate1);
//        assertSame(publicUserDetails.getCurrentEntity(), user.getEntity());
//        assertSame(publicUserDetails.getPersonalData(), user.getIdentity().getPersonalData());
//        assertEquals(0, publicUserDetails.getEntities().size());
//                
//    }
//    
//    public void testLoadUserByUsernameManyUsers() {
//
//        userList = createUsers(testIdentity, 3);
//        
//        PublicUserDetails publicUserDetails = (PublicUserDetails) userDetailsService.loadUserByUsername("CRED1");
//        assertEquals(2, publicUserDetails.getEntities().size());
//        
//    }
//    
    public void testLoadUserByUsernameFailure() {
//        
//        try {
//            userDetailsService.loadUserByUsername("");
//            fail();
//        } catch (DataRetrievalFailureException e) {
//            // ok
//        }
//        
    }
    
    // setup
    
    public void setUp() {
        UserCreator userCreator = new UserCreatorImpl();
        testIdentity = userCreator.identityFactory("CRED1", "");
        userDao = createMock(UserDao.class);
        userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setUserDao(new UserDaoStub());
    }
    
    public void tearDown() {
        reset(userDao);
    }
    
//    final void addUserToList(Identity identity, Entity entity) {
//        User user = new User();
//        user.setEntity(entity);
//        user.setIdentity(identity);
//        identity.getUsers().add(user);
//        createRoles(user, 3);
//        userList.add(user);
//        if (logger.isDebugEnabled()) {
//            logger.debug("User created "+user);
//        }
//    }
//    
    final void createRoles(User user, int size) {
        Role[] r = new Role[size];
        for (int i=0;i<size;i++) {
            r[i] = new Role();
            r[i].setUser(user);
            user.getRoles().add(r[i]);
            r[i].setRoleName("ROLE_"+user.getIdentity().getPrincipal()+"_"+i);
            if (logger.isDebugEnabled()) {
                logger.debug("Role created "+r[i]+"(" +r[i].getRoleName()+") for user "+user);
            }
        }
    }
    
    // private members
    
    private final Log logger = LogFactory.getLog(getClass());
    private Identity testIdentity;
    private List<UserLog> userLogList;
    private UserLog lastUserLog = null;
    private Date lastLogin = new Date();
    private User auto = null;
    
    // inner stub class
    
    public class UserDaoStub extends UserDaoImpl {
        
        // stub members
        
        
        @Override
        public Identity findIdentityByPrincipal(String principal) {
            if (principal.compareTo(testIdentity.getPrincipal())==0) {
                return testIdentity;
            }
            return null;
        }

        public User autoCreateAndPersistUser(String principal) { return auto; }

        public UserLog createAndPersistUserLog(User user) {
            if (userLogList==null) {
                userLogList = new ArrayList<UserLog>();
            }
            lastUserLog = new UserLog();
            lastUserLog.setUser(user);
            lastUserLog.setLastLogin(lastLogin);
            userLogList.add(lastUserLog);
            return lastUserLog;
        }

        public UserLog findLastUserLog(String principal) {
            return lastUserLog;
        }

    }

}
