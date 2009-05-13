/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <p>
 * Personal data, if any.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
@Embeddable
public class PersonalData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private char gender;
    private char appellation;

    /** default constructor */
    public PersonalData() {
    }

    /**
     * FirstName getter.
     */
    @Column(length=32)
    public String getFirstName() {
        return this.firstName;
    }
    /**
     * FirstName setter.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * LastName getter.
     */
    @Column(length=32)
    public String getLastName() {
        return this.lastName;
    }
    /**
     * LastName setter.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gender.
     */
    public char getGender() {
        return this.gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender.getValue();
    }

    /**
     * Appellation getter.
     */
    public char getAppellation() {
        return this.appellation;
    }
    public void setAppellation(char appellation) {
        this.appellation = appellation;
    }
    public void setAppellation(Appellation appellation) {
        this.appellation = appellation.getValue();
    }

    /**
     * <code>PersonalData</code> is created by default with
     * appellation and gender as <code>NOT_SUPPLIED</code>.
     * 
     * @see Appellation
     * @see Gender
     */
    public static PersonalData personalDataFactory(String firstName, String lastName) {
        PersonalData personalData = new PersonalData();
        
        personalData.setFirstName(firstName);
        personalData.setLastName(lastName);
        personalData.setAppellation(Appellation.NOT_SUPPLIED.getValue());
        personalData.setGender(Gender.NOT_SUPPLIED.getValue());
        return personalData;
    }

}
