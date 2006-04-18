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
import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Role;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.helianto.core.dao.UserDao;

public class UserDetailsServiceImplTests extends TestCase {
    
    public void testGuessUserSuccess() {
        
        String principal = "CRED1";
        userList = createUsers(testCredential, 10);
        User user = userDetailsService.guessUser(principal);
        assertTrue(userList.contains(user));
        
    }
    
    public void testGuessUserNoCredential() {
        
        String principal = "CRED2";
        try {
            userDetailsService.guessUser(principal);
            fail();
        } catch (UsernameNotFoundException e) {
            //ok
        }
    }
    
    public void testGuessUserNoUser() {
        
        String principal = "CRED1";
        try {
            userDetailsService.guessUser(principal);
            fail();
        } catch (UsernameNotFoundException e) {
            //ok
        }
    }
    
    public void testGuessUserAuto() {
        
        String principal = "CRED1";
        auto = new User();
        User user = userDetailsService.guessUser(principal);
        assertSame(auto, user);
    }
    
    public void testLoadUserByUsernameSuccess() {
        
        // prepare first login
        
        userList = createUsers(testCredential, 1);
        User user = userList.get(0);
        Date loginDate1 = new Date();
        lastLogin = loginDate1;
        
        // test case 1: guess user and create log
        
        UserDetails userDetails = userDetailsService.loadUserByUsername("CRED1");
        assertEquals(userDetails.getUsername(), "CRED1");
        Set<Role> roles = user.getRoles();
        GrantedAuthority[] authorities = userDetails.getAuthorities();
        // all authorities are roles
        for (GrantedAuthority ga: authorities) {
            if (logger.isDebugEnabled()) {
                logger.debug("GrantedAuthority is "+ga);
            }
            boolean matchAuthorityToRole = false;
            for (Role r: roles) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Comparing to Role "+r+", "+r.getRoleName());
                }
                if (ga.getAuthority().compareTo(r.getRoleName())==0) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Equals");
                    }
                    matchAuthorityToRole = true;
                } 
            }
            if (!matchAuthorityToRole) {
                fail();
            }
        }
        // all roles are authorities
        for (Role r: roles) {
            if (logger.isDebugEnabled()) {
                logger.debug("Role is "+r+", "+r.getRoleName());
            }
            boolean matchRoleToAuthority = false;
            for (GrantedAuthority ga: authorities) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Comparing to GrantedAuthority "+ga);
                }
                if (r.getRoleName().compareTo(ga.getAuthority())==0) {
                    matchRoleToAuthority = true;
                }
            }
            if (!matchRoleToAuthority) {
                fail();
            }
        }
        assertEquals("PASSWORD1", userDetails.getPassword());
        
        assertEquals(1, userLogList.size());
        UserLog userLog = userLogList.get(0);
        assertEquals(user, userLog.getUser());
        
        // cast UserDetails to PublicUserDetails
        
        PublicUserDetails publicUserDetails = (PublicUserDetails) userDetails;
        assertSame(publicUserDetails.getLastLogin(), loginDate1);
        assertSame(publicUserDetails.getCurrentEntity(), user.getEntity());
        assertSame(publicUserDetails.getPersonalData(), user.getCredential().getPersonalData());
        
        // prepare UserDaoStub for second invocation
        
        Date loginDate2 = new Date();
        lastLogin = loginDate2;
        
        // test case 2: second invocation
        
        PublicUserDetails publicUserDetails2 = (PublicUserDetails) userDetailsService.loadUserByUsername("CRED1");
        assertEquals(2, userLogList.size());
        assertSame(publicUserDetails2.getLastLogin(), loginDate2);
        
    }
    
    // setup
    
    public void setUp() {
        testCredential = new Credential();
        testCredential.setPrincipal("CRED1");
        testCredential.setPassword("PASSWORD1");
        userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setUserDao(new UserDaoStub());
    }
    
    final List<User> createUsers(Credential credential, int size) {
        List<User> userList = new ArrayList<User>();
        Entity entity = new Entity();
        User u;
        for (int i=0;i<size;i++) {
            u = new User();
            u.setEntity(entity);
            u.setCredential(credential);
            credential.getUsers().add(u);
            createRoles(u, 3);
            userList.add(u);
            if (logger.isDebugEnabled()) {
                logger.debug("User created "+u);
            }
        }
        return userList;
    }
    
    final void createRoles(User user, int size) {
        Role[] r = new Role[size];
        for (int i=0;i<size;i++) {
            r[i] = new Role();
            r[i].setUser(user);
            user.getRoles().add(r[i]);
            r[i].setRoleName("ROLE_"+user.getCredential().getPrincipal()+"_"+i);
            if (logger.isDebugEnabled()) {
                logger.debug("Role created "+r[i]+"(" +r[i].getRoleName()+") for user "+user);
            }
        }
    }
    
    // private members
    
    private final Log logger = LogFactory.getLog(getClass());
    private Credential testCredential;
    private List<User> userList;
    private List<UserLog> userLogList;
    private Date lastLogin = new Date();
    private User auto = null;
    private UserDetailsServiceImpl userDetailsService;
    
    // inner stub class
    
    public class UserDaoStub implements UserDao {
        
        // stub members
        
        public Credential findCredentialByPrincipal(String principal) {
            if (principal.compareTo(testCredential.getPrincipal())==0) {
                return testCredential;
            }
            return null;
        }

        public User autoCreateAndPersistUser(String principal) { return auto; }

        public UserLog createAndPersistUserLog(User user) {
            if (userLogList==null) {
                userLogList = new ArrayList<UserLog>();
            }
            UserLog userLog = new UserLog();
            userLog.setUser(user);
            userLog.setLastLogin(lastLogin);
            userLogList.add(userLog);
            return userLog;
        }

        // not used
        
        public void persistUser(User user) { }

        public void removeUser(User user) { }

        public User findUserByEntityAliasAndPrincipal(String alias, String principal) { return null; }

        public List<User> findUserByEntity(Entity entity) { return null; }

        public List<UserLog> findUserLogByUser(User user) { return null; }

        public UserLog findLastUserLog(String principal) { return null; }

        public void persistCredential(Credential credential) { }

        public void removeCredential(Credential credential) { }

        public int countCredentialByPrincipal(String principal) { return 0; }
        
    }

}
