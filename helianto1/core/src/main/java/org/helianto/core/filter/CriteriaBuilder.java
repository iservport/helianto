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
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Build an EJB-QL string criteria, appropriate to be appended
 * after the "where" keyword in a query.
 * 
 * <p><code>CriteriaBuilder</code> supports nesting, i.e., a
 * <code>CriteriaBuilder</code> instance can be built from 
 * one or more second-level instances.</p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CriteriaBuilder {

    static String DEFAULT_SQL_CONVERSION_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static String DEFAULT_AND_CONNECTOR = "AND ";
    static String DEFAULT_OR_CONNECTOR = "OR ";

    private String prefix;
    private String connector = DEFAULT_AND_CONNECTOR;
    private StringBuilder criteria;
    private DateFormat formatter;
    private int openCount = 0;
    private int segmentCount = 0;

    /**
     * Default constructor.
     */
    public CriteriaBuilder(String prefix) {
        this(prefix, DEFAULT_SQL_CONVERSION_DATE_FORMAT);
    }
    
    /**
     * Constructor taking a date format string.
     */
    public CriteriaBuilder(String prefix, String dateFormat) {
        setDateFormat(dateFormat);
        createCriteria(prefix);
    }
    
    /**
     * Set the date format.
     * 
     * @param dateFormat
     */
    public void setDateFormat(String dateFormat) {
        formatter = new SimpleDateFormat(dateFormat);
    }

    /**
     * Initialize a criteria.
     * 
     * @param prefix
     */
    public void createCriteria(String prefix) {
        this.prefix = prefix;
        criteria = new StringBuilder();
    }

    /**
     * Append an <code>Entity</code> filter if a <code>User</code> is present, or throws 
     * <code>IllegalArgumentException</code>.
     * 
     * @param filter
     */
    public CriteriaBuilder appendEntityFromUserBackedFilter(UserBackedFilter filter) {
        if (filter.getUser()==null) {
            throw new IllegalArgumentException("User required!");
        }
        appendSegment("entity.id", "=")
        .append(filter.getUser().getEntity().getId());
        return this;
    }
    
    /**
     * Filed name appender that can suppress the initial connector.
     * 
     * @param fieldName
     * @param sqlOperator
     */
    public CriteriaBuilder appendSegment(String fieldName, String sqlOperator) {
        return appendSegment(fieldName, sqlOperator, "");
    }
    
    /**
     * Filed name appender that can suppress the initial connector.
     * 
     * @param fieldName
     * @param sqlOperator
     * @param sqlFunction
     */
    public CriteriaBuilder appendSegment(String fieldName, String sqlOperator, String sqlFunction) {
        segmentCount++;
        if (!sqlFunction.equals("")) {
            criteria.append(sqlFunction).append("(");
        }
        criteria.append(prefix).append(".")
            .append(fieldName);
        if (!sqlFunction.equals("")) {
            criteria.append(")");
        }
        criteria.append(" ")
            .append(sqlOperator)
            .append(" ");
        return this;
    }
    
    /**
     * And appender.
     */
    public CriteriaBuilder appendAnd() {
        if (segmentCount>0) {
            criteria.append(DEFAULT_AND_CONNECTOR);
        }
        return this;
    }
    
    /**
     * Conditional and appender.
     */
    public CriteriaBuilder appendAnd(boolean condition) {
        if (condition) {
            return appendAnd();
        }
        return this;
    }
    
    /**
     * Or appender.
     */
    public CriteriaBuilder appendOr() {
        if (segmentCount>0) {
            criteria.append(DEFAULT_OR_CONNECTOR);
        }
        return this;
    }
    
    /**
     * Conditional or appender.
     */
    public CriteriaBuilder appendOr(boolean condition) {
        if (condition) {
            return appendOr();
        }
        return this;
    }

    /**
     * String appender.
     * 
     * @param content
     */
    public CriteriaBuilder append(String content) {
        criteria.append(content)
        .append(" ");
        return this;
    }

    /**
     * String appender.
     * 
     * @param content
     */
    public CriteriaBuilder appendString(String content) {
        criteria.append("'")
        .append(content)
        .append("' ");
        return this;
    }

    /**
     * String appender.
     * 
     * @param content
     */
    public CriteriaBuilder appendLike(String content) {
        criteria.append("'%").append(content).append("%' ");
        return this;
    }

    /**
     * Integer appender.
     * 
     * @param content
     */
    public CriteriaBuilder append(int content) {
        criteria.append(content)
        .append(" ");
        return this;
    }

    /**
     * Long appender.
     * 
     * @param content
     */
    public CriteriaBuilder append(long content) {
        criteria.append(content)
        .append(" ");
        return this;
    }

    /**
     * Char appender.
     * 
     * @param content
     */
    public CriteriaBuilder append(char content) {
        criteria.append("'").append(content).append("' ");
        return this;
    }
    
    /**
     * Date appender.
     * 
     * @param content
     */
    public CriteriaBuilder append(Date content) {
        criteria.append("'").append(formatter.format(content)).append("' ");
        return this;
    }

    /**
     * Sub criteria appender.
     * 
     * @param criteriaBuilder
     */
    public CriteriaBuilder append(CriteriaBuilder criteriaBuilder) {
        segmentCount++;
        openParenthesis();
        criteria.append(criteriaBuilder.getCriteriaAsString());
        closeParenthesis();
        return this;
    }

    /**
     * Open parenthesis and increment a parenteheses counter.
     */
    public void openParenthesis() {
        criteria.append("(");
        openCount++;
    }

    /**
     * Open parenthesis and increment a parenteheses counter
     * if a condition is true.
     */
    public void openParenthesis(boolean condition) {
        if (condition) {
            openParenthesis();
        }
    }

    /**
     * If parenteheses counter is positive, close parenthesis and 
     * decrement it.
     */
    public void closeParenthesis() {
        if (openCount > 0) {
            criteria.append(") ");
            openCount--;
        }
    }
    
    /**
     * Prints the output as a string.
     * @return
     */
    public String getCriteriaAsString() {
        return criteria.toString();
    }

    //- property getters

    /**
     * Prefix getter
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Connector getter
     */
    public String getConnector() {
        return connector;
    }

    /**
     * Formatter getter
     */
    public DateFormat getFormatter() {
        return formatter;
    }

    /**
     * OpenCount getter
     */
    public int getOpenCount() {
        return openCount;
    }

    /**
     * SegmentCount getter
     */
    public int getSegmentCount() {
        return segmentCount;
    }

}
