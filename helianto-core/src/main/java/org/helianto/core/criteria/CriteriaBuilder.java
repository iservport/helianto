package org.helianto.core.criteria;

/**
 * A criteria builder interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CriteriaBuilder {

    /**
     * Prints the output as a string.
     */
    public String getCriteriaAsString();
    
    /**
     * And appender.
     */
    public CriteriaBuilder appendAnd();
    
    /**
     * Or appender.
     */
    public CriteriaBuilder appendOr();
    
}
