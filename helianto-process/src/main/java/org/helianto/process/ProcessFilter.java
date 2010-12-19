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

import java.util.Collection;
import java.util.HashSet;

import org.helianto.core.filter.classic.AbstractUserBackedCriteriaFilter;

/**
 * Filter to <code>User</code>.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated use ProcessDocumentFilter
 */
public abstract class ProcessFilter extends AbstractUserBackedCriteriaFilter {

    private static final long serialVersionUID = 1L;
    private long internalNumber;
    private String docNameLike = "";
    private Collection<Process> exclusions;
    
    /**
     * Default constructor
     */
    public ProcessFilter() {
    }
    
    /**
     * Force filter to standards.
     */
    public void reset() {
    	setInternalNumber(0);
    	setDocNameLike("");
    	setExclusions(new HashSet<Process>(0));
    }
    
	public String getObjectAlias() {
		return "process";
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
