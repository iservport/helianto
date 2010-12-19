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

package org.helianto.core.criteria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.util.Date;

import org.helianto.core.User;
import org.helianto.core.filter.classic.AbstractDateRangeFilter;
import org.helianto.core.filter.classic.DateRange;
import org.helianto.core.test.SecurityTestSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class CriteriaBuilderTests  {
    
    private CriteriaBuilder criteriaBuilder;
    
    @Test
    public void defaultConstructor() {
    	criteriaBuilder = new CriteriaBuilder();
        DateFormat formatter = criteriaBuilder.getFormatter();
        Date date = new Date(0);
        assertEquals("1969-12-31 21:00:00", formatter.format(date));
        assertEquals("", criteriaBuilder.getPrefix());
    }
    
    @Test
    public void prefixConstructor() {
        DateFormat formatter = criteriaBuilder.getFormatter();
        Date date = new Date(0);
        assertEquals("1969-12-31 21:00:00", formatter.format(date));
        assertEquals("PREFIX", criteriaBuilder.getPrefix());
    }
    
    @Test
    public void dateFormatConstructor() {
        criteriaBuilder = new CriteriaBuilder("OTHER_PREFIX", "ddMMyyyy HHmmss");
        DateFormat formatter = criteriaBuilder.getFormatter();
        Date date = new Date(0);
        assertEquals("31121969 210000", formatter.format(date));
        assertEquals("OTHER_PREFIX", criteriaBuilder.getPrefix());
    }
    
    @Test
    public void dateFormatSetter() {
        criteriaBuilder.setDateFormat("ddMMyyyy HHmmss");
        DateFormat formatter = criteriaBuilder.getFormatter();
        Date date = new Date(0);
        assertEquals("31121969 210000", formatter.format(date));
    }
    
    @Test
    public void createCriteria() {
        criteriaBuilder.createCriteria("OTHER_PREFIX");
        assertEquals("OTHER_PREFIX", criteriaBuilder.getPrefix());
    }
    
    @Test
    public void segmentAppender() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR") instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR ", criteriaBuilder.getCriteriaAsString());
        assertEquals(1, criteriaBuilder.getSegmentCount());
    }
    
    @Test
    public void appendAndFirstSegment() {
        assertTrue(criteriaBuilder.appendAnd() instanceof CriteriaBuilder);
        assertEquals("", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void appendAnd() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR").appendAnd() instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR AND ", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void appendAndFalse() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR").appendAnd(false) instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR ", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void appendOrFirstSegment() {
        assertTrue(criteriaBuilder.appendOr() instanceof CriteriaBuilder);
        assertEquals("", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void appendOr() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR").appendOr() instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR OR ", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void appendOrFalse() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR").appendOr(false) instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR ", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void stringAppender() {
        assertTrue(criteriaBuilder.append("STRING") instanceof CriteriaBuilder);
        assertEquals("STRING ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void stringWithPrefixAppender() {
        assertTrue(criteriaBuilder.appendWithPrefix("STRING") instanceof CriteriaBuilder);
        assertEquals("PREFIX.STRING ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void stringLikeAppender() {
        assertTrue(criteriaBuilder.appendLike("STRING") instanceof CriteriaBuilder);
        assertEquals("'%STRING%' ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void integerAppender() {
        assertTrue(criteriaBuilder.append(Integer.MAX_VALUE) instanceof CriteriaBuilder);
        assertEquals("2147483647 ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void integerArrayAppender() {
    	int[] keys = new int[] { 1, 2, 3 };
        assertTrue(criteriaBuilder.append(keys) instanceof CriteriaBuilder);
        assertEquals("(1, 2, 3) ", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void integerArrayAppenderSingle() {
    	int[] keys = new int[] { 1 };
        assertTrue(criteriaBuilder.append(keys) instanceof CriteriaBuilder);
        assertEquals("(1) ", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void integerArrayAppenderEmpty() {
    	int[] keys = new int[0];
        assertTrue(criteriaBuilder.append(keys) instanceof CriteriaBuilder);
        assertEquals("", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void longAppender() {
        assertTrue(criteriaBuilder.append(Long.MAX_VALUE) instanceof CriteriaBuilder);
        assertEquals("9223372036854775807 ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void longArrayAppender() {
    	long[] keys = new long[] { 1, 2, 3 };
        assertTrue(criteriaBuilder.append(keys) instanceof CriteriaBuilder);
        assertEquals("(1, 2, 3) ", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void longArrayAppenderSingle() {
    	long[] keys = new long[] { 1 };
        assertTrue(criteriaBuilder.append(keys) instanceof CriteriaBuilder);
        assertEquals("(1) ", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void longArrayAppenderEmpty() {
    	long[] keys = new long[0];
        assertTrue(criteriaBuilder.append(keys) instanceof CriteriaBuilder);
        assertEquals("", criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void charAppender() {
        assertTrue(criteriaBuilder.append('A') instanceof CriteriaBuilder);
        assertEquals("'A' ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void dateAppender() {
        assertTrue(criteriaBuilder.append(new Date(0)) instanceof CriteriaBuilder);
        assertEquals("'1969-12-31 21:00:00' ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void subCriteriaAppender() {
        CriteriaBuilder subCriteriaBuilder = new CriteriaBuilder("PREFIX");
        subCriteriaBuilder.append("TEST");
        assertTrue(criteriaBuilder.append(subCriteriaBuilder) instanceof CriteriaBuilder);
        assertEquals("(TEST ) ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void openParenthesis() {
        criteriaBuilder.openParenthesis();
        assertEquals("(",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void openParenthesisFalse() {
        criteriaBuilder.openParenthesis(false);
        assertEquals("",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void closeParenthesis() {
        criteriaBuilder.openParenthesis();
        criteriaBuilder.closeParenthesis();
        assertEquals("() ",criteriaBuilder.getCriteriaAsString());
    }
    
    @Test
    public void closeParenthesisNotOpen() {
        criteriaBuilder.closeParenthesis();
        assertEquals("",criteriaBuilder.getCriteriaAsString());
    }
    
//    @Test
//    public void appendRangeNoDate() {
//    	DateRange filter = new UserBackedFilterStub();
//    	filter.setFromDate(null);
//    	filter.setToDate(null);
//        criteriaBuilder.appendDateRange("field", filter);
//        assertEquals("", criteriaBuilder.getCriteriaAsString());
//    }
//    
//    @Test
//    public void appendRangeFromDate() {
//    	Date fromDate = new Date(0);
//    	DateRange filter = new UserBackedFilterStub();
//    	filter.setFromDate(fromDate);
//    	filter.setToDate(null);
//        criteriaBuilder.appendDateRange("field", filter);
//        assertEquals("(PREFIX.field >= '1969-12-31 21:00:00' ) ", criteriaBuilder.getCriteriaAsString());
//    }
//    
//    @Test
//    public void appendRangeToDate() {
//    	Date toDate = new Date(0);
//    	DateRange filter = new UserBackedFilterStub();
//    	filter.setFromDate(null);
//    	filter.setToDate(toDate);
//        criteriaBuilder.appendDateRange("field", filter);
//        assertEquals("(PREFIX.field < '1969-12-31 21:00:00' ) ", criteriaBuilder.getCriteriaAsString());
//    }
//    
//    @Test
//    public void appendRangeFromDateToDate() {
//    	Date fromDate = new Date(0);
//    	Date toDate = new Date(1000000);
//    	DateRange filter = new UserBackedFilterStub();
//    	filter.setFromDate(fromDate);
//    	filter.setToDate(toDate);
//        criteriaBuilder.appendDateRange("field", filter);
//        assertEquals("(PREFIX.field >= '1969-12-31 21:00:00' AND PREFIX.field < '1969-12-31 21:16:40' ) ", criteriaBuilder.getCriteriaAsString());
//    }
//    
    @Before
    public void setUp() {
        criteriaBuilder = new CriteriaBuilder("PREFIX");
    }
    
    //- user backed filter stub
    
    @SuppressWarnings("serial")
	public class UserBackedFilterStub extends AbstractDateRangeFilter {

        private Date fromDate;
        private Date toDate;

        public User getUser() {
            User user = (User) SecurityTestSupport.createUserDetailsAdapter().getUser();
            user.getEntity().setId(Long.MAX_VALUE);
            return user;
        }
        public void setUser(User user) {}
        public void reset() {}
		public Date getFromDate() { return this.fromDate; }
		public Date getToDate() { return this.toDate; }
		public void setFromDate(Date fromDate) { this.fromDate = fromDate; }
		public void setToDate(Date toDate) { this.toDate = toDate; }
//		public boolean isSelection() { return false; }
//		public String createCriteriaAsString(boolean requireEntity) { return null; }
//		public Entity getEntity() { return null; }
//		public String createCriteriaAsString() { return null; }
//		public String getObjectAlias() { return null; }
//		public List<?> getList() { return null; }
//		public void setList(List<?> itemList) { }
		@Override protected void doFilter(CriteriaBuilder mainCriteriaBuilder) { }
		@Override protected void doSelect(CriteriaBuilder mainCriteriaBuilder) { }
		public String getObjectAlias() { return "ALIAS"; }
    }

}
