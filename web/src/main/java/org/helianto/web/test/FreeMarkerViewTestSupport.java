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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * @deprecated see AbstractFreeMarkerViewTestSupport
 */
public class FreeMarkerViewTestSupport extends TestCase {

    protected StaticWebApplicationContext wac;

    protected HttpServletRequest request;

    protected Map<String, Object> model;
    
    protected FreeMarkerConfigurer configurer;
    
    protected BindingResult bindingResult;
    
    protected String outputFileName = "";
    
    /**
     * Default implementation returns "classpath:/freemarker/".
     */
    protected String getTemplateLoaderPath() {
    	return "classpath:/freemarker/";
    }

    /**
     * Default implementation returns "ISO-8859-1".
     */
    protected String getDefaultEncoding() {
    	return "ISO-8859-1";
    }
    
    /**
     * Default implementation false.
     */
    protected boolean getPreferFileSystemAccess() {
    	return false;
    }

    @Override
    public void setUp() throws IOException, TemplateException, InstantiationException, IllegalAccessException {
        wac = new StaticWebApplicationContext();
        wac.setServletContext(new MockServletContext());

        request = new MockHttpServletRequest();
        request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
        request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
        request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE, new FixedThemeResolver());
        
        configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath(getTemplateLoaderPath());
        configurer.setDefaultEncoding(getDefaultEncoding());
        configurer.setPreferFileSystemAccess(getPreferFileSystemAccess());
        configurer.afterPropertiesSet();

        wac.getDefaultListableBeanFactory().registerSingleton("freeMarkerConfigurer", configurer);
        wac.refresh();

        model = new HashMap<String, Object>();
        model.put("flowExecutionKey", "fKey");
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
        fv.setEncoding(getDefaultEncoding());
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
        if (logger.isDebugEnabled()) {
            logger.debug("Url "+fv.getUrl());
            if (request.getAttribute("errors")!=null) {
                logger.debug("Errors: "+request.getAttribute("errors"));
            }
        }
        searchTransition(expectedResponse.getContentAsString());
        if (visualTest) {
            if (!outputFileName.equals("")) {
                createOutputFile(expectedResponse.getContentAsString().toCharArray(), outputFileName);
            }
            else {
                System.out.println(expectedResponse.getContentAsString());
            }
            fail("Forced to fail; please, set the visualTest parameter to false");
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
        fv.setEncoding(getDefaultEncoding());
        if (visualTest) {
            if (!outputFileName.equals("")) {
                createOutputFile(expectedResponse.getContentAsString().toCharArray(), outputFileName);
            }
            else {
                System.out.println(expectedResponse.getContentAsString());
            }
            fail("Forced to fail; please, set the visualTest parameter to false");
        }
        return expectedResponse;
    }
    
    public void createOutputFile(char[] buffer, String outputFileName) {
        PrintWriter outputStream = null;
        String outputDir = "prototype/";
        String resourcePath = "src/main/resources";
        try {
            File dirFile = new File(outputDir);
            if (dirFile.exists() || dirFile.mkdirs()) {
                outputStream = 
                    new PrintWriter(new FileWriter(outputDir.toString()+outputFileName));
                if (logger.isDebugEnabled()) {
                    logger.debug("Output is "+outputDir.toString()+outputFileName);
                }
            }
            else {
                outputStream = 
                    new PrintWriter(new FileWriter(resourcePath+outputFileName));
                if (logger.isDebugEnabled()) {
                    logger.debug("Output is "+resourcePath+outputFileName);
                }
            }
            outputStream.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
    
    protected boolean searchTransition(CharSequence text) {
    	Pattern pattern = Pattern.compile("_eventId.*$");
    	Matcher matcher = pattern.matcher(text);
        boolean found = false;
        while (matcher.find()) {
        	if (logger.isDebugEnabled()) {
            	logger.debug("Found " + matcher.group()+" at "+matcher.start());
        	}
            found = true;
        }
        return found;

    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    
    private final Log logger = LogFactory.getLog(FreeMarkerViewTestSupport.class);

}
