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


package org.helianto.inventory.test;

import org.helianto.core.Entity;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.inventory.CardSet;
import org.helianto.inventory.CardType;
import org.helianto.process.ProcessDocument;
import org.helianto.process.test.ProcessTestSupport;


/**
 * Card set test support.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CardSetTestSupport {
	
	private static int testKey = 0;
	
	/**
	 * Create sample.
	 */
	public static CardSet createCardSet() {
		Entity entity = EntityTestSupport.createEntity();
		return CardSetTestSupport.createCardSet(entity);
	}

	/**
	 * Create sample.
	 * 
	 * @param entity
	 */
	public static CardSet createCardSet(Entity entity) {
		ProcessDocument process = ProcessTestSupport.createProcess(entity);
		return CardSetTestSupport.createCardSet(entity, process);
	}

	/**
	 * Create sample.
	 * 
	 * @param entity
	 * @param process
	 */
	public static CardSet createCardSet(Entity entity, ProcessDocument process) {
		CardSet sample =  CardSet.cardSetFactory(entity, CardType.DATA, testKey++);
		sample.setProcess(process);
		return sample;
	}

}
