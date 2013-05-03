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


package org.helianto.inventory.service;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.SequenceMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.filter.Filter;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.inventory.InvalidCardException;
import org.helianto.inventory.domain.Card;
import org.helianto.inventory.domain.CardSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * <code>CardMgr</code> default implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("cardMgr")
public class CardMgrImpl implements CardMgr {

	public List<CardSet> findCardSets(Filter cardSetFilter) {
    	List<CardSet> cardSetList = (List<CardSet>) cardSetDao.find(cardSetFilter);
    	if (logger.isDebugEnabled() && cardSetList!=null) {
    		logger.debug("Found card list of size {}", cardSetList.size());
    	}
    	return cardSetList;
	}

	public CardSet storeCardSet(CardSet cardSet) {
		sequenceMgr.validateInternalNumber(cardSet);
		return cardSetDao.merge(cardSet);
	}

	public Card findCard(Entity entity, String cardLabel, boolean createIfNecessary) throws InvalidCardException {
		long cardSetNumber = CardSet.getInternalNumber(cardLabel);
    	logger.debug("Card set number {}", cardSetNumber);
    	CardSet cardSet = cardSetDao.findUnique(entity, cardSetNumber);
    	if (cardSet==null) {
			throw new InvalidCardException(cardLabel, "card set not found");
    	}
    	Card card = doFindCard(cardSet, cardLabel);
    	if (card!=null) {
        	logger.debug("Found card {}", card.getCardLabel());
    	}
    	else if(createIfNecessary) {
    		card = doCreateCard(cardSet, cardLabel);
        	logger.debug("Created card {}", card.getCardLabel());
    	}
    	return card;
	}
	
	/**
	 * Subclasses may override to control card selection.
	 * 
	 * @param cardSet
	 * @param cardLabel
	 */
	protected Card doFindCard(CardSet cardSet, String cardLabel) {
		return cardDao.findUnique(cardSet.getId(), cardLabel);
	}
	
	/**
	 * Subclasses may override to control creation.
	 * 
	 * @param cardSet
	 * @param cardLabel
	 */
	protected Card doCreateCard(CardSet cardSet, String cardLabel) throws InvalidCardException {
		return cardSet.cardFactory(cardLabel);
	}
	
	// collabs
	
	private BasicDao<Card> cardDao;
	private FilterDao<CardSet> cardSetDao;
	private SequenceMgr sequenceMgr;
	
	@Resource(name="cardSetDao")
	public void setCardSetDao(FilterDao<CardSet> cardSetDao) {
		this.cardSetDao = cardSetDao;
	}

	@Resource(name="cardDao")
	public void setCardDao(BasicDao<Card> cardDao) {
		this.cardDao = cardDao;
	}
	
	@Resource 
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}

	private static final Logger logger = LoggerFactory.getLogger(CardMgrImpl.class);

}
