package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.inventory.domain.Card;
import org.helianto.inventory.domain.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Card repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CardRepository 
	extends JpaRepository<Card, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param cardSet
	 * @param cardLabel
	 */
	Card findByCardSetAndCardLabel(CardSet cardSet, String cardLabel);

}