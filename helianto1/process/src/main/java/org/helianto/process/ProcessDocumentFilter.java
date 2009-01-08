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

import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentFilter extends AbstractUserBackedCriteriaFilter {

    private static final long serialVersionUID = 1L;
    private String docCode = "";
	private long internalNumber;
	private Document document;
	private String docNameLike = "";
	private char inheritanceType = ' ';
	private char priority = '0';
	private Class<? extends ProcessDocument> clazz = ProcessDocument.class;
    private Collection<? extends ProcessDocument> exclusions;
	
	
	public ProcessDocumentFilter() {
	}
	
	/**
	 * Create the filter for a given type.
	 * 
	 * @param user
	 * @param clazz
	 */
	public static ProcessDocumentFilter processDocumentFilterFactory(User user, Class<? extends ProcessDocument> clazz) {
		ProcessDocumentFilter processDocumentFilter = new ProcessDocumentFilter();
		processDocumentFilter.setUser(user);
		processDocumentFilter.setClazz(clazz);
		return processDocumentFilter;
	}

	/**
	 * Create the filter for a hierarchy.
	 * 
	 * @param user
	 * @param root
	 */
	public static ProcessDocumentFilter processDocumentFilterFactory(User user, ProcessDocument root) {
		ProcessDocumentFilter processDocumentFilter = ProcessDocumentFilter.processDocumentFilterFactory(user, root.getClass());
		processDocumentFilter.setDocument(root);
		return processDocumentFilter;
	}

	public void reset() {
		setDocCode("");
		setDocNameLike("");
	}

	/**
	 * <<Chain>> Process document code.
	 */
	public String getDocCode() {
		return docCode;
	}
	public ProcessDocumentFilter setDocCode(String docCode) {
		this.docCode = docCode;
		return this;
	}

	/**
	 * Process internal number.
	 */
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}
	
	/**
	 * Subclass
	 */
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	/**
	 * Subclass
	 */
	public Class<? extends ProcessDocument> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends ProcessDocument> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Discriminator
	 */
	public char getDiscriminator() {
		if (clazz.equals(ControlMethod.class)) {
			return 'S'; 
		}
		if (clazz.equals(Characteristic.class)) {
			return 'K'; 
		}
		if (clazz.equals(Operation.class)) {
			return 'O'; 
		}
		if (clazz.equals(Process.class)) {
			return 'P'; 
		}
		return ' ';
	}

	public void setDiscriminator(char discriminator) {
		if (discriminator=='S') {
			clazz = ControlMethod.class; 
		}
		if (discriminator=='K') {
			clazz = Characteristic.class; 
		}
		if (discriminator=='O') {
			clazz = Operation.class; 
		}
		if (discriminator=='P') {
			clazz = Process.class; 
		}
	}

	/**
	 * Process name like.
	 */
    public String getDocNameLike() {
		return docNameLike;
	}
	public void setDocNameLike(String docNameLike) {
		this.docNameLike = docNameLike;
	}

	/**
	 * Inheritance type.
	 */
	public char getInheritanceType() {
		return inheritanceType;
	}
	public void setInheritanceType(char inheritanceType) {
		this.inheritanceType = inheritanceType;
	}

	/**
	 * Priority
	 */
	public char getPriority() {
		return priority;
	}
	public void setPriority(char priority) {
		this.priority = priority;
	}

	/**
	 * Exclusions.
	 */
	public Collection<? extends ProcessDocument> getExclusions() {
		return exclusions;
	}
	public void setExclusions(Collection<? extends ProcessDocument> exclusions) {
		this.exclusions = exclusions;
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
