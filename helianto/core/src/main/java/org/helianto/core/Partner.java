package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * Persist relationships between the organization and its 
 * partners, like customers, suppliers, banks, etc. 
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 *         
*/
public class Partner implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String alias;

    /** persistent field */
    private char state;

    /** persistent field */
    private boolean strong;

    /** nullable persistent field */
    private Date relatedSince;

    /** nullable persistent field */
    private String numberAssignedRemotely;

    /** nullable persistent field */
    private Long importedKey;

    /** nullable persistent field */
    private String profile;

    /** nullable persistent field */
    private org.helianto.core.Entity entity;

    /** nullable persistent field */
    private org.helianto.core.Entity related;

    /** full constructor */
    public Partner(String alias, char state, boolean strong, Date relatedSince, String numberAssignedRemotely, Long importedKey, String profile, org.helianto.core.Entity entity, org.helianto.core.Entity related) {
        this.alias = alias;
        this.state = state;
        this.strong = strong;
        this.relatedSince = relatedSince;
        this.numberAssignedRemotely = numberAssignedRemotely;
        this.importedKey = importedKey;
        this.profile = profile;
        this.entity = entity;
        this.related = related;
    }

    /** default constructor */
    public Partner() {
    }

    /** minimal constructor */
    public Partner(String alias, char state, boolean strong) {
        this.alias = alias;
        this.state = state;
        this.strong = strong;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public char getState() {
        return this.state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public boolean isStrong() {
        return this.strong;
    }

    public void setStrong(boolean strong) {
        this.strong = strong;
    }

    public Date getRelatedSince() {
        return this.relatedSince;
    }

    public void setRelatedSince(Date relatedSince) {
        this.relatedSince = relatedSince;
    }

    public String getNumberAssignedRemotely() {
        return this.numberAssignedRemotely;
    }

    public void setNumberAssignedRemotely(String numberAssignedRemotely) {
        this.numberAssignedRemotely = numberAssignedRemotely;
    }

    public Long getImportedKey() {
        return this.importedKey;
    }

    public void setImportedKey(Long importedKey) {
        this.importedKey = importedKey;
    }

    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public org.helianto.core.Entity getEntity() {
        return this.entity;
    }

    public void setEntity(org.helianto.core.Entity entity) {
        this.entity = entity;
    }

    public org.helianto.core.Entity getRelated() {
        return this.related;
    }

    public void setRelated(org.helianto.core.Entity related) {
        this.related = related;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
