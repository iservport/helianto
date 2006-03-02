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

package org.helianto.admin.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.helianto.core.Entity;
import org.helianto.core.service.CoreMgr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {
    
    protected ApplicationContext context;
    
    protected CoreMgr coreMgr;

    public void init() {
        context = new ClassPathXmlApplicationContext( 
                new String[] {"deploy/dataSource.xml", 
                        "deploy/sessionFactory.xml",
                        "deploy/support.xml",
                        "deploy/coreMgr.xml"});
        coreMgr = (CoreMgr) context.getBean("coreMgr");
    }
    
    private static void createAndShowGUI(String name) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel(name);
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.init();
        final Entity entity = (Entity) starter.coreMgr.loadEntity(new Long(1));
        System.out.println(">>"+entity.getAlias());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(entity.getAlias());
            }
        });
    }

}
