package org.helianto.core;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * Persist personal data.
 * </p>
 * 	
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 			
*/
public class PersonalData implements Serializable {

    /** nullable persistent field */
    private String firstName;

    /** nullable persistent field */
    private String lastName;

    /** persistent field */
    private char gender;

    /** persistent field */
    private int appellation;

    /** full constructor */
    public PersonalData(String firstName, String lastName, char gender, int appellation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.appellation = appellation;
    }

    /** default constructor */
    public PersonalData() {
    }

    /** minimal constructor */
    public PersonalData(char gender, int appellation) {
        this.gender = gender;
        this.appellation = appellation;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /** 
     * 					@see Gender
     * 				
     */
    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    /** 
     * 					@see Appellation
     * 				
     */
    public int getAppellation() {
        return this.appellation;
    }

    public void setAppellation(int appellation) {
        this.appellation = appellation;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .toString();
    }

}
