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

package org.helianto.process;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import org.helianto.core.User;
import org.helianto.core.filter.UserBackedFilter;

/**
 * Filter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessFilter implements Serializable , UserBackedFilter {

    private static final long serialVersionUID = 1L;
    private User user;
    private long internalNumber;
    private String docNameLike;
    private Collection<Process> exclusions;
    
    /**
     * Default constructor
     */
    public ProcessFilter() {
    }
    
    /**
     * Factory method.
     * @param user
     */
    public static ProcessFilter processFilterFactory(User user) {
    	ProcessFilter process = new ProcessFilter();
    	process.setUser(user);
    	return process;
    }
    
    /**
     * Force filter to standards.
     */
    public void reset() {
    	setInternalNumber(0);
    	setDocNameLike("");
    	setExclusions(new HashSet<Process>(0));
    }
    
    /**
     * User to meet <code>UserBackedFilter</code> interface requirements.
     */
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Internal number criterion field.
     */
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

    /**
     * Processes name criterion field.
     */
	public String getDocNameLike() {
		return docNameLike;
	}
	public void setDocNameLike(String docNameLike) {
		this.docNameLike = docNameLike;
	}

    /**
     * Collection of processes to exclude on result set.
     */
    public Collection<Process> getExclusions() {
        return exclusions;
    }
    public void setExclusions(Collection<Process> exclusions) {
        this.exclusions = exclusions;
    }

}