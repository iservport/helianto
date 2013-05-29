package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.inventory.domain.Card;
import org.helianto.inventory.domain.CardSet;

/**
 * Card repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CardRepository 
	extends FilterRepository<Card, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param cardSet
	 * @param cardLabel
	 */
	Card findByCardSetAndCardLabel(CardSet cardSet, String cardLabel);

}