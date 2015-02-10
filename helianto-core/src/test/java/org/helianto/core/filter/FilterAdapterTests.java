package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.def.NavigationMode;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.form.NavigableForm;
import org.helianto.user.domain.User;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FilterAdapterTests {
	
	@Test
	public void filter() {
		User user = new User(new Entity(), new Identity("p"));
		user.getIdentity().setPrincipal("TESTE");
		
		FirstFilter filter1 = new FirstFilter(user);
		assertEquals("alias.alias = 'p' AND alias.userState = 'A' ", filter1.createCriteriaAsString());
		filter1.getForm().setUserState('B');
		assertEquals("alias.alias = 'p' AND alias.userState = 'B' ", filter1.createCriteriaAsString());
		
		user.setUserType('I');
		SecondFilter filter2 = new SecondFilter(user);
		assertEquals("alias.type = 'I' ", filter2.createCriteriaAsString());
	}
	
	private NavigableForm form;
	private NavigationMode navigatioMode = NavigationMode.IGNORE;
	
	@SuppressWarnings("serial")
	@Test
	public void navigable() {
		Filter filter = new AbstractFilterAdapter<NavigableForm>(form) {
			@Override protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }
			@Override public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { }
		};
		assertEquals("", filter.createCriteriaAsString());
		navigatioMode = NavigationMode.FETCH_SIBLINGS;
		assertEquals("lower(alias.parentPath) = '/a/b/' ", filter.createCriteriaAsString());
		navigatioMode = NavigationMode.FETCH_DESCENDANTS;
		assertEquals("lower(alias.parentPath) like '/a/b/c/%' ", filter.createCriteriaAsString());
	}
	
	@Before
	public void setUp() {
		form = new NavigableForm() {
			public String getParentPath() { return "/A/B/"; }
			public String getCurrentPath() { return "/A/B/C/"; }
			public NavigationMode getNavigationMode() { return navigatioMode; }
			public void setNavigationMode(NavigationMode navigationMode) {}
		};
	}
	

	/** 
	 * Test subclass
	 * 
	 * @author mauriciofernandesdecastro
	 */
	public class FirstFilter extends AbstractFilterAdapter<User> {

		private static final long serialVersionUID = 1L;

		public FirstFilter(User filter) {
			super(filter);
		}

		public void reset() { }

		@Override
		protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }

		@Override
		public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
			appendEqualFilter("alias", getForm().getUserKey(), mainCriteriaBuilder);
			appendEqualFilter("userState", getForm().getUserState(), mainCriteriaBuilder);
			
		}

	}
	
	/** 
	 * Test subclass
	 * 
	 * @author mauriciofernandesdecastro
	 */
	public class SecondFilter extends AbstractFilterAdapter<User> {

		private static final long serialVersionUID = 1L;

		public SecondFilter(User filter) {
			super(filter);
		}

		public void reset() { }

		@Override
		protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) { }

		@Override
		public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
			appendEqualFilter("type", getForm().getUserType(), mainCriteriaBuilder);
			
		}

	}

}
