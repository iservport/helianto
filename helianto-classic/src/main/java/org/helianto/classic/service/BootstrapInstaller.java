package org.helianto.classic.service;

import org.helianto.classic.install.CityInstaller;
import org.helianto.classic.install.EntityInstaller;
import org.helianto.core.domain.City;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.install.IdentityInstaller;
import org.helianto.core.repository.IdentityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

/**
 * Bootstrap installer.
 */
public class BootstrapInstaller implements InitializingBean {

    protected static final Logger logger = LoggerFactory.getLogger(BootstrapInstaller.class);

    private String contextName;

    @Inject
    private CityInstaller cityInstaller;

    @Inject
    private IdentityRepository identityRepository;

    @Inject
    private IdentityInstaller identityInstaller;

    @Inject
    private EntityInstaller entityInstaller;

    @Inject
    private Environment env;

    /**
     * @throws Exception
     */
    @Override
    public final void afterPropertiesSet() throws Exception {
        contextName = env.getProperty("helianto.defaultContextName", "DEFAULT");
        if (env.getProperty("helianto.reinstall", "NO").equals("YES")) {
            City rootCity = installContext();
            Identity rootIdentity = installRootIdentity();
            // TODO install services
            Entity rootEntity = installRootEntity(rootCity.getId(), rootIdentity);
            afterInstall(rootIdentity, rootEntity);
        }
    }

    /**
     * Run once at installation to assure root state and city are persisted.
     */
    protected final City installContext() {
        return cityInstaller.installStatesAndCities(contextName, "BRA");
    }

    /**
     * Run once at installation to assure root identity is persisted.
     */
    protected Identity installRootIdentity() {
        String rootPrincipal = env.getRequiredProperty("helianto.rootPrincipal");
        String rootFirstName = env.getRequiredProperty("helianto.rootFirstName");
        String rootLastName = env.getRequiredProperty("helianto.rootLastName");
        String rootDisplayName = env.getProperty("helianto.rootDisplayName", rootFirstName);
        // Root identity
        return identityInstaller.installIdentity(rootPrincipal,rootDisplayName,rootFirstName,rootLastName);
    }

    /**
     * Run once at installation to assure root entity is persisted.
     */
    protected Entity installRootEntity(int rootCityId, Identity rootIdentity) {
        String rootEntityAlias = env.getProperty("helianto.rootEntityAlias", "DEFAULT");
        return entityInstaller.installEntity(rootCityId, rootEntityAlias, rootIdentity.getPrincipal());
    }
    
    /**
     * Subclasses may override to continue with post installation tasks.
     * 
     * @param rootIdentity
     * @param rootEntity
     */
    protected void afterInstall(Identity rootIdentity, Entity rootEntity) {
    	
    }

}