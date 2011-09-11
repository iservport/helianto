package org.helianto.core.filter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.Identity;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.core.UserState;
import org.helianto.core.filter.form.UserGroupForm;
import org.helianto.core.test.EntityTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class UserFormFilterAdapterTests {
	
	static String S1 = "select alias from UserGroup alias ";
	static String S2 = "inner join alias.parentAssociations as parentAssociations ";
	static String O0 = "order by alias.userKey ";
	static String C0 = "alias.entity.id = 10 ";
	static String C1 = "AND alias.userKey = 'USERKEY' ";
	static String C2 = "AND alias.userState = 'A' ";
	static String C3 = "AND alias.identity.id not in (  1 ,  2 ) ";
	static String C4 = "AND alias.class=UserGroup ";
	static String C5 = "AND parentAssociations.parent.id = 100 ";
	static String C6 = "AND parentAssociations.parent.userKey = 'USER' ";
	static String C7 = "AND alias.identity.id = 1 ";
	
	@Test
	public void empty() {
		assertEquals(C0+O0, filter.createCriteriaAsString());
	}

	@Test
	public void select() {
		userKey = "USERKEY";
		assertEquals(C0+C1, filter.createCriteriaAsString());
	}

	@Test
	public void userState() {
		userState = UserState.ACTIVE.getValue();
		assertEquals(C0+C2+O0, filter.createCriteriaAsString());
	}

	@Test
	public void exclusions() {
		List<Identity> exclusions = new ArrayList<Identity>();
		Identity i1 = new Identity("1");
		i1.setId(1);
		exclusions.add(i1);
		Identity i2 = new Identity("2");
		i2.setId(2);
		exclusions.add(i2);
		form.setExclusions(exclusions);
		assertEquals(C0+C3+O0, filter.createCriteriaAsString());
	}

	@Test
	public void clazz() {
		form.setClazz(UserGroup.class);
		assertEquals(C0+C4+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parent() {
		UserGroup parent = new UserGroup(form.getEntity(), "PARENT");
		parent.setId(100);
		form.setParent(parent);
		assertEquals(S1+S2, filter.createSelectAsString());
		assertEquals(C0+C5+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parentUserKey() {
		parentUserKey = "USER";
		assertEquals(S1+S2, filter.createSelectAsString());
		assertEquals(C0+C6+O0, filter.createCriteriaAsString());
	}
	
	@Test
	public void identity() {
		Identity identity = new Identity("PRINCIPAL");
		identity.setId(1);
		form.setIdentity(identity);
		assertEquals(C0+C7+O0, filter.createCriteriaAsString());
	}

	@Test
	public void parentIdentity() {
		Identity identity = new Identity("PRINCIPAL");
		identity.setId(1);
		form.setIdentity(identity);
		parentUserKey = "USER";
		assertEquals(C0+C6+C7+O0, filter.createCriteriaAsString());
	}

	// collabs
	
	private UserFormFilterAdapter filter;
	private UserGroupForm form;
	
    private Entity entity;
    private String userKey = "";
    private String parentUserKey;
    private char userState = ' ';
    private char userType = ' ';

	@SuppressWarnings("serial")
	@Before
	public void setUp() {
		entity = EntityTestSupport.createEntity();
		entity.setId(10);
		form = new UserGroupForm() {
			
		    private Class<? extends UserGroup> clazz;
			private Collection<Identity> exclusions;
		    private UserGroup parent;
		    private List<UserGroup> parentList;
		    private Identity identity;
		    
		    public void reset() { }

		    public Entity getEntity() {
				return entity;
			}
			public String getUserKey() {
				return userKey;
			}
			public UserGroup getParent() {
				return parent;
			}
			public void setParent(UserGroup parent) {
				this.parent = parent;
			}
			public List<UserGroup> getParentList() {
				return parentList;
			}
			public void setParentList(List<UserGroup> parentList) {
				this.parentList = parentList;
			}
			public String getParentUserKey() {
				return parentUserKey;
			}
		    public char getUserType() {
		        return userType;
		    }
		    public char getUserState() {
		        return userState;
		    }
			public Class<? extends UserGroup> getClazz() {
				return clazz;
			}
			public void setClazz(Class<? extends UserGroup> clazz) {
				this.clazz = clazz;
			}
			public char getDiscriminator() {
				if (clazz.equals(UserGroup.class)) {
					return 'G'; 
				}
				if (clazz.equals(User.class)) {
					return 'U'; 
				}
				return ' ';
			}
			public void setDiscriminator(char discriminator) {
				if (discriminator=='G') {
					clazz = UserGroup.class; 
				}
				else if (discriminator=='U') {
					clazz = User.class; 
				}
				else {
					clazz = null;
				}
			}
		    public Identity getIdentity() {
		        return identity;
		    }
		    public void setIdentity(Identity identity) {
		    	this.identity = identity;
		    }
		    public Collection<Identity> getExclusions() {
		        return exclusions;
		    }
		    public void setExclusions(Collection<Identity> exclusions) {
		    	this.exclusions = exclusions;
		    }
		};
		filter = new UserFormFilterAdapter(form);
	}
}
