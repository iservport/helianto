package org.helianto.inventory.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertSame;

import org.helianto.classic.SequenceMgr;
import org.helianto.inventory.domain.Card;
import org.helianto.inventory.domain.CardSet;
import org.helianto.inventory.repository.CardRepository;
import org.helianto.inventory.repository.CardSetRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class CardMgrImplTests {

	@Test
	public void storeCardSet() {
		CardSet cardSet = new CardSet();
		
		sequenceMgr.validateInternalNumber(cardSet);
		replay(sequenceMgr);
		
		expect(cardSetRepository.saveAndFlush(cardSet)).andReturn(cardSet);
		replay(cardSetRepository);
		
		assertSame(cardSet, cardMgr.storeCardSet(cardSet));
		verify(sequenceMgr);
		verify(cardSetRepository);
	}
	
	@Test
	public void storeCard() {
		Card card = new Card();
		
		expect(cardRepository.saveAndFlush(card)).andReturn(card);
		replay(cardRepository);
		
		assertSame(card, cardMgr.storeCard(card));
		verify(cardRepository);
	}
	
	// collabs
	
	private CardMgrImpl cardMgr;
	private CardRepository cardRepository;
	private CardSetRepository cardSetRepository;
	private SequenceMgr sequenceMgr;
	
	@Before
	public void setUp() {
		cardMgr = new CardMgrImpl();
		cardRepository = createMock(CardRepository.class);
		cardMgr.setCardRepository(cardRepository);
		cardSetRepository = createMock(CardSetRepository.class);
		cardMgr.setCardSetRepository(cardSetRepository);
		sequenceMgr = createMock(SequenceMgr.class);
		cardMgr.setSequenceMgr(sequenceMgr);
	}
	
	@After
	public void tearDown() {
		reset(cardRepository);
		reset(cardSetRepository);
		reset(sequenceMgr);
	}
	

}
