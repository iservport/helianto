/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.core.hibernate;

import org.helianto.core.test.AbstractHibernateIntegrationTest;

public class LightweightDaoImplTests extends AbstractHibernateIntegrationTest {
    
    // class under test
    
    private LightweightDaoImpl lightweightDaoImpl; 

    public void testLoad()  {
//        load(Class clazz, Serializable key);
    }

    public void testMerge()  {
//        merge(Object object)
        
    }

    public void testRemove()  {
//        remove(Object object)
        
    }

    public void testRefresh()  {
//        refresh(Object object)
        
    }

    public void testPersist()  {
//        persist(Object object)
        
    }

    public void testFind()   {
        // find(String query, Object values)
    }

    public void testFindVarArgs()  {
//        hibernateTemplate.execute(
//            new HibernateCallback() {
//
//                public Object doInHibernate(Session session) throws HibernateException, SQLException {
//                    session.get
//                    Query query = session.createQuery(
//                    "...");
//                    query.setString(0, ...);
//                    return query.list();
//                }
//                
//            }
//        );
        // find(String query, Object... values)
    }

    public void testFindUnique()  {
        // findUnique(String query, Object values)
    }

    public void testFindUniqueVarArgs()  {
        // findUnique(String query, Object... values)
    }

    // setup
    
    @Override
    public void onSetUpInTransaction() {
        lightweightDaoImpl = new LightweightDaoImpl();
        lightweightDaoImpl.setHibernateTemplate(hibernateTemplate);
    }

}
