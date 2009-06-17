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


package org.helianto.core;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base class to generic associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractAssociation<P, C> implements Association<P, C>, Serializable, NaturalKeyInfo {
	
    /**
     * Internal factory method.
     * 
     * @param parent
     * @param child
     */
    protected static <P, C> AbstractAssociation<P, C> associationFactory(Class<? extends AbstractAssociation<P, C>> clazz, P parent, C child) {
    	AbstractAssociation<P, C> association;
		try {
			association = clazz.newInstance();
	    	association.setChild(child);
	    	association.setParent(parent);
	        return association;
		} catch (Exception e) {
			throw new RuntimeException("Unable to create association", e);
		}
    }
    
	private static final long serialVersionUID = 1L;
	private int id;
	private int version;
	private int sequence;
	protected P parent;
	protected C child;
	
	/**
	 * Constructor
	 */
	public AbstractAssociation() { }

	/**
	 * Auto generated primary key.
	 */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    /**
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

	/**
	 * Sequence.
	 */
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public void setParent(P parent) {
		this.parent = parent;
	}

	public void setChild(C child) {
		this.child = child;
	}
	
    /**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("parent").append("='").append(getParent()).append("' ");
		buffer.append("child").append("='").append(getChild()).append("' ");
		buffer.append("]");

		return buffer.toString();
	}

	/**
	 * equals
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof AbstractAssociation)) return false;
		AbstractAssociation castOther = (AbstractAssociation) other;

		return ((this.getParent() == castOther.getParent()) || (this
				.getParent() != null
				&& castOther.getParent() != null && this.getParent().equals(
				castOther.getParent())))
				&& ((this.getChild() == castOther.getChild()) || (this
						.getChild() != null
						&& castOther.getChild() != null && this.getChild()
						.equals(castOther.getChild())));
	}
	   
	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		int result = 17;
        result = 37 * result + (int) this.getSequence();
		result = 37 * result
				+ (getChild() == null ? 0 : this.getChild().hashCode());
		return result;
	}   

}
