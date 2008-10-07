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

package org.helianto.core.filter;

import java.text.DateFormat;
import java.util.Date;

import org.helianto.core.User;
import org.helianto.core.test.SecurityTestSupport;

import junit.framework.TestCase;

/**
 * @author Mauricio Fernandes de Castro
 */
public class CriteriaBuilderTests extends TestCase {
    
    private CriteriaBuilder criteriaBuilder;
    
    public void testDefaultConstructor() {
    	criteriaBuilder = new CriteriaBuilder();
        DateFormat formatter = criteriaBuilder.getFormatter();
        Date date = new Date(0);
        assertEquals("1969-12-31 21:00:00", formatter.format(date));
        assertEquals("", criteriaBuilder.getPrefix());
    }
    
    public void testPrefixConstructor() {
        DateFormat formatter = criteriaBuilder.getFormatter();
        Date date = new Date(0);
        assertEquals("1969-12-31 21:00:00", formatter.format(date));
        assertEquals("PREFIX", criteriaBuilder.getPrefix());
    }
    
    public void testDateFormatConstructor() {
        criteriaBuilder = new CriteriaBuilder("OTHER_PREFIX", "ddMMyyyy HHmmss");
        DateFormat formatter = criteriaBuilder.getFormatter();
        Date date = new Date(0);
        assertEquals("31121969 210000", formatter.format(date));
        assertEquals("OTHER_PREFIX", criteriaBuilder.getPrefix());
    }
    
    public void testDateFormatSetter() {
        criteriaBuilder.setDateFormat("ddMMyyyy HHmmss");
        DateFormat formatter = criteriaBuilder.getFormatter();
        Date date = new Date(0);
        assertEquals("31121969 210000", formatter.format(date));
    }
    
    public void testCreateCriteria() {
        criteriaBuilder.createCriteria("OTHER_PREFIX");
        assertEquals("OTHER_PREFIX", criteriaBuilder.getPrefix());
    }
    
    public void testAppendEntityFromUserBackedFilter() {
        UserBackedFilter userBackedFilter = new UserBackedFilterStub();
        criteriaBuilder.appendEntityFromUserBackedFilter(userBackedFilter);
        assertEquals("PREFIX.entity.id = 9223372036854775807 ", criteriaBuilder.getCriteriaAsString());
    }
    
    public void testSegmentAppender() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR") instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR ", criteriaBuilder.getCriteriaAsString());
        assertEquals(1, criteriaBuilder.getSegmentCount());
    }
    
    public void testAppendAndFirstSegment() {
        assertTrue(criteriaBuilder.appendAnd() instanceof CriteriaBuilder);
        assertEquals("", criteriaBuilder.getCriteriaAsString());
    }
    
    public void testAppendAnd() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR").appendAnd() instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR AND ", criteriaBuilder.getCriteriaAsString());
    }
    
    public void testAppendAndFalse() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR").appendAnd(false) instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR ", criteriaBuilder.getCriteriaAsString());
    }
    
    public void testAppendOrFirstSegment() {
        assertTrue(criteriaBuilder.appendOr() instanceof CriteriaBuilder);
        assertEquals("", criteriaBuilder.getCriteriaAsString());
    }
    
    public void testAppendOr() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR").appendOr() instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR OR ", criteriaBuilder.getCriteriaAsString());
    }
    
    public void testAppendOrFalse() {
        assertTrue(criteriaBuilder.appendSegment("FIELDNAME", "OPERATOR").appendOr(false) instanceof CriteriaBuilder);
        assertEquals("PREFIX.FIELDNAME OPERATOR ", criteriaBuilder.getCriteriaAsString());
    }
    
    public void testStringAppender() {
        assertTrue(criteriaBuilder.append("STRING") instanceof CriteriaBuilder);
        assertEquals("STRING ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testStringWithPrefixAppender() {
        assertTrue(criteriaBuilder.appendWithPrefix("STRING") instanceof CriteriaBuilder);
        assertEquals("PREFIX.STRING ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testStringLikeAppender() {
        assertTrue(criteriaBuilder.appendLike("STRING") instanceof CriteriaBuilder);
        assertEquals("'%STRING%' ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testIntegerAppender() {
        assertTrue(criteriaBuilder.append(Integer.MAX_VALUE) instanceof CriteriaBuilder);
        assertEquals("2147483647 ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testLongAppender() {
        assertTrue(criteriaBuilder.append(Long.MAX_VALUE) instanceof CriteriaBuilder);
        assertEquals("9223372036854775807 ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testCharAppender() {
        assertTrue(criteriaBuilder.append('A') instanceof CriteriaBuilder);
        assertEquals("'A' ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testDateAppender() {
        assertTrue(criteriaBuilder.append(new Date(0)) instanceof CriteriaBuilder);
        assertEquals("'1969-12-31 21:00:00' ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testSubCriteriaAppender() {
        CriteriaBuilder subCriteriaBuilder = new CriteriaBuilder("PREFIX");
        subCriteriaBuilder.append("TEST");
        assertTrue(criteriaBuilder.append(subCriteriaBuilder) instanceof CriteriaBuilder);
        System.out.println(criteriaBuilder.getCriteriaAsString());
        assertEquals("(TEST ) ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testOpenParenthesis() {
        criteriaBuilder.openParenthesis();
        assertEquals("(",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testOpenParenthesisFalse() {
        criteriaBuilder.openParenthesis(false);
        assertEquals("",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testCloseParenthesis() {
        criteriaBuilder.openParenthesis();
        criteriaBuilder.closeParenthesis();
        assertEquals("() ",criteriaBuilder.getCriteriaAsString());
    }
    
    public void testCloseParenthesisNotOpen() {
        criteriaBuilder.closeParenthesis();
        System.out.println(criteriaBuilder.getCriteriaAsString());
        assertEquals("",criteriaBuilder.getCriteriaAsString());
    }
    
    @Override
    public void setUp() {
        criteriaBuilder = new CriteriaBuilder("PREFIX");
    }
    
    //- user backed filter stub
    
    public class UserBackedFilterStub implements UserBackedFilter {

        public User getUser() {
            User user = SecurityTestSupport.createUserDetailsAdapter().getUser();
            user.getEntity().setId(Long.MAX_VALUE);
            return user;
        }

        public void setUser(User arg0) {}
        
        public void reset() {}
        
    }

}
