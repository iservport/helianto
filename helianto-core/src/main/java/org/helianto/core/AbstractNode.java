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


/**
 * Base class to the <code>Node</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractNode<T> implements Node {

	private static final long serialVersionUID = 1L;
	private long id;
	private T content;
	private int level = 0;
	private int sequence = 0;
	private boolean expanded = false;
	private boolean editable = true;
	private String icon = "";
	
	/**
	 * Constructor.
	 */
	public AbstractNode(long id, T content,  int level, int sequence, boolean editable) {
		this.id = id;
		this.content = content;
		this.level = level;
		this.sequence = sequence;
		this.editable = editable;
	}

	/**
	 * Constructor.
	 */
	public AbstractNode(long id, T payLoad,  int level, int sequence) {
		this(id, payLoad,  level, sequence, true);
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Exposed content.
	 */
	public T getContent() {
		return content;
	}

	/**
	 * Level used to provide ordering and identation.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sequence used to provide ordering within a level.
	 */
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
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

	/**
	 * True if node is editable.
	 */
	public boolean isEditable() {
		return editable;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int compareTo(Node nextNode) {
		int levelPosition = getLevel() - nextNode.getLevel();
		if (levelPosition==0) {
			return getSequence() - nextNode.getSequence();
		}
		return levelPosition;
	}

	/**
	 * equals
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		if ((this == other)) return true;
		if ((other == null)) return false;
		if (!(other instanceof AbstractNode)) return false;
		AbstractNode<T> castOther = (AbstractNode<T>) other;
		
		return (
				((this.getContent() == castOther.getContent()) 
				|| 
				(
				 this.getContent() != null
				 && castOther.getContent() != null 
				 && this.getContent().equals(castOther.getContent())
				))
				&& (this.getLevel()==castOther.getLevel())
			   );
	}

	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
        result = 37 * result + this.getLevel();
		return result;
	}

}
