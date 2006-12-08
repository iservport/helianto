package org.helianto.core;
// Generated 08/12/2006 09:46:04 by Hibernate Tools 3.2.0.beta8



/**
 * 				
 * <p>
 * Personal data, if any.
 * </p>
 * 	
 * @author Mauricio Fernandes de Castro
 * 				
 * 			
 */
public class PersonalData  implements java.io.Serializable {

    // Fields    

     private String firstName;
     private String lastName;
     private char gender;
     private int appellation;

     // Constructors

    /** default constructor */
    public PersonalData() {
    }

	/** minimal constructor */
    public PersonalData(char gender, int appellation) {
        this.gender = gender;
        this.appellation = appellation;
    }
    /** full constructor */
    public PersonalData(String firstName, String lastName, char gender, int appellation) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.gender = gender;
       this.appellation = appellation;
    }
   
    // Property accessors
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
    public char getGender() {
        return this.gender;
    }
    
    public void setGender(char gender) {
        this.gender = gender;
    }
    public int getAppellation() {
        return this.appellation;
    }
    
    public void setAppellation(int appellation) {
        this.appellation = appellation;
    }




}


