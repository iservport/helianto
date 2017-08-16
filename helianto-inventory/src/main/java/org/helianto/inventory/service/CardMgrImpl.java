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

import javax.annotation.Resource;

import org.helianto.classic.SequenceMgr;
import org.helianto.core.domain.Entity;
import org.helianto.inventory.CardMgr;
import org.helianto.inventory.InvalidCardException;
import org.helianto.inventory.domain.Card;
import org.helianto.inventory.domain.CardSet;
import org.helianto.inventory.repository.CardRepository;
import org.helianto.inventory.repository.CardSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <code>CardMgr</code> default implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Service("cardMgr")
public class CardMgrImpl 
	implements CardMgr 
{

	@Transactional
	public CardSet storeCardSet(CardSet cardSet) {
		sequenceMgr.validateInternalNumber(cardSet);
		return cardSetRepository.saveAndFlush(cardSet);
	}

	@Transactional(readOnly=true)
	public Card findCard(Entity entity, String cardLabel, boolean createIfNecessary) throws InvalidCardException {
		long cardSetNumber = CardSet.getInternalNumber(cardLabel);
    	logger.debug("Card set number {}", cardSetNumber);
    	CardSet cardSet = cardSetRepository.findByEntityAndInternalNumber(entity, cardSetNumber);
    	if (cardSet==null) {
			throw new InvalidCardException(cardLabel, "card set not found");
    	}
    	Card card = cardRepository.findByCardSetAndCardLabel(cardSet, cardLabel);
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
	 * Subclasses may override to control creation.
	 * 
	 * @param cardSet
	 * @param cardLabel
	 */
	protected Card doCreateCard(CardSet cardSet, String cardLabel) throws InvalidCardException {
		return new Card(cardSet, cardLabel);
	}
	
	@Transactional
	public Card storeCard(Card card) {
		return cardRepository.saveAndFlush(card);
	}

	// collabs
	
	private CardRepository cardRepository;
	private CardSetRepository cardSetRepository;
	private SequenceMgr sequenceMgr;
	
	@Resource
	public void setCardSetRepository(CardSetRepository cardSetRepository) {
		this.cardSetRepository = cardSetRepository;
	}

	@Resource
	public void setCardRepository(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	@Resource 
	public void setSequenceMgr(SequenceMgr sequenceMgr) {
		this.sequenceMgr = sequenceMgr;
	}

	private static final Logger logger = LoggerFactory.getLogger(CardMgrImpl.class);

}
