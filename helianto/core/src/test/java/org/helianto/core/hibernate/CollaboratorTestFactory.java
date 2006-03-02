//Created on 16/08/2005
package org.helianto.core.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Appellation;
import org.helianto.core.Credential;
import org.helianto.core.CredentialState;
import org.helianto.core.CredentialType;
import org.helianto.core.Customer;
import org.helianto.core.Entity;
import org.helianto.core.Gender;
import org.helianto.core.Locale;
import org.helianto.core.LocaleType;
import org.helianto.core.Notification;
import org.helianto.core.Partner;
import org.helianto.core.PartnerState;
import org.helianto.core.PersonalData;
import org.helianto.core.Role;
import org.helianto.core.Service;
import org.helianto.core.Supervisor;
import org.helianto.core.User;
import org.helianto.core.UserType;
import org.helianto.process.Resource;

public class CollaboratorTestFactory {

    /**
     * Logger for this class
     */
    protected static final Log logger = LogFactory.getLog(CollaboratorTestFactory.class);
    
    private long startTime = new Date().getTime();
    
    private Map collaborator;
    
    public String generateKey() {
        return String.valueOf(new Date().getTime()-startTime);
    }

    @SuppressWarnings("unchecked")
    public final Object getCollaborator(Class clazz) {
        Object object = null;
        if (collaborator == null) {
            collaborator = new HashMap();
        }
        if (collaborator.get(clazz) == null) {
            object = createCollaborator(clazz);
            collaborator.put(clazz, object);
            return object;
        }
        return collaborator.get(clazz);
    }
    
    public Object getNewCollaborator(Class clazz) {
        collaborator.remove(clazz);
        return getCollaborator(clazz);
    }
    
