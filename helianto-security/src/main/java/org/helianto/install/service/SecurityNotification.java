package org.helianto.install.service;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.user.domain.UserToken;

/**
 * Security notification interface.
 */
public interface SecurityNotification {

    /**
     * Sent to the user (candidate) after sign-up submission.
     *
     * @param userToken
     */
    Boolean sendSignUp(UserToken userToken);

    /**
     * Sent to the user after final registration submission.
     *
     * @param userToken
     */
    Boolean sendWelcome(UserToken userToken);

    /**
     * Sent to the user after password recovery request.
     *
     * @param userToken
     */
    Boolean sendRecovery(UserToken userToken);

    /**
     * Sent to the entity admin if an user requires access.
     *
     * @param entity
     * @param identity
     */
    Boolean sendAdminNotify(Entity entity, Identity identity);

}
