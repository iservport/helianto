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

package org.helianto.core.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.helianto.core.Credential;
import org.helianto.core.Entity;
import org.helianto.core.Home;
import org.helianto.core.InternalEnumerator;
import org.helianto.core.User;
import org.helianto.core.creation.CredentialType;
import org.helianto.core.creation.UserCreatorImpl;
import org.helianto.core.mail.MailComposer;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Default implementation of the 
 * <code>CoreMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 */
public class CoreMgrImpl extends AbstractGenericService implements CoreMgr {
    
    public void persistCredential(Credential credential) {
        if (credential.getPassword()==null) {
            credential.setPassword(getUserCreatorImpl().generatePassword(8));
        }
        this.getGenericDao().merge(credential);
    }  
    
    public void persistEntity(Entity entity) {
        this.getGenericDao().merge(entity);
    }
    
    public void persistUser(User user) {
        this.getGenericDao().merge(user);
    }

    public Credential loadCredential(Long key) {
        try {
            return (Credential) getGenericDao().load(Credential.class, key);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Unable to load credential with id "+key+", raised the exception:"+e.toString());
            }
        }
        return null;
    }
    
    public Entity loadEntity(Long key) {
        try {
            return (Entity) getGenericDao().load(Entity.class, key);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Unable to load entity with id "+key+", raised the exception:"+e.toString());
            }
        }
        return null;
    }
    
    public Credential findCredentialByPrincipal(String principal) {
        List list =  (List) getGenericDao().find(CREDENTIAL_QRY_BY_PRINCIPAL, principal);
        if (list.size()==1) {
            return (Credential) list.get(0);
        }
        return null;
    }
    
    public Home findRequiredHome(Object homeName) {
        List list = null;
        if (homeName!=null && homeName instanceof String) {
            list =  (List) getGenericDao().find(HOME_QRY_BY_NAME, homeName.toString());
        }
        if (list!=null && list.size()==1) {
            return (Home) list.get(0);
        }
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        String[] params = new String[] { language, country };
        list =  (List) getGenericDao().find(HOME_QRY_BY_LANGUAGE_AND_COUTRY, params);
        if (list.size()>0) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Home found with default language "+language+
                        "and country "+country);
            }
            return (Home) list.get(0);
        }
        list =  (List) getGenericDao().find(HOME_QRY_BY_LANGUAGE, language);
        if (list.size()>0) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Home found with default language "+language);
            }
            return (Home) list.get(0);
        }
        list =  (List) getGenericDao().find(HOME_QRY_ALL, null);
        if (list.size()>0) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Taking root Supervisor");
            }
            return (Home) list.get(0);
        }
        throw new IllegalStateException("Unable to find at least the root Supervisor");
    }
    
    public Entity findEntityByAlias(String entityAlias) {
        return (Entity) getGenericDao().findUnique(ENTITY_QRY_BY_ENTITY_ALIAS, entityAlias);
    }

    @SuppressWarnings("unchecked")
    public List<User> findUserByCredentialId(Long credId) {
        return (List<User>) getGenericDao().find(USER_QRY_BY_CRED, credId);
    }

    public int currentNumber(Entity entity, String typeName) {
        InternalEnumerator enumerator = null;
        Object[] args = new Object[] { entity.getId(), typeName };
        try {
            enumerator = (InternalEnumerator) getGenericDao().findUnique(ENUM_QRY, args);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Internal enumerator "+typeName+
                        " for entity "+entity+" raised an exception: "+e.toString());
            }
        }
        if (enumerator!=null) {
            int lastNumber = enumerator.getLastNumber();
            enumerator.setLastNumber(lastNumber+1);
            getGenericDao().update(enumerator);
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Internal enumerator "+typeName+
                        " for entity "+entity+" current number is: "+lastNumber);
            }
            return lastNumber;
        } else  {
            enumerator = new InternalEnumerator();
            enumerator.setEntity(entity);
            enumerator.setTypeName(typeName);
            enumerator.setLastNumber(2);
            getGenericDao().save(enumerator);
            if (logger.isDebugEnabled()) {
                logger.debug("\n         Internal enumerator "+typeName+
                        " for entity "+entity+" created, current number is: 1");
            }
            return 1;
        }
    }
    
    public void sendRegistrationNotification(Home home, Credential cred) throws MessagingException {
        if (cred.getCredentialType()==CredentialType.NOT_ADDRESSABLE.getValue()) {
            throw new IllegalStateException("Credential is not addressable.");
        }
        MailComposer mailComposer = getJavaMailAdapter().getMailComposer();
        MimeMessage message = getJavaMailAdapter().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "ISO-8859-1");
        String mailUser = home.getMailAccessData().getUser();
        helper.setTo(cred.getPrincipal());
        helper.setReplyTo(mailUser);
        helper.setFrom(mailUser);
        helper.setSubject(mailComposer
            .composeRegistrationNotificationSubject(home.getHomeName()));
        helper.setSentDate(new Date());
        helper.setText(mailComposer
            .composeRegistrationNotification(cred, home.getHttpAddress()), true);
        getJavaMailAdapter().send(home.getMailTransportData(), home.getMailAccessData(), message);
    }
    
    static final String CREDENTIAL_QRY_BY_PRINCIPAL = 
        "from Credential cred " +
        "where cred.principal = ?";
    
    static final String HOME_QRY_BY_NAME = 
        "from Home home " +
        "where home.homeName = ?";

    static final String HOME_QRY_BY_LANGUAGE = 
        "from Home home " +
        "where home.language = ?";

    static final String HOME_QRY_BY_LANGUAGE_AND_COUTRY = 
        "from Home home " +
        "where home.language = ? " +
        "and home.country = ?";

    static final String HOME_QRY_ALL = 
        "from Home home ";

    static final String ENTITY_QRY_BY_ENTITY_ALIAS = 
        "from Entity ent where ent.alias = ?";
    
    static final String USER_QRY_BY_CRED = 
        "from User user where user.credential.id = ?";
    
    static final String ENUM_QRY = 
        "from InternalEnumerator enumerator " +
        "where enumerator.entity.id = ? "+
        "and enumerator.typeName = ? ";
    
    // accessors
    
    private UserCreatorImpl userCreatorImpl;
    
    public UserCreatorImpl getUserCreatorImpl() {
        return userCreatorImpl;
    }

    public void setUserCreatorImpl(UserCreatorImpl userCreatorImpl) {
        this.userCreatorImpl = userCreatorImpl;
    }

}
