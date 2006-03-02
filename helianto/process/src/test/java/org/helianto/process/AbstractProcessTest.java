//Created on 04/08/2005
package org.helianto.process;

import org.helianto.core.junit.AbstractTestLife;

public abstract class AbstractProcessTest extends AbstractTestLife {

    private CollaboratorTestFactory factory = new CollaboratorTestFactory(); 
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "deploy/applicationContext-core1.xml" };
    }
    
    public Object getCollaborator(Class clazz) {
        return factory.getCollaborator(clazz);
    }

    public Object getNewCollaborator(Class clazz) {
        return factory.getNewCollaborator(clazz);
    }
    
    public String generateKey() {
        return factory.generateKey();
    }

}
