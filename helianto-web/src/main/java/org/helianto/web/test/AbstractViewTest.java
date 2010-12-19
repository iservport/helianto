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
import java.util.List;

import org.helianto.controller.DefaultTargetForm;
import org.helianto.core.filter.classic.UserBackedFilter;
import org.helianto.core.security.UserDetailsAdapter;
import org.helianto.core.test.SecurityTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;

import freemarker.template.TemplateException;

/**
 * Base class to test freemarker views derived from frame.ftl.
 * 
 * <p>
 * IMPORTANT: set the property <code>visualTest</code> to false to
 * run the test in automated enviroments! Otherwise the tests will fail
 * by default and output the generated html under a folder named prototype.
 * </p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractViewTest<F extends UserBackedFilter, T> extends AbstractFreeMarkerViewTestSupport {
	
	F filter;
	DefaultTargetForm<T> targetForm;
	T target;
	
	protected abstract boolean visualTest();
	protected abstract F createFilter();
	protected abstract List<T> getList();
	protected abstract T createTarget();
	protected abstract String getBase();
	
	protected String getEntityStyle() {
		return "white.css";
	}
	
	protected String getFolder() {
		return getBase();
	}
	
	protected String getOutputName() {
		return getBase();
	}
	
	protected String getTargetName() {
		return getBase();
	}
	
	protected boolean isForwarRequired() {
		return false;
	}
	
	protected String getAssign() {
		return "/assign";
	}
	
	protected String getSelectionNav() {
		return "/selection_nav";
	}
	
	protected String getSelectionFilter() {
		return "/selection_filter";
	}
	
	protected String getSelectionList() {
		return "/selection";
	}
	
	protected String getSideNav() {
		return "/"+getBase()+"_nav";
	}
	
	protected String getSideBar() {
		return "/"+getBase()+"_bar";
	}
	
	protected String getTemplate() {
		return "/"+getBase();
	}
	
	protected String getEditNav() {
		return "/edit_nav";
	}
	
	protected String getInfo() {
		return null;
	}
	
	protected String getForm() {
		return "/"+getBase()+"_form";
	}
	
	protected UserDetailsAdapter getAuthorizedUser() {
		return SecurityTestSupport.createUserDetailsAdapter();
	}
	
	protected String getContextRoot() {
		return "";
	}
	
	protected Object createParent() {
		return null;
	}
	
	protected String getParentName() {
		return null;
	}
	
	@Test
	public void doSelection() throws Exception {
		if (getList()!=null) {
			process(selection());
		}
	}
	
	@Test
	public void doBrowse() throws Exception {
		process(browse());
	}
	
	@Test
	public void doEdit() throws Exception {
		process(edit());
	}
	
	protected void process(String phase) throws Exception {
		setOutputFileName(getOutputName()+phase+".htm");
		processView("frame.ftl", model, visualTest());
	}
	
	@Before
	public final void setUp() throws IOException, TemplateException, InstantiationException, IllegalAccessException {
		super.setUp();
		filter = createFilter();
		targetForm = new DefaultTargetForm<T>();
		target = createTarget();
		targetForm.setTarget(target);
		model.put("entityStyle", getEntityStyle());
		model.put("secureUser", getAuthorizedUser());
		model.put(getTargetName(),  target);
		if (isForwarRequired()) {
			model.put("forward",   getFolder()+"/forward");
		}
		if (getAssign()!=null) {
			model.put("assign",   getFolder()+getAssign());
		}
		if (getInfo()!=null) {
			model.put("info",   getFolder()+getInfo());
		}
		model.put("filter",  filter);
		model.put("formObject",  targetForm);
		bindingResult = new BeanPropertyBindingResult(targetForm, "formObject");
		model.put("testRoot", getContextRoot());
		Object parent = createParent();
		if (parent!=null && getParentName()!=null) {
			model.put(getParentName(), parent);
		}
		addToModel();
	}
	
	protected void addToModel() {
	}
	
	protected String selection() {
		model.put("targetList",  getList());
		if (getList()!=null) {
			model.put("targetListSize",  getList().size());
		}
		else {
			model.put("targetListSize",  0);
		}
		model.put("sidenav",  getFolder()+getSelectionNav());
		model.put("sidebar",  getFolder()+getSelectionFilter());
		model.put("template", getFolder()+getSelectionList());
		return "Selection";
	}

	protected String browse() {
		model.put("sidenav",  getFolder()+getSideNav());
		model.put("sidebar",  getFolder()+getSideBar());
		model.put("template", getFolder()+getTemplate());
		return "Browse";
	}

	protected String edit() {
		model.put("sidenav",  getFolder()+getEditNav());
		model.put("sidebar",  getFolder()+getSideBar());
		model.put("template", getFolder()+getForm());
		return "Edit";
	}

}