    protected Object createCollaborator(Class clazz) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n         create collaborator with class "+clazz.toString());
        }
        if (clazz.equals(Locale.class)) {
            return createLocale();
        }
        if (clazz.equals(Supervisor.class)) {
            return createSupervisor();
        }
        if (clazz.equals(Entity.class)) {
            return createEntity();
        }
        if (clazz.equals(Credential.class)) {
            return createCredential();
        }
        if (clazz.equals(User.class)) {
            return createUser();
        }
        if (clazz.equals(Service.class)) {
            return createService();
        }
        if (clazz.equals(Role.class)) {
            return createRole();
        }
        if (clazz.equals(Partner.class)) {
            return createPartner();
        }
        if (clazz.equals(Customer.class)) {
            return createCustomer();
        }
        if (clazz.equals(Resource.class)) {
            return createResources();
        }
        throw new UnsupportedOperationException("Unsupported type");
    }
    
    private Locale createLocale() {
        Locale locale = new Locale();
        locale.setLanguage(generateKey());
        locale.setCountry(generateKey());
        locale.setLocaleType(LocaleType.LANGUAGE.getValue());
        logger.info("\n         Created Locale, [ " +
                locale.toString()+
                " details [ id = "+locale.getId()+
                ", language = "+locale.getLanguage()+
                ", country = "+locale.getCountry()+
                ", localeType = "+locale.getLocaleType()+
                " ]]");
        return locale;
    }
    
    private Supervisor createSupervisor() {
        Supervisor supervisor =  new Supervisor();
        supervisor.setSupervisorName(generateKey());
        supervisor.setSupervisorDesc("DESC");
        supervisor.setAdded(new Date());
        logger.info("\n         Created Owner, [ " +
                supervisor.toString()+
                " details [ id = "+supervisor.getId()+
                ", ownerName = "+supervisor.getSupervisorName()+
                ", ownerDesc = "+supervisor.getSupervisorDesc()+
                ", added = "+supervisor.getAdded()+
                " ]]");
        return supervisor;
    }
    
    private Entity createEntity() {
        Entity entity =  new Entity();
        entity.setAlias(generateKey());
        entity.setSupervisor((Supervisor) getCollaborator(Supervisor.class));
        logger.info("\n         Created Entity, [ " +
                entity.toString()+
                " details [ id = "+entity.getId()+
                ", alias = "+entity.getAlias()+
                " ]]");
        return entity;
    }
    
    private Credential createCredential() {
        Credential credential =  new Credential();
        credential.setPrincipal(generateKey()+"@TEST");
        credential.setPassword(generateKey());
        credential.setCredentialType(CredentialType.NOT_ADDRESSABLE.getValue());
        credential.setNotification(Notification.AUTOMATIC.getValue());
        credential.setCreated(new Date());
        credential.setLastModified(credential.getCreated());
        credential.setExpired(credential.getCreated());
        credential.setCredentialState(CredentialState.IDLE.getValue());
        PersonalData pd = new PersonalData();
        pd.setFirstName(generateKey());
        pd.setLastName(generateKey());
        pd.setGender(Gender.NOT_SUPPLIED.getValue());
        pd.setAppellation(Appellation.NOT_SUPPLIED.getValue());
        credential.setPersonalData(pd);
        logger.info("\n         Created Credential, [ " +
                credential.toString()+
                " details [ id = "+credential.getId()+
                ", principal = "+credential.getPrincipal()+
                ", password = "+credential.getPassword()+
                ", credentialType = "+credential.getCredentialType()+
                ", created = "+credential.getCreated()+
                ", lastModified = "+credential.getLastModified()+
                ", expired = "+credential.getExpired()+
                ", credentialState = "+credential.getCredentialState()+
                ", personalData = "+
                credential.getPersonalData().toString()+
                " details [ firstName = "+credential.getPersonalData().getFirstName()+
                ", firstName = "+credential.getPersonalData().getFirstName()+
                ", lastName = "+credential.getPersonalData().getLastName()+
                ", gender = "+credential.getPersonalData().getGender()+
                ", appellation = "+credential.getPersonalData().getAppellation()+
                " ] ]]");
        return credential;
    }
    
    private User createUser() {
        User user =  new User();
        user.setUserType(UserType.INTERNAL.getValue());
        user.setEntity((Entity) getNewCollaborator(Entity.class));
        user.setCredential((Credential) getNewCollaborator(Credential.class));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        logger.info("\n         Created User, [ " +
                user.toString()+
                " details [ id = "+user.getId()+
                ", userType = "+user.getUserType()+
                ", entity = "+user.getEntity()+
                ", credential = "+user.getCredential()+
                ", accountNonExpired = "+user.isAccountNonExpired()+
                ", accountNonLocked = "+user.isAccountNonLocked()+
                " ]]");
        return user;
    }
    
    private Service createService() {
        Service service =  new Service();
        service.setServiceName(generateKey());
        logger.info("\n         Created Service, [ " +
                service.toString()+
                " details [ id = "+service.getId()+
                ", serviceName = "+service.getServiceName()+
                " ]]");
        return service;
    }
    
    private Role createRole() {
        Role role = new Role();
        role.setRoleName(generateKey());
        role.setUser((User) getNewCollaborator(User.class));
        role.setService((Service) getNewCollaborator(Service.class));
        logger.info("\n         Created Role, [ " +
                role.toString()+
                " details [ id = "+role.getId()+
                ", roleName = "+role.getRoleName()+
                ", user = "+role.getUser()+
                ", service = "+role.getService()+
                " ]]");
        return role;
    }

    private Partner createPartner() {
        Partner partner = new Partner();
        partner.setAlias(generateKey());
        partner.setEntity((Entity) getNewCollaborator(Entity.class));
        partner.setImportedKey(new Long(1));
        partner.setNumberAssignedRemotely(generateKey());
        partner.setProfile("PROFILE PROFILE PROFILE");
        partner.setRelatedSince(new Date());
        partner.setState(PartnerState.IDLE.getValue());
        partner.setStrong(false);
        return partner;
    }
    
    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setAlias(generateKey());
        customer.setEntity((Entity) getNewCollaborator(Entity.class));
        customer.setImportedKey(new Long(1));
        customer.setNumberAssignedRemotely(generateKey());
        customer.setProfile("PROFILE PROFILE PROFILE");
        customer.setRelatedSince(new Date());
        customer.setState(PartnerState.IDLE.getValue());
        customer.setStrong(false);
        return customer;
    }

    private Resource createResources() {
		Resource mq1 = new Resource();
		mq1.setEntity((Entity) getNewCollaborator(Entity.class));
		mq1.setResourceCode(generateKey());
		mq1.setResourceName("Indução 1");
		mq1.setResourceType(0);
		return mq1;
    }

}
