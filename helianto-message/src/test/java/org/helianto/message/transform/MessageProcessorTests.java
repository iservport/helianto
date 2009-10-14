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

package org.helianto.message.transform;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.reset;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.helianto.core.Identity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Mauricio Fernandes de Castro
 */
public class MessageProcessorTests {
    
    @Test
    public void messageConstructor() throws IOException, TemplateException {
        Model model = new Model(new Identity());
        model.setTemplateName("TEST");
        MessageProcessor messageProcessor = new MessageProcessor(configuration, model);
        
        expect(configuration.getTemplate("TEST"))
            .andReturn(template);
        replay(configuration);
        
        template.process(isA(Map.class), isA(StringWriter.class));
        replay(template);
        
        assertTrue(messageProcessor.process() instanceof String);
        verify(configuration);
        verify(template);
    }
    
    //

    private Configuration configuration;
    Template template;
    
    @Before
    public void setUp() {
        configuration = createMock(Configuration.class);
        template = createMock(Template.class);
    }
    
    @After
    public void tearDown() {
        reset(configuration);
        reset(template);
    }
    
}
