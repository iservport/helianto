package org.helianto.security.internal;

/**
 * Registration form.
 * 
 * @author mauriciofernandesdecastro
 */
public class Registration {

    private String contextId = "";

    private String email;

    private  String password;

    private String entityAlias;

    private boolean admin;

    private boolean isDomain;

    private Integer cityId = 0;

    public Registration(){
      super();
    }

    public Registration(Integer cityId, String contextId, String email, String entityAlias, Boolean isAdmin, Boolean isDomain, String password) {
        this.cityId = cityId;
        this.contextId = contextId;
        this.email = email;
        this.entityAlias = entityAlias;
        this.admin = isAdmin;
        this.isDomain = isDomain;
        this.password = password;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEntityAlias() {
        return entityAlias;
    }

    public void setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isDomain() {
        return isDomain;
    }

    public void setDomain(boolean domain) {
        this.isDomain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "admin=" + admin +
                ", contextId=" + contextId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", entityAlias='" + entityAlias + '\'' +
                ", isDomain=" + isDomain +
                ", cityId=" + cityId +
                '}';
    }
}
