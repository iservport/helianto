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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.helianto.core.User;
import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.filter.PolymorphicFilter;
import org.helianto.document.AbstractDocument;
import org.helianto.partner.Partner;

/**
 * Process document filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentFilter extends AbstractUserBackedCriteriaFilter implements Serializable, PolymorphicFilter<ProcessDocument> {

	/**
	 * Factory method.
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
	 * Factory method.
	 * 
	 * @param user
	 * @param root
	 */
	public static ProcessDocumentFilter processDocumentFilterFactory(User user, ProcessDocument root) {
		ProcessDocumentFilter processDocumentFilter = ProcessDocumentFilter.processDocumentFilterFactory(user, root.getClass());
		processDocumentFilter.setDocument(root);
		return processDocumentFilter;
	}

    private static final long serialVersionUID = 1L;
	private Class<? extends ProcessDocument> clazz;
    private String docCode;
	private String docNameLike;
	private char inheritanceType;
	private char priority;
	private AbstractDocument document;
	private Partner partner;
    private Collection<? extends ProcessDocument> exclusions;
	
	
	public ProcessDocumentFilter() {
		super();
		setClazz(ProcessDocument.class);
		setDocCode("");
		setDocNameLike("");
		setInheritanceType(' ');
		setPriority(' ');
	}
	
	public void reset() {
		setDocCode("");
		setDocNameLike("");
	}
	
	public boolean isSelection() {
		return getDocCode().length()>0;
	}

	public String getObjectAlias() {
		return "processdocument";
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("docCode", getDocCode(), mainCriteriaBuilder);
	}

	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (!getClazz().equals(ProcessDocument.class)) {
	        logger.debug("Document class is: '{}'", getClazz());
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("docName", getDocNameLike(), mainCriteriaBuilder);
		appendEqualFilter("inheritanceType", getInheritanceType(), mainCriteriaBuilder);
		appendEqualFilter("priority", getPriority(), mainCriteriaBuilder);
		appendOrderBy("docCode", mainCriteriaBuilder);
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
	 * Subclass
	 */
	public AbstractDocument getDocument() {
		return document;
	}
	public void setDocument(AbstractDocument document) {
		this.document = document;
	}
	
	/**
	 * Partner
	 */
	public Partner getPartner() {
		return this.partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
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
	
	private static Logger logger = LoggerFactory.getLogger(ProcessDocument.class);

}
