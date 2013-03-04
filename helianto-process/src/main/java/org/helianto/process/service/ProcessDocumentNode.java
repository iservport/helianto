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

import java.io.Serializable;
import java.util.List;

import org.helianto.process.domain.ProcessDocument;

/**
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentNode implements Serializable, Comparable<ProcessDocument> {

	private static final long serialVersionUID = 1L;
	private ProcessDocument processDocument;
	private int level = 0;
	private List<ProcessDocument> processDocumentList;
	private boolean expanded = false;

	/**
	 * Constructor.
	 */
	public ProcessDocumentNode() {
	}

	/**
	 * Exposed process document.
	 */
	public ProcessDocument getProcessDocument() {
		return processDocument;
	}
	public void setProcessDocument(ProcessDocument processDocument) {
		this.processDocument = processDocument;
	}

	/**
	 * Exposed level.
	 */
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Exposed level.
	 */
	public List<ProcessDocument> getProcessDocumentList() {
		return processDocumentList;
	}
	public void setProcessDocumentList(List<ProcessDocument> processDocumentList) {
		this.processDocumentList = processDocumentList;
	}

	/**
	 * True if node is expanded.
	 */
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public int compareTo(ProcessDocument o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * equals
	 */
	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof ProcessDocumentNode)) return false;
		ProcessDocumentNode castOther = (ProcessDocumentNode) other;

		return ((this.getProcessDocument() == castOther.getProcessDocument()) 
				|| (this.getProcessDocument() != null
				&& castOther.getProcessDocument() != null && this.getProcessDocument().equals(
				   castOther.getProcessDocument()))
				&& (this.getLevel()==castOther.getLevel()));
	}

	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result
				+ (getProcessDocument() == null ? 0 : this.getProcessDocument().hashCode());
        result = 37 * result + this.getLevel();
		return result;
	}

}
