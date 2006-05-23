package org.helianto.core;
// Generated 23/05/2006 19:13:14 by Hibernate Tools 3.1.0.beta4



/**
 * 				
 * <p>
 * Persist personal data.
 * </p>
 * 	
 * @author Mauricio Fernandes de Castro
 * @version $Id: helianto-core3.hbm.xml,v 1.4 2006/03/28 10:03:12 iserv Exp $
 * 				
 * 			
 */

public class PersonalData extends AbstractCredential implements java.io.Serializable {


    // Fields    

     private String firstName;
     private String lastName;
     /**
      * 					@see Gender
 * 				
     */
     private char gender;
     /**
      * 					@see Appellation
 * 				
     */
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
    /**       
     *      * 					@see Gender
     * 				
     */

    public char getGender() {
        return this.gender;
    }
    
    public void setGender(char gender) {
        this.gender = gender;
    }
    /**       
     *      * 					@see Appellation
     * 				
     */

    public int getAppellation() {
        return this.appellation;
    }
    
    public void setAppellation(int appellation) {
        this.appellation = appellation;
    }
   








}
