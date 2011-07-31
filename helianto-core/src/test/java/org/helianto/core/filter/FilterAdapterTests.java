package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import org.helianto.core.User;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.core.test.UserTestSupport;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FilterAdapterTests {
	
	@Test
	public void filter() {
		User user = UserTestSupport.createUser();
		user.getIdentity().setPrincipal("TESTE");
		
		FirstFilter filter1 = new FirstFilter(user);
		assertEquals("alias.alias = 'teste' AND alias.userState = 'A' ", filter1.createCriteriaAsString());
		filter1.getForm().setUserState('B');
		assertEquals("alias.alias = 'teste' AND alias.userState = 'B' ", filter1.createCriteriaAsString());
		SecondFilter filter2 = new SecondFilter(user);
		assertEquals("alias.type = 'I' ", filter2.createCriteriaAsString());
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
