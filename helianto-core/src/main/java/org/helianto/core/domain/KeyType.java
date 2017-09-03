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

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represent key types like customer, supplier or government assigned numbers.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_keytype",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextName", "keyCode"})}
)
public class KeyType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(length=20)
    private String contextName;
    
    @Column(length=20)
    private String keyCode = "";
    
    private Character keyGroup = 'A';
    
    @Column(length=48)
    private String keyName = "";
    
    @Column(length=255)
    private String purpose = "";

    @Column(length=512)
    private String synonyms = "";

    /** 
     * Default constructor
     */
    public KeyType() {
    	super();
    }

    /** 
     * Key constructor
     * 
     * @param contextName
     * @param keyCode
     */
    public KeyType(String contextName, String keyCode) {
    	this();
    	setContextName(contextName);
    	setKeyCode(keyCode);
    }

    /**
     * Primary key.
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Context name.
     */
    public String getContextName() {
        return contextName;
    }
    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    /**
     * <<NaturalKey>> Key code.
     */
    public String getKeyCode() {
        return this.keyCode;
    }
    public KeyType setKeyCode(String keyCode) {
        this.keyCode = keyCode;
        return this;
    }
    
    /**
     * Group to distinguish some keys from others, when applicable.
     */
    public Character getKeyGroup() {
		return keyGroup;
	}
    public void setKeyGroup(Character keyGroup) {
		this.keyGroup = keyGroup;
	}

    /**
     * Key name.
     */
    public String getKeyName() {
        return this.keyName;
    }
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Purpose description.
     */
    public String getPurpose() {
        return this.purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    /**
     * Comma separated list of synonyms.
     */
    public String getSynonyms() {
		return synonyms;
	}
    public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

    /**
     * Merger.
     *
     * @param command
     */
    public KeyType merge(KeyType command) {
        setKeyGroup(command.getKeyGroup());
        setKeyName(command.getKeyName());
        setPurpose(command.getPurpose());
        setSynonyms(command.getSynonyms());
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof KeyType)) return false;
        final KeyType other = (KeyType) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$contextName = this.getContextName();
        final Object other$contextName = other.getContextName();
        if (this$contextName == null ? other$contextName != null : !this$contextName.equals(other$contextName))
            return false;
        final Object this$keyCode = this.getKeyCode();
        final Object other$keyCode = other.getKeyCode();
        if (this$keyCode == null ? other$keyCode != null : !this$keyCode.equals(other$keyCode)) return false;
        final Object this$keyGroup = this.getKeyGroup();
        final Object other$keyGroup = other.getKeyGroup();
        if (this$keyGroup == null ? other$keyGroup != null : !this$keyGroup.equals(other$keyGroup)) return false;
        final Object this$keyName = this.getKeyName();
        final Object other$keyName = other.getKeyName();
        if (this$keyName == null ? other$keyName != null : !this$keyName.equals(other$keyName)) return false;
        final Object this$purpose = this.getPurpose();
        final Object other$purpose = other.getPurpose();
        if (this$purpose == null ? other$purpose != null : !this$purpose.equals(other$purpose)) return false;
        final Object this$synonyms = this.getSynonyms();
        final Object other$synonyms = other.getSynonyms();
        if (this$synonyms == null ? other$synonyms != null : !this$synonyms.equals(other$synonyms)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $contextName = this.getContextName();
        result = result * PRIME + ($contextName == null ? 43 : $contextName.hashCode());
        final Object $keyCode = this.getKeyCode();
        result = result * PRIME + ($keyCode == null ? 43 : $keyCode.hashCode());
        final Object $keyGroup = this.getKeyGroup();
        result = result * PRIME + ($keyGroup == null ? 43 : $keyGroup.hashCode());
        final Object $keyName = this.getKeyName();
        result = result * PRIME + ($keyName == null ? 43 : $keyName.hashCode());
        final Object $purpose = this.getPurpose();
        result = result * PRIME + ($purpose == null ? 43 : $purpose.hashCode());
        final Object $synonyms = this.getSynonyms();
        result = result * PRIME + ($synonyms == null ? 43 : $synonyms.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof KeyType;
    }

    public String toString() {
        return "org.helianto.core.domain.KeyType(id=" + this.getId() + ", contextName=" + this.getContextName() + ", keyCode=" + this.getKeyCode() + ", keyGroup=" + this.getKeyGroup() + ", keyName=" + this.getKeyName() + ", purpose=" + this.getPurpose() + ", synonyms=" + this.getSynonyms() + ")";
    }

}