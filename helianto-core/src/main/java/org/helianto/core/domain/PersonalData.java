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

package org.helianto.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.helianto.core.def.Appellation;
import org.helianto.core.def.Gender;
import org.helianto.core.def.PersonalIdentityType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Personal data, if any.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Embeddable
public class PersonalData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName = "";
    private String lastName = "";
    private char gender = Gender.NOT_SUPPLIED.getValue();
    private char appellation = Appellation.NOT_SUPPLIED.getValue();
    private Date birthDate = new Date(0l);
	private String profileUrl = "";
	private String imageUrl = "";
    private String personalIdentityNumber_1 = "";
    private char personalIdentityType_1 = PersonalIdentityType.NOT_REQUIRED.getValue();
    private String personalIdentityNumber_2 = "";
    private char personalIdentityType_2 = PersonalIdentityType.NOT_REQUIRED.getValue();

    /** 
     * Default constructor.
     */
    public PersonalData() {
    	this("", "");
    }
    
    /**
     * Name constructor.
     * 
     * @param firstName
     * @param lastName
     */
    public PersonalData(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /**
     * First name.
     */
    @Column(length=32)
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name.
     */
    @Column(length=32)
    public String getLastName() {
        return this.lastName;
    }
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
    public void setGenderAsEnum(Gender gender) {
        this.gender = gender.getValue();
    }

    /**
     * Appellation.
     */
    public char getAppellation() {
        return this.appellation;
    }
    public void setAppellation(char appellation) {
        this.appellation = appellation;
    }
    public void setAppellationAsEnum(Appellation appellation) {
        this.appellation = appellation.getValue();
    }
    
    /**
     * Birth date.
     */
    @DateTimeFormat(style="SS")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getBirthDate() {
		return birthDate;
	}
    public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
    
    public String getProfileUrl() {
		return profileUrl;
	}
    public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
    
    public String getImageUrl() {
		return imageUrl;
	}
    public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
    /**
     * First personal identity number.
     */
    @Column(length=20, name="PIN_1") 
    public String getPersonalIdentityNumber_1() {
		return personalIdentityNumber_1;
	}
    public void setPersonalIdentityNumber_1(String personalIdentityNumber_1) {
		this.personalIdentityNumber_1 = personalIdentityNumber_1;
	}
    
    /**
     * First personal identity type.
     */
    @Column(name="PIT_1") 
    public char getPersonalIdentityType_1() {
		return personalIdentityType_1;
	}
    public void setPersonalIdentityType_1(char personalIdentityType_1) {
		this.personalIdentityType_1 = personalIdentityType_1;
	}
    public void setPersonalIdentityType_1(PersonalIdentityType personalIdentityType) {
		this.personalIdentityType_1 = personalIdentityType.getValue();
	}

    /**
     * Second personal identity number.
     */
    @Column(length=20, name="PIN_2") 
    public String getPersonalIdentityNumber_2() {
		return personalIdentityNumber_2;
	}
    public void setPersonalIdentityNumber_2(String personalIdentityNumber_2) {
		this.personalIdentityNumber_2 = personalIdentityNumber_2;
	}
    
    /**
     * Personal document type.
     */
    @Column(name="PIT_2") 
    public char getPersonalIdentityType_2() {
		return personalIdentityType_2;
	}
    public void setPersonalIdentityType_2(char personalIdentityType_2) {
		this.personalIdentityType_2 = personalIdentityType_2;
	}
    public void setPersonalIdentityType_2(PersonalIdentityType personalIdentityType) {
		this.personalIdentityType_2 = personalIdentityType.getValue();
	}

}
