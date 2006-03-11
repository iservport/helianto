/*
 * Criado em 09/03/2006
 *
 * TODO Para alterar o gabarito desse arquivo gerado, v? para
 * Janela - Prefer?ncias - Java - Estilo de C?digo - Gabaritos de C?digo
 */
package org.helianto.core.junit;

import java.util.Date;

/**
 */
public interface UnitTest {
	
    /**
     * Generate a not repeatable key.
     */
    public String generateKey();

    /**
     * Generate a not repeatable key of a given size.
     */
    public String generateKey(int size);

}
