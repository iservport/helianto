package org.helianto.install.service;

import org.helianto.core.domain.Entity;
import org.helianto.security.domain.UserAuthority;
import org.helianto.security.repository.UserAuthorityRepository;
import org.helianto.user.domain.UserGroup;
import org.helianto.user.repository.UserGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Authority installer.
 */
@Service
public class AuthorityInstaller {

    protected static final Logger logger = LoggerFactory.getLogger(AuthorityInstaller.class);

    @Inject
    private UserGroupRepository userGroupRepository;

    @Inject
    private UserAuthorityRepository userAuthorityRepository;

    /**
     * Assures root user is persisted and have appropriate authorities.
     */
    public void installAuthorities(Entity entity, String principal) {
        // Root authorities
        UserGroup admin = userGroupRepository.findByEntity_IdAndUserKey(entity.getId(), "ADMIN");
        // TODO pay attention to the hard coded ADMIN group here; see contextGroups for a better solution
        if (admin==null) {
            throw new IllegalArgumentException("Unable to install context, ADMIN group not found; check your contextGroups definition to ensure proper installation.");
        }
        // Root privileges
        UserAuthority rootAuthority = installAuthority(admin, "ADMIN", "READ,WRITE");
        // User privileges
        UserAuthority userAuthority = installAuthority(admin, "USER", "READ,WRITE");
    }

    private UserAuthority installAuthority(UserGroup userGroup, String serviceName, String serviceExtension) {
        UserAuthority authority = userAuthorityRepository.findByUserGroupAndServiceCode(userGroup, serviceName);
        if (authority==null) {
            authority = new UserAuthority(userGroup, serviceName);
            authority.setServiceExtension(serviceExtension);
            userAuthorityRepository.saveAndFlush(authority);
        }
        logger.debug("Mandatory {} authority is {}", serviceName, authority);
        return authority;
    }

}
