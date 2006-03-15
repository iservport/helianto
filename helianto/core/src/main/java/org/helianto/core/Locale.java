package org.helianto.core;
// Generated 14/03/2006 22:23:41 by Hibernate Tools 3.1.0.beta4



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

public class Locale  implements java.io.Serializable {


    // Fields    

     private int id;
     private Locale parent;
     private String language;
     private String country;
     private char localeType;


    // Constructors

    /** default constructor */
    public Locale() {
    }

	/** minimal constructor */
    public Locale(String language, String country, char localeType) {
        this.language = language;
        this.country = country;
        this.localeType = localeType;
    }
    
    /** full constructor */
    public Locale(Locale parent, String language, String country, char localeType) {
        this.parent = parent;
        this.language = language;
        this.country = country;
        this.localeType = localeType;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Locale getParent() {
        return this.parent;
    }
    
    public void setParent(Locale parent) {
        this.parent = parent;
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
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Locale) ) return false;
		 Locale castOther = ( Locale ) other; 
         
		 return ( (this.getLanguage()==castOther.getLanguage()) || ( this.getLanguage()!=null && castOther.getLanguage()!=null && this.getLanguage().equals(castOther.getLanguage()) ) )
 && ( (this.getCountry()==castOther.getCountry()) || ( this.getCountry()!=null && castOther.getCountry()!=null && this.getCountry().equals(castOther.getCountry()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         
         result = 37 * result + ( getLanguage() == null ? 0 : this.getLanguage().hashCode() );
         result = 37 * result + ( getCountry() == null ? 0 : this.getCountry().hashCode() );
         
         return result;
   }   





}
