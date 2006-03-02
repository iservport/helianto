//Created on 04/08/2005
package org.helianto.core.hibernate;

import org.helianto.core.junit.AbstractTestLife;
import org.helianto.core.mail.JavaMailAdapter;

public abstract class AbstractCoreTest extends AbstractTestLife {

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
    
    public void flush() { }

    public void clear() { }

    public JavaMailAdapter getJavaMailAdapter() {
        return null;
    }

    public void setJavaMailAdapter(JavaMailAdapter javaMailAdapter) {
    }
    
}
