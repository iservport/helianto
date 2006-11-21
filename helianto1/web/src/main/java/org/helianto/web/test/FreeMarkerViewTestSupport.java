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

package org.helianto.web.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.theme.FixedThemeResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.TemplateException;

/**
 * Base class to test FreeMarker based views.
 * Thanks to Darren Davison for providing part of this
 * test inside Spring code.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class FreeMarkerViewTestSupport extends TestCase {

    protected StaticWebApplicationContext wac;

    protected HttpServletRequest request;

    protected Map<String, Object> model;
    
    protected FreeMarkerConfigurer configurer;
    
    protected BindingResult bindingResult;

    @Override
    public void setUp() throws IOException, TemplateException, InstantiationException, IllegalAccessException {
        wac = new StaticWebApplicationContext();
        wac.setServletContext(new MockServletContext());

        request = new MockHttpServletRequest();
        request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
        request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
        request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, new FixedThemeResolver());
        
        configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:/freemarker/");
        configurer.setDefaultEncoding("ISO-8859-1");
        configurer.setPreferFileSystemAccess(false);
        configurer.afterPropertiesSet();

        wac.getDefaultListableBeanFactory().registerSingleton("freeMarkerConfigurer", configurer);
        wac.refresh();

        model = new HashMap<String, Object>();
        model.put("flowExecutionKey", "flowExecutionKeyValue");
    }
    
    /**
     * Basic FreeMarker view setup.
     * 
     * @param templateName a template in classpath
     * @throws Exception 
     */
    protected FreeMarkerView prepareView(String templateName) throws Exception {
        FreeMarkerView fv = new FreeMarkerView();
        fv.setUrl(templateName);
        fv.setApplicationContext(wac);
        fv.setExposeSpringMacroHelpers(true);
        fv.setBeanName(templateName);
        fv.afterPropertiesSet();
        return fv;
    }
    
    /**
     * FreeMarker view rendering.
     * 
     * @param templateName a template in classpath.
     * @param model 
     * @param visualTest if true prints to System.out and fails.
     */
    protected MockHttpServletResponse processView(String templateName, Map<String, Object> model, boolean visualTest) throws Exception {
        MockHttpServletResponse expectedResponse = new MockHttpServletResponse();
        FreeMarkerView fv = prepareView(templateName);
        fv.render(model, request, expectedResponse);
        System.out.println(request.getAttribute("errors"));
        if (visualTest) {
            String output = expectedResponse.getContentAsString();
            System.out.println(output);
            fail();
        }
        return expectedResponse;
    }
    
    /**
     * FreeMarker view rendering.
     * 
     * @param fv a FreeMarker view 
     * @param visualTest if true prints to System.out and fails.
     */
    protected MockHttpServletResponse processView(FreeMarkerView fv, boolean visualTest) throws Exception {
        MockHttpServletResponse expectedResponse = new MockHttpServletResponse();
        fv.render(model, request, expectedResponse);
        if (visualTest) {
            String output = expectedResponse.getContentAsString();
            System.out.println(output);
            fail();
        }
        return expectedResponse;
    }
    
}
