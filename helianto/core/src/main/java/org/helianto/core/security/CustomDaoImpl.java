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

import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.User;
import org.helianto.core.UserLog;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Custom implementation for the {@link org.acegisecurity.userdetails.UserDetailsService}
 * interface.
 * 
 * <p>
 * The method {@link #loadUserByUsername(String)} returns a 
 * <code>Credential</code> wrapped in an <code>UserAdpater</code>
 * instance.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class CustomDaoImpl implements UserDetailsService {
    
    private final Log logger = LogFactory.getLog(CustomDaoImpl.class);
    
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         Username "+username);
        }
        List list = null;
        HibernateTemplate hibernateTemplate = null;
        try {
            hibernateTemplate = new HibernateTemplate(sessionFactory, true);
            list = (List) hibernateTemplate.find(CREDENTIAL_QUERY, username);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         thrown from "+e.toString());
            }
            throw new DataRetrievalFailureException("Username: "+username);
        }
        if (list.size()==1) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         One credential loaded by username");
            }
            List userLogList = (List) hibernateTemplate.find(USERLOG_QUERY, username);
            User user = null;
            if (userLogList.size()==0) {
            	// TODO guess user
            } else if (userLogList.size()==1) {
            	// TODO take this
                user = ((UserLog) userLogList.get(0)).getUser();
            } else {
            	// TODO take last
            }
            return new UserAdapter(user);
        } 
        throw new UsernameNotFoundException("Username: "+username);
    }

    static final String CREDENTIAL_QUERY = "from Credential cred " +
    	"where cred.principal = ?";

    static final String USERLOG_QUERY = "from UserLog userLog " +
    	"where userLog.user.credential.principal = ?";

}
