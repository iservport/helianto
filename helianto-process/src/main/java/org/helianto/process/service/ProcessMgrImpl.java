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

package org.helianto.process.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.helianto.core.Node;
import org.helianto.core.SequenceMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.Filter;
import org.helianto.core.number.Sequenceable;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.document.domain.ProcessDocumentAssociation;
import org.helianto.document.filter.ProcessDocumentFilterAdapter;
import org.helianto.partner.service.PartnerMgrImpl;
import org.helianto.process.ProcessMgr;
import org.helianto.process.def.AssociationType;
import org.helianto.process.domain.Process;
import org.helianto.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iservport.production.domain.Operation;
import com.iservport.production.domain.Setup;

/**
 * Default <code>ProcessMgr</code> interface implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("processMgr")
public class ProcessMgrImpl 
	implements ProcessMgr {

	@Transactional
    public List<Node> prepareTree(ProcessDocument processDocument) {
    	ProcessDocument managedProcessDocument = processDocumentDao.merge(processDocument);
    	ProcessNode root = new ProcessNode(managedProcessDocument);
    	List<Node> processTree = sequenceMgr.prepareTree(root);
    	return processTree;
    }

	@Transactional(readOnly=true)
    public List<ProcessDocument> findProcessDocuments(Filter filter) {
        List<ProcessDocument> processDocumentList = (List<ProcessDocument>) processDocumentDao.find(filter);
        if (logger.isDebugEnabled() && processDocumentList.size()>0) {
            logger.debug("Found {} item(s)", processDocumentList.size());
        }
        if (filter instanceof ProcessDocumentFilterAdapter) {
        	ProcessDocumentFilterAdapter processDocumentFilter = (ProcessDocumentFilterAdapter) filter;
            if (processDocumentFilter.getExclusions()!=null && processDocumentFilter.getExclusions().size()>0) {
            	processDocumentList.removeAll(processDocumentFilter.getExclusions());
                if (logger.isDebugEnabled()) {
                    logger.debug("Removed {} item(s)", processDocumentFilter.getExclusions());
                }
            }
        }
        return processDocumentList ;
	}

	@Transactional
  	public ProcessDocument storeProcessDocument(ProcessDocument processDocument) {
  		processDocumentDao.saveOrUpdate(processDocument);
  		if (processDocument instanceof Sequenceable) {
  			sequenceMgr.validateInternalNumber((Sequenceable) processDocument);
  		}
  		processDocumentDao.flush();
		return processDocument;
	}

	/**
	 * Create a criteria string from a filter.
	 * 
	 * @param filter
	 * @param prefix
	 */
	protected String createProcessDocumentCriteriaAsString(ProcessDocumentFilterAdapter filter, String prefix) {
		return new StringBuilder()
			.append(prefix)
			.append(".parent.id=")
			.append(filter.getParentId())
			.append(" order by ")
			.append(prefix)
			.append(".sequence ")
			.toString();
	}
	
    public Process createProcess(Entity entity) {
        // TODO Auto-generated method stub
        return null;
    }
    
    // collaborators 

    private FilterDao<ProcessDocument> processDocumentDao;
    private SequenceMgr sequenceMgr;
    
    @javax.annotation.Resource(name="processDocumentDao")
    public void setProcessDocumentDao(FilterDao<ProcessDocument> processDocumentDao) {
        this.processDocumentDao = processDocumentDao;
    }

    @javax.annotation.Resource
    public void setSequenceMgr(SequenceMgr sequenceMgr) {
        this.sequenceMgr = sequenceMgr;
    }

    public static final Logger logger = LoggerFactory.getLogger(ProcessMgrImpl.class);

}
