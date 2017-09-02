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

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Locale;
import java.util.UUID;

/**
 * Context.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_context",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextName"})}
)
public class Context implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Column(length=20)
    private String contextName;
    
    private Locale locale;
    
    @Column(length=20)
    private String defaultEncoding = "ISO-8859-1";
    
    @Column(length=12)
    private String preferredDateFormat;
    
    @Column(length=12)
    private String preferredTimeFormat;
    
    @Column(length=5)
    private String rfc822TimeZone;
    
    /**
     * Default constructor.
     */
    public Context() {
    	super();
    	this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    /** 
     * Key constructor.
     * 
     * @param contextName
     */
    public Context(String contextName) {
    	this(contextName, Locale.getDefault());
    }

    /** 
     * Locale constructor.
     * 
     * @param contextName
     * @param locale
     */
    public Context(String contextName, Locale locale) {
    	this();
    	setContextName(contextName);
        setLocale(locale);
    }

    /**
     * Primary key.
     */
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Context name.
     */
    public String getContextName() {
        return this.contextName;
    }
    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    /**
     * Locale.
     */
    public Locale getLocale() {
        return this.locale;
    }
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Default encoding.
     */
    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }
    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    /**
     * Preferred date format.
     */
    public String getPreferredDateFormat() {
        return this.preferredDateFormat;
    }
    public void setPreferredDateFormat(String preferredDateFormat) {
        this.preferredDateFormat = preferredDateFormat;
    }

    /**
     * Preferred time format.
     */
    public String getPreferredTimeFormat() {
        return this.preferredTimeFormat;
    }
    public void setPreferredTimeFormat(String preferredTimeFormat) {
        this.preferredTimeFormat = preferredTimeFormat;
    }

    /**
     * Rfc822 time zone.
     */
    public String getRfc822TimeZone() {
        return this.rfc822TimeZone;
    }
    public void setRfc822TimeZone(String rfc822TimeZone) {
        this.rfc822TimeZone = rfc822TimeZone;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Context)) return false;
        final Context other = (Context) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$contextName = this.getContextName();
        final Object other$contextName = other.getContextName();
        if (this$contextName == null ? other$contextName != null : !this$contextName.equals(other$contextName))
            return false;
        final Object this$locale = this.getLocale();
        final Object other$locale = other.getLocale();
        if (this$locale == null ? other$locale != null : !this$locale.equals(other$locale)) return false;
        final Object this$defaultEncoding = this.getDefaultEncoding();
        final Object other$defaultEncoding = other.getDefaultEncoding();
        if (this$defaultEncoding == null ? other$defaultEncoding != null : !this$defaultEncoding.equals(other$defaultEncoding))
            return false;
        final Object this$preferredDateFormat = this.getPreferredDateFormat();
        final Object other$preferredDateFormat = other.getPreferredDateFormat();
        if (this$preferredDateFormat == null ? other$preferredDateFormat != null : !this$preferredDateFormat.equals(other$preferredDateFormat))
            return false;
        final Object this$preferredTimeFormat = this.getPreferredTimeFormat();
        final Object other$preferredTimeFormat = other.getPreferredTimeFormat();
        if (this$preferredTimeFormat == null ? other$preferredTimeFormat != null : !this$preferredTimeFormat.equals(other$preferredTimeFormat))
            return false;
        final Object this$rfc822TimeZone = this.getRfc822TimeZone();
        final Object other$rfc822TimeZone = other.getRfc822TimeZone();
        if (this$rfc822TimeZone == null ? other$rfc822TimeZone != null : !this$rfc822TimeZone.equals(other$rfc822TimeZone))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $contextName = this.getContextName();
        result = result * PRIME + ($contextName == null ? 43 : $contextName.hashCode());
        final Object $locale = this.getLocale();
        result = result * PRIME + ($locale == null ? 43 : $locale.hashCode());
        final Object $defaultEncoding = this.getDefaultEncoding();
        result = result * PRIME + ($defaultEncoding == null ? 43 : $defaultEncoding.hashCode());
        final Object $preferredDateFormat = this.getPreferredDateFormat();
        result = result * PRIME + ($preferredDateFormat == null ? 43 : $preferredDateFormat.hashCode());
        final Object $preferredTimeFormat = this.getPreferredTimeFormat();
        result = result * PRIME + ($preferredTimeFormat == null ? 43 : $preferredTimeFormat.hashCode());
        final Object $rfc822TimeZone = this.getRfc822TimeZone();
        result = result * PRIME + ($rfc822TimeZone == null ? 43 : $rfc822TimeZone.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Context;
    }

    public String toString() {
        return "org.helianto.core.domain.Context(id=" + this.getId() + ", contextName=" + this.getContextName() + ", locale=" + this.getLocale() + ", defaultEncoding=" + this.getDefaultEncoding() + ", preferredDateFormat=" + this.getPreferredDateFormat() + ", preferredTimeFormat=" + this.getPreferredTimeFormat() + ", rfc822TimeZone=" + this.getRfc822TimeZone() + ")";
    }
}
