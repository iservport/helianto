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

package org.helianto.controller;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * A simple LIFO target stack.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractTargetStack<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private LinkedList<T> internalStack;
	
	/**
	 * Default constructor.
	 */
	public AbstractTargetStack() {
		internalStack = new LinkedList<T>();
	}
	
	/**
	 * Get the stack.
	 */
	public LinkedList<T> getInternalStack() {
		return internalStack;
	}
	
	/**
	 * Add a new target to the stack.
	 */
	public void push(T target) {
		internalStack.addFirst(target);
	}

	/**
	 * Get the current target.
	 */
	public T peek() {
		return internalStack.getFirst();
	}

	/**
	 * Remove the current target.
	 */
	public T pop() {
		return internalStack.removeFirst();
	}
	
	/**
	 * True if empty.
	 */
	public boolean isEmpty() {
		return internalStack.isEmpty();
	}
	
}
