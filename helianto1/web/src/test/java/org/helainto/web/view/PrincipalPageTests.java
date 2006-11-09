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

package org.helainto.web.view;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.helianto.web.view.IdentityForm;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.support.BindStatus;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.theme.FixedThemeResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PrincipalPageTests extends TestCase {

	private FreeMarkerConfigurer configurer;

	private Map<String, Object> model;

	private Map<String, Object> requestContext;

	public void testSummary() {
		model.put("page", 1);
		StringWriter result = process("identity/summary.ftl", false);
	}

//	public void testPrincipal() {
//		model.put("page", 1);
//		StringWriter result = process("identity/principal.ftl", false);
//	}

	public StringWriter process(String templateName, boolean visualTest) {
		try {
			Template template =
			// configurer.getTemplate(templateName);
			configurer.getConfiguration().getTemplate(templateName);
			StringWriter result = new StringWriter();
			template.process(model, result);
			if (visualTest) {
				System.out.println(result.toString());
				fail();
			}
			return result;
		} catch (IOException e) {
			throw new IllegalStateException("Unable to open template "
					+ templateName, e);
		} catch (TemplateException e) {
			throw new IllegalStateException("Ivalid template " + templateName,
					e);
		}
	}

	public void setUp() throws IOException, TemplateException, InstantiationException, IllegalAccessException {
		configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:/freemarker/");
		configurer.setDefaultEncoding("ISO-8859-1");
		configurer.afterPropertiesSet();
		model = prepareForm(IdentityForm.class, "identityForm");
	}
	
	public Map<String, Object> prepareForm(Class formObjectClass, String formObjectName) throws InstantiationException, IllegalAccessException {
		Map<String, Object> model = new HashMap<String, Object>();
		StaticWebApplicationContext wac = new StaticWebApplicationContext();
		wac.setServletContext(new MockServletContext());
		HttpServletRequest request = new MockHttpServletRequest();
		request.setAttribute(
				DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE,
				new AcceptHeaderLocaleResolver());
		request.setAttribute(DispatcherServlet.THEME_RESOLVER_ATTRIBUTE,
				new FixedThemeResolver());
		RequestContext requestContext = new RequestContext(request);
//		BindStatus status = requestContext.getBindStatus("tb.name");
		BindStatus status = new BindStatus(requestContext, formObjectName, false);
		model.put("springMacroRequestContext", requestContext);
		model.put(formObjectName, formObjectClass.newInstance());
		return model;
	}

}
