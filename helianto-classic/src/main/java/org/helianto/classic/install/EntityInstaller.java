package org.helianto.classic.install;

import org.helianto.core.domain.Entity;

/**
 * Entity installer
 */
public interface EntityInstaller {

    /**
     * Install entity.
     *
     * @param  alias
     * @param  cityId
     * @param  principal
     */
    Entity installEntity(int cityId, String alias, String principal);

}
