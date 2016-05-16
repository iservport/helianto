package org.helianto.install.service;

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

}
