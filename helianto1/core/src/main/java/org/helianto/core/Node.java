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
import java.util.List;

/**
 * Interface to represent a tree node required by a <code>TreeBuilder</code>. 
 *
 * <p>
 * Any tree assembled with nodes implementing this interface are
 * simple ordered lists. Implementing classes must assure
 * appropriate ordering using getLevel() and getSequence().
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface Node extends Comparable<Node>, Serializable {
	
	/**
	 * Id associated to the node.
	 */
	public long getId();
	
	/**
	 * Text displayed next to the node.
	 */
	public String getCaption();
	
	/**
	 * Content wrapped by the node.
	 */
	public Object getContent();
	
	/**
	 * Level used to provide ordering and identation.
	 */
	public int getLevel();
	
	/**
	 * Sequence used to provide ordering within a level.
	 */
	public int getSequence();
	
	/**
	 * Exposed icon.
	 */
	public String getIcon();
	
	/**
	 * True if node is expanded.
	 */
	public boolean isExpanded();
	
	/**
	 * True if node is editable.
	 */
	public boolean isEditable();
	
	/**
	 * Child list.
	 */
	public List<Node> getChildList();

}
