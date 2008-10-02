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

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentFilter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Document document;
	
	public ProcessDocumentFilter() {
	}
	
	public static ProcessDocumentFilter processDocumentFilterFactory(ProcessDocument processDocument) {
		ProcessDocumentFilter processDocumentFilter = new ProcessDocumentFilter();
		processDocumentFilter.setDocument(processDocument);
		return processDocumentFilter;
	}

	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
    /**
     * toString
     * @return String
     */
	@Override
	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        builder.append("document").append("='").append(getDocument()).append("' ");
        builder.append("]");
        return builder.toString();
    }

}
