package org.helianto.core;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * A domain object to represent a supervisor, unique by a short name.
 * </p>
 * <p>
 * Supervisors are the source for comunication between server and
 * e-mail recipients, so they hold the information necessary to connect to 
 * external servers. They can be configured to represent a geographical
 * limit, bound to a language or country, or according to any territory
 * arrangement.
 * </p>
 * <p>
 * At least one default (root) supervisor must be defined per installation.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Supervisor implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String supervisorName;

    /** nullable persistent field */
    private String httpAddress;

    /** nullable persistent field */
    private String supervisorDesc;

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
    private org.helianto.core.Supervisor parent;

    /** nullable persistent field */
    private org.helianto.core.Locale locale;

    /** full constructor */
    public Supervisor(String supervisorName, String httpAddress, String supervisorDesc, Date added, String mailHost, String mailUser, String mailPassword, String storeType, String storeHost, String storeUser, String storePassword, org.helianto.core.Supervisor parent, org.helianto.core.Locale locale) {
        this.supervisorName = supervisorName;
        this.httpAddress = httpAddress;
        this.supervisorDesc = supervisorDesc;
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
    public Supervisor() {
    }

    /** minimal constructor */
    public Supervisor(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupervisorName() {
        return this.supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getHttpAddress() {
        return this.httpAddress;
    }

    public void setHttpAddress(String httpAddress) {
        this.httpAddress = httpAddress;
    }

    public String getSupervisorDesc() {
        return this.supervisorDesc;
    }

    public void setSupervisorDesc(String supervisorDesc) {
        this.supervisorDesc = supervisorDesc;
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

    public org.helianto.core.Supervisor getParent() {
        return this.parent;
    }

    public void setParent(org.helianto.core.Supervisor parent) {
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
