package org.helianto.core.hibernate;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.springframework.core.io.Resource;

/**
 * Extension of the spring LocalSessionFactoryBean that allows for multiple
 * hibernate config locations to be set, allowing modular mapping configuration.
 *
 * @see org.springframework.orm.hibernate3.LocalSessionFactoryBean
 *
 * <P>
 * Copyright (c) Employers Mutual Limited
 * </P>
 *
 * @author jw2: May 18, 2005
 */
public class CustomSessionFactoryBean extends
        org.springframework.orm.hibernate3.LocalSessionFactoryBean {
    
    private static final Log logger = LogFactory.getLog(CustomSessionFactoryBean.class);

    private boolean mCheckConfigLocations = true;

    private Resource[] mConfigLocations;

    public CustomSessionFactoryBean() {
        super();
    }

    /**
     * Set the location of a list of Hibernate XML config files, for example as
     * classpath resource "classpath:hibernate.cfg.xml".
     * <p>
     * Note: Can be omitted when all necessary properties and mapping resources
     * are specified locally via this bean.
     * <p>
     * Note: If configLocation is also set, this will be treated as if it is at
     * the beginning of the list.
     * <p>
     * Note: For each configuration item, the first one appears to take
     * precedence. So configLocation takes precedence over configLocations which
     * takes precedence over properties in the spring configuation. But all
     * mapping directives are processed irrespective of where they occur and in
     * what order. So configurations added here should <b>only </b> contain
     * &lt;mapping&gt; elements to avoid unexpected behaviour.
     *
     * @see org.hibernate.cfg.Configuration#configure(java.net.URL)
     */
    public void setConfigLocations(Resource[] pConfigLocations) {
        mCheckConfigLocations = true;
        mConfigLocations = pConfigLocations;
    }

    /**
     * Set the location of a list of Hibernate XML config files, for example as
     * classpath resource "classpath:hibernate.cfg.xml". If a config location is
     * not found no exception is thrown, but an INFO message is logged.
     * <p>
     * Note: Can be omitted when all necessary properties and mapping resources
     * are specified locally via this bean.
     * <p>
     * Note: If configLocation is also set, this will be treated as if it is at
     * the beginning of the list.
     * <p>
     * Note: For each configuration item, the first one appears to take
     * precedence. So configLocation takes precedence over configLocations which
     * takes precedence over properties in the spring configuation. But all
     * mapping directives are processed irrespective of where they occur and in
     * what order. So configurations added here should <b>only </b> contain
     * &lt;mapping&gt; elements to avoid unexpected behaviour.
     *
     * @see org.hibernate.cfg.Configuration#configure(java.net.URL)
     */
    public void setConfigOptionalLocations(Resource[] pConfigLocations) {
        mCheckConfigLocations = false;
        mConfigLocations = pConfigLocations;
    }

    /** @see org.springframework.orm.hibernate3.LocalSessionFactoryBean#postProcessConfiguration(org.hibernate.cfg.Configuration) */
    protected void postProcessConfiguration(Configuration pConfig) throws HibernateException {
        super.postProcessConfiguration(pConfig);

        if (mConfigLocations != null) {
            for (int i = 0; i < mConfigLocations.length; i++) {
                try {
                    // Load Hibernate configurations from given location.
                    pConfig.configure(mConfigLocations[i].getURL());
                } catch (FileNotFoundException e) {
                    if (mCheckConfigLocations)
                        throw new HibernateException("Could not configure from resource: "
                                + mConfigLocations[i].getDescription(), e);
                    else
                        logger.info("A hibernate configuration resource does not exist: "
                                + mConfigLocations[i].getDescription());

                } catch (IOException e) {
                    throw new HibernateException("Could not configure from resource: "
                            + mConfigLocations[i].getDescription(), e);
                }
            }
        }
    }
} 