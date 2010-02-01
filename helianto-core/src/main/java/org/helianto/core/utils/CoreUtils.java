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

package org.helianto.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Core utilities.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CoreUtils {

	/**
	 * Create a sorted list from the corresponding set.
	 *  
	 * @param set
	 */
	public static <T extends Comparable<T>> List<T> createSortedList(Set<T> set) {
		List<T> sortedList = createList(set);
		Collections.sort(sortedList);
		return sortedList;
	}
	
	/**
	 * Create a list from the corresponding set.
	 *  
	 * @param set
	 */
	public static <T> List<T> createList(Set<T> set) {
		List<T> sortedList = new ArrayList<T>(set);
    	logger.debug("Created list of size {}", sortedList.size());
		return sortedList;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CoreUtils.class);

}
