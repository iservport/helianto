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


package org.helianto.document.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Add information, in most cases visual, to some other domain class.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractTag implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String tagCode;
    private String tagName;
    private String tagIcon;
    private char sequence;
    
    /** 
     * Default constructor
     */
    public AbstractTag() {
    	super();
    	setTagName("");
    	setTagIcon("");
    	setSequence('0');
    }

    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Tag code.
     */
    @Column(length=12)
    public String getTagCode() {
        return this.tagCode;
    }
    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    /**
     * Tag name.
     */
    @Column(length=128)
    public String getTagName() {
        return this.tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Tag icon.
     */
    @Column(length=32)
    public String getTagIcon() {
        return this.tagIcon;
    }
    public void setTagIcon(String tagIcon) {
        this.tagIcon = tagIcon;
    }

    /**
     * Sequence.
     */
    public char getSequence() {
        return this.sequence;
    }
    public void setSequence(char priority) {
        this.sequence = priority;
    }

    /**
     * Content.
     */
    @Transient
    public String getContent() {
        return "";
    }
    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("tagCode").append("='").append(getTagCode()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * hashCode
    */
   @Override
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getTagCode() == null ? 0 : this.getTagCode().hashCode() );
         return result;
   }   

}
