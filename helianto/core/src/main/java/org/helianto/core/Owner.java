package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * A class to persist an owner, unique by key.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Owner implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String ownerName;

    /** nullable persistent field */
    private String ownerDesc;

    /** nullable persistent field */
    private Date added;

    /** nullable persistent field */
    private String mailHost;

    /** nullable persistent field */
    private String mailUser;

    /** nullable persistent field */
    private String mailPassword;

    /** nullable persistent field */
    private String storeType;

    /** nullable persistent field */
    private String storeHost;

    /** nullable persistent field */
    private String storeUser;

    /** nullable persistent field */
    private String storePassword;

    /** nullable persistent field */
    private org.helianto.core.Owner parent;

    /** nullable persistent field */
    private org.helianto.core.Locale locale;

    /** full constructor */
    public Owner(String ownerName, String ownerDesc, Date added, String mailHost, String mailUser, String mailPassword, String storeType, String storeHost, String storeUser, String storePassword, org.helianto.core.Owner parent, org.helianto.core.Locale locale) {
        this.ownerName = ownerName;
        this.ownerDesc = ownerDesc;
        this.added = added;
        this.mailHost = mailHost;
        this.mailUser = mailUser;
        this.mailPassword = mailPassword;
        this.storeType = storeType;
        this.storeHost = storeHost;
        this.storeUser = storeUser;
        this.storePassword = storePassword;
        this.parent = parent;
        this.locale = locale;
    }

    /** default constructor */
    public Owner() {
    }

    /** minimal constructor */
    public Owner(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerDesc() {
        return this.ownerDesc;
    }

    public void setOwnerDesc(String ownerDesc) {
        this.ownerDesc = ownerDesc;
    }

    public Date getAdded() {
        return this.added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getMailHost() {
        return this.mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public String getMailUser() {
        return this.mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getMailPassword() {
        return this.mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getStoreType() {
        return this.storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getStoreHost() {
        return this.storeHost;
    }

    public void setStoreHost(String storeHost) {
        this.storeHost = storeHost;
    }

    public String getStoreUser() {
        return this.storeUser;
    }

    public void setStoreUser(String storeUser) {
        this.storeUser = storeUser;
    }

    public String getStorePassword() {
        return this.storePassword;
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword;
    }

    public org.helianto.core.Owner getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.core.Owner parent) {
        this.parent = parent;
    }

    public org.helianto.core.Locale getLocale() {
        return this.locale;
    }

    public void setLocale(org.helianto.core.Locale locale) {
        this.locale = locale;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
