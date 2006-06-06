package org.helianto.process.service;

import org.helianto.core.junit.AbstractIntegrationTest;

public class AbstractProcessTest extends AbstractIntegrationTest {

    @Override
    protected String[] getConfigLocations() {
        return new String[] { 
        		"deploy/dataSource.xml", 
                "deploy/sessionFactory.xml",
                "deploy/support.xml",
                "deploy/transaction.xml",
                "deploy/core.xml",
                "deploy/process.xml"};
    }
    
}
