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

package org.helianto.document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * A superclass to represent document versions.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated versions will be included on the document in future releases
 */
@MappedSuperclass
public class AbstractVersion implements java.io.Serializable {

 	private static final long serialVersionUID = 1L;
	private int id;
    private int majorNumber;
    private int minorNumber;
    private char releaseAction;
    private char activityCode;
    private String changeSummary;

    /** default constructor */
    public AbstractVersion() {
    	setMajorNumber(0);
    	setMinorNumber(0);
    }

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getMajorNumber() {
        return this.majorNumber;
    }
    public void setMajorNumber(int majorNumber) {
        this.majorNumber = majorNumber;
    }
    
    public int getMinorNumber() {
        return this.minorNumber;
    }
    public void setMinorNumber(int minorNumber) {
        this.minorNumber = minorNumber;
    }
    
    public char getReleaseAction() {
        return this.releaseAction;
    }
    public void setReleaseAction(char releaseAction) {
        this.releaseAction = releaseAction;
    }
    public void setReleaseAction(ReleaseAction releaseAction) {
        this.releaseAction = releaseAction.getValue();
    }
    
    public char getActivityCode() {
        return this.activityCode;
    }
    public void setActivityCode(char activityCode) {
        this.activityCode = activityCode;
    }

    public String getChangeSummary() {
        return this.changeSummary;
    }
    public void setChangeSummary(String changeSummary) {
        this.changeSummary = changeSummary;
    }


}


