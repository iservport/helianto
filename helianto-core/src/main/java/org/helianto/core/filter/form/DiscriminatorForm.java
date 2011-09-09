package org.helianto.core.filter.form;

/**
 * Discriminator form interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DiscriminatorForm<C> extends ClassForm<C> {

    /**
     * Discriminator that maps to a class constraint.
     */
	public char getDiscriminator();
	
	/**
	 * Discriminator is a R/W property.
	 * 
	 * @param discriminator
	 */
	public void setDiscriminator(char discriminator);
	
}
