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

package org.helianto.web.controller;

import java.util.List;

import org.helianto.core.User;
import org.helianto.core.UserFilter;
import org.helianto.core.UserGroup;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.service.UserMgr;
import org.springframework.stereotype.Component;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * A controller to handle <code>User</code> selection requests.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("userSelectionAction")
public class UserSelectionAction extends MultiAction {
    
    /** 
     * Action decision state to control if the selection is hidden or shown.
     * <p>
     * If the selection result is the same as the current, signals
     * the view to hide the selection if is being shown, or to 
     * show the selection if hidden. If the selection result is 
     * not the same as the current, the the selection is not switched.
     * </p>
     */
    public Event switchSelection(RequestContext context) {
        ParameterMap parameters = context.getRequestParameters();
        if (!parameters.contains("userEntity")) {
            // nothing to do, the user has no other associated entities
            return result("doNothing");
        }
        PublicUserDetails secureUser = UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
        List<UserGroup> candidates = userMgr.findUsers(secureUser.getUser().getIdentity());
        String userEntity = parameters.get("userEntity");
        if (secureUser.getUser().getEntity().getId()==Long.parseLong(userEntity)) {
            if (candidates.size()==1) {
                // nothing to do, the user has no other associated entities
                return result("doNothing");
            }
            // retrieve the selection view state
            boolean showUserSelection = false;
            if (context.getConversationScope().contains("showUserSelection")) {
                showUserSelection = context.getConversationScope().getBoolean("showUserSelection");
                if (logger.isDebugEnabled()) {
                    logger.debug("Current state of showUserSelection: "+showUserSelection);
                }
            }
            // switch the selection view state
            showUserSelection = !showUserSelection;
            context.getConversationScope().put("showUserSelection", showUserSelection);
            if (logger.isDebugEnabled()) {
                logger.debug("New state of showUserSelection: "+showUserSelection);
            }
            return yes();
        }
        else {
            return no();
        }
    }

    /**
     * Selects the <code>User</code>.
     */
    public Event selectUser(RequestContext context) {
        // TODO refactor to move rules to service layer
        ParameterMap parameters = context.getRequestParameters();
        PublicUserDetails secureUser = UserDetailsAdapter.retrievePublicUserDetailsFromSecurityContext();
        long userEntity = parameters.getLong("userEntity");
        UserFilter userFilter = new UserFilter(secureUser.getUser().getIdentity(), true);
        List<UserGroup> userList = userMgr.findUsers(userFilter);
        for (UserGroup user: userList) {
            if (user instanceof User && user.getEntity().getId()==userEntity) {
                secureUser.setUser((User) user);
                if (logger.isDebugEnabled()) {
                    logger.debug("New user is "+user);
                }
//                userMgr.storeUserLog((User) user, new Date());
                break;
            }
        }
        context.getConversationScope().put("showUserSelection", false);
        return success();
    }
    
    //- collaborators

    private UserMgr userMgr;
    
    @javax.annotation.Resource
    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

}
