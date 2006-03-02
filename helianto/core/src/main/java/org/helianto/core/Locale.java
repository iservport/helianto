package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * A domain object to hold support information for languages and
 * countries.
 * </p>
 * <p>
 * In Java, a <code>java.util.Locale</code> can represent either a 
 * language, a country, or a combination of language and country. Java 
 * locale constructors take string literals, standardized by ISO 639 
 * and ISO 3166, like "en" or "BR".
 * </p>
 * <p>
 * This class holds the same standardized literals, that can be 
 * therefore used to build a Java <code>java.util.Locale</code>. 
 * It also stores a self reference in the field <code>parent</code> 
 * to allaw the service layer to look up a singly-rooted tree for 
 * defaults.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * @see java.util.Locale
 * 				
 * 		
*/
public class Locale implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String language;

    /** persistent field */
    private String country;

    /** persistent field */
    private char localeType;

    /** nullable persistent field */
    private org.helianto.core.Locale parent;

    /** full constructor */
    public Locale(String language, String country, char localeType, org.helianto.core.Locale parent) {
        this.language = language;
        this.country = country;
        this.localeType = localeType;
        this.parent = parent;
    }

    /** default constructor */
    public Locale() {
    }

    /** minimal constructor */
    public Locale(String language, String country, char localeType) {
        this.language = language;
        this.country = country;
        this.localeType = localeType;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public char getLocaleType() {
        return this.localeType;
    }

    public void setLocaleType(char localeType) {
        this.localeType = localeType;
    }

    public org.helianto.core.Locale getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.core.Locale parent) {
        this.parent = parent;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
