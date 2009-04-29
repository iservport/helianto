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
import org.helianto.process.Card;
import org.helianto.process.test.CardTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class CardDaoTests extends AbstractProcessIntegrationTest {
	
	public CardDaoTests() {
		super();
		setAutowireMode(AUTOWIRE_BY_NAME);
	}

    /**
     * Find by natural id.
     */  
    public void testFindOneResult() {
    	Card card = CardTestSupport.createSample();
    	Card managedCard = cardDao.merge(card);

    	cardDao.flush();
    	cardDao.clear();
        
        String criteria = "cardSet.id=0 AND cardLabel=' ' ";
        
        assertEquals(card, managedCard);
        assertNotNull(cardDao.find(criteria));
    }
    

    //- collabs

    private BasicDao<Card> cardDao;
    
    public void setCardDao(BasicDao<Card> cardDao) {
        this.cardDao = cardDao;
    }
    
}
