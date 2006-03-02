package org.helianto.process;

import org.helianto.core.hibernate.AbstractCoreTest;
import org.helianto.core.mail.JavaMailAdapter;

public class ResourcesTests extends AbstractCoreTest {
    
    public ResourcesTests() {
        setKeyType(Long.class);
        setBaseQuery("from Resources");
    }

    public Object first() {
        Resource obj = (Resource) getCollaborator(Resource.class);
        return obj;
    }

    public Object changeUniqueKey(Object object) {
        ((Resource) object).setResourceCode(generateKey());
        return object;
    }

    public Object third() {
    	Resource obj = (Resource) getNewCollaborator(Resource.class);
        return obj;
    }

    public JavaMailAdapter getJavaMailHandler() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setJavaMailHandler(JavaMailAdapter javaMailHandler) {
        // TODO Auto-generated method stub
        
    }
    
}
