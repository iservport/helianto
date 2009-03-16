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


package org.helianto.process.orm;

import org.helianto.core.dao.BasicDao;
import org.helianto.process.CardSet;
import org.helianto.process.test.CardSetTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class CardSetDaoTests extends AbstractProcessTest {
	
	public CardSetDaoTests() {
		super();
		setAutowireMode(AUTOWIRE_BY_NAME);
	}

    /**
     * Find by natural id.
     */  
    public void testFindOneResult() {
    	CardSet cardSet = CardSetTestSupport.createSample();
    	CardSet managedCardSet = cardSetDao.merge(cardSet);

    	cardSetDao.flush();
    	cardSetDao.clear();
        
        String criteria = "entity.id=0 AND internalNumber=0 ";
        
        assertEquals(cardSet, managedCardSet);
        assertNotNull(cardSetDao.find(criteria));
    }
    

    //- collabs

    private BasicDao<CardSet> cardSetDao;
    
    public void setCardSetDao(BasicDao<CardSet> cardSetDao) {
        this.cardSetDao = cardSetDao;
    }
    
}