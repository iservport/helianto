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

package org.helianto.core.webflow.access;

import java.util.List;

import org.helianto.core.User;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserAdapter;
import org.helianto.core.service.CoreMgr;
import org.springframework.webflow.Event;
import org.springframework.webflow.RequestContext;
import org.springframework.webflow.action.MultiAction;

/**
 * Multi action to the access flow.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class AccessActionImpl extends MultiAction implements AccessAction {
    
    public CoreMgr coreMgr;
    
    public void setCoreMgr(CoreMgr coreMgr) {
        this.coreMgr = coreMgr;
    }

    public Event findEntity(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n\n         ENTERING STATE.");
        }
        PublicUserDetails pud = UserAdapter.retrievePublicUserDetailsFromSecurityContext();
        if (pud.getEntity()!=null) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Entity found: "+pud.getEntity().getAlias());
            }
            return result("one");
        }
        List<User> list = coreMgr.findUserByCredentialId(pud.getCredentialId());
        if (list==null) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Zero entities found");
            }
            return result("none");
        } else if (list.size()==0) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Zero entities found");
            }
            return result("none");
        } else if (list.size()==1) {
            pud.setUser(list.get(0));
            if (logger.isDebugEnabled()) {
                logger.debug("\n         User set, entity found: "+pud.getEntity().getAlias());
            }
            return result("one");
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Many entities found");
            }
            return result("many");
        }
    }

    public Event findService(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n\n         ENTERING STATE.");
        }
        if (false) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Zero services found");
            }
            return result("none");
        } else if (true) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Service found: ...");
            }
            return result("one");
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Many services found");
            }
            return result("many");
        }
    }

}
