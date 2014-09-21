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


package org.helianto.core.internal;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.helianto.core.Association;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Base class to generic associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractAssociation<P, C> 
	implements Association<P, C>, Serializable, Comparable<AbstractAssociation<P,C>> {
	
	private static final long serialVersionUID = 1L;
	
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    @Version
	private int version;
	
	private int sequence;
	
    @JsonBackReference("parent")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parentId", nullable=true)
	protected P parent;
	
    @JsonBackReference("child")
    @ManyToOne
    @JoinColumn(name="childId", nullable=true)
	protected C child;
	
	/**
	 * Constructor
	 */
	public AbstractAssociation() { }

	/**
	 * Auto generated primary key.
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    /**
     * Version.
     */
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

	/**
	 * Parent.
	 */
	public P getParent() {
		return parent;
	}
	public void setParent(P parent) {
		this.parent = parent;
	}

	/**
	 * Child.
	 */
	public C getChild() {
		return child;
	}
	public void setChild(C child) {
		this.child = child;
	}
	
	/**
	 * Implements <code>Comparable</code> interface.
	 * 
	 * <p>
	 * If parents are equal, delegate to {@link #compareChild(AbstractAssociation)},
	 * otherwise delegate to {@link #compareParent(AbstractAssociation)}.
	 * </p>
	 */
	public int compareTo(AbstractAssociation<P,C> other) {
    	if (this.parent.equals(other.parent)) {
    		return compareChild(other);
    	}
		return compareParent(other);
	}
    
    /**
     * Default implementation ignores child and order by sequence.
     * 
     * @param other
     */
	protected int compareChild(AbstractAssociation<P,C> other) {
    	return this.sequence - other.getSequence();
    }

    /**
     * Default implementation ignores child and order by sequence.
     * 
     * @param other
     */
 	protected int compareParent(AbstractAssociation<P,C> other) {
    	return this.sequence - other.getSequence();
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
		AbstractAssociation<P,C> castOther = (AbstractAssociation<P,C>) other;

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
