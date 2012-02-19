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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.springsource.util.common.StringUtils;


/**
 * Build an EJB-QL string criteria, appropriate to be appended
 * after the "where" keyword in a query.
 * 
 * <p><code>CriteriaBuilder</code> supports nesting, i.e., a
 * <code>CriteriaBuilder</code> instance can be built from 
 * one or more second-level instances. It is not thread-safe.</p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class OrmCriteriaBuilder extends SelectFromBuilder implements CriteriaBuilder {

    static String DEFAULT_SQL_CONVERSION_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static String DEFAULT_AND_CONNECTOR = "AND ";
    static String DEFAULT_OR_CONNECTOR = "OR ";

    private String connector = DEFAULT_AND_CONNECTOR;
    private DateFormat formatter;
    private int openCount = 0;
    private int segmentCount = 0;

    /**
     * Default constructor.
     */
    public OrmCriteriaBuilder() {
        this("", DEFAULT_SQL_CONVERSION_DATE_FORMAT);
    }
    
    /**
     * Default alias constructor.
     * 
     * @param alias
     */
    public OrmCriteriaBuilder(String alias) {
        this(alias, DEFAULT_SQL_CONVERSION_DATE_FORMAT);
    }
    
    /**
     * Class alias constructor.
     * 
     * @param clazz
     * @param alias
     */
    public OrmCriteriaBuilder(Class<?> clazz, String alias) {
        this(alias, DEFAULT_SQL_CONVERSION_DATE_FORMAT);
        setClazz(clazz);
    }
    
    /**
     * Constructor taking a date format string.
     * 
     * @param alias
     * @param dateFormat
     */
    public OrmCriteriaBuilder(String alias, String dateFormat) {
    	super();
        setDateFormat(dateFormat);
        createCriteria(alias);
    }
    
    //--
    
    /**
     * Prints the output as a string.
     */
    public String getCriteriaAsString() {
        return getInternalBuilder().toString();
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
     * @param alias
     */
    public void createCriteria(String alias) {
    	getNewInternalBuilder();
        setAlias(alias);
    }

    /**
     * Filed name appender that can suppress the initial connector.
     * 
     * @param fieldName
     * @param sqlOperator
     */
    public OrmCriteriaBuilder appendSegment(String fieldName, String sqlOperator) {
        return appendSegment(fieldName, sqlOperator, "");
    }
    
    /**
     * Filed name appender that can suppress the initial connector.
     * 
     * @param fieldName
     * @param sqlOperator
     * @param sqlFunction
     */
    public OrmCriteriaBuilder appendSegment(String fieldName, String sqlOperator, String sqlFunction) {
        segmentCount++;
        if (!sqlFunction.equals("")) {
            getInternalBuilder().append(sqlFunction).append("(");
        }
        if (getAlias()!=null && getAlias().length()==0) {
        	getInternalBuilder().append(fieldName);
        }
        else {
        	getInternalBuilder().append(getAlias())
            .append(".")
            .append(fieldName);
        }
        if (!sqlFunction.equals("")) {
        	getInternalBuilder().append(")");
        }
        getInternalBuilder().append(" ")
            .append(sqlOperator)
            .append(" ");
        return this;
    }
    
    /**
     * And appender.
     */
    public OrmCriteriaBuilder appendAnd() {
        if (segmentCount>0) {
        	getInternalBuilder().append(DEFAULT_AND_CONNECTOR);
        }
        return this;
    }
    
    /**
     * Conditional and appender.
     */
    public OrmCriteriaBuilder appendAnd(boolean condition) {
        if (condition) {
            return appendAnd();
        }
        return this;
    }
    
    /**
     * Or appender.
     */
    public OrmCriteriaBuilder appendOr() {
        if (segmentCount>0) {
        	getInternalBuilder().append(DEFAULT_OR_CONNECTOR);
        }
        return this;
    }
    
    /**
     * Conditional or appender.
     */
    public OrmCriteriaBuilder appendOr(boolean condition) {
        if (condition) {
            return appendOr();
        }
        return this;
    }
    
    @Override
    public OrmCriteriaBuilder append(String content) {
    	return (OrmCriteriaBuilder) super.append(content);
    }

    /**
     * Class appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder append(Class<?> clazz) {
    	if (clazz!=null) {
    		getInternalBuilder().append(getAlias())
            .append(".class=")
            .append(clazz.getSimpleName())
            .append(" ");
    	}
        return this;
    }

    /**
     * String appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder appendString(String content) {
    	getInternalBuilder().append("'")
        .append(content)
        .append("' ");
        return this;
    }

    /**
     * String appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder appendLike(String content) {
    	getInternalBuilder().append("'%").append(content).append("%' ");
        return this;
    }

    /**
     * String appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder appendStartLike(String content) {
    	getInternalBuilder().append("'").append(content).append("%' ");
        return this;
    }

    /**
     * Integer appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder append(int content) {
    	getInternalBuilder().append(content)
        .append(" ");
        return this;
    }

    /**
     * Integer array appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder append(int[] content) {
    	if (content.length>0) {
    		getInternalBuilder().append(Arrays.toString(content).replace("[", "(").replace("]", ")"))
            .append(" ");
    	}
        return this;
    }

    /**
     * String array appender.
     * 
     * @param content
     * @param encloseWithParenthesis
     */
    public OrmCriteriaBuilder append(String[] content, boolean encloseWithParenthesis) {
    	if (content!=null && content.length>0) {
    		String contentArray = new StringBuilder(encloseWithParenthesis ? "(" : "")
    		.append("'")
    		.append(StringUtils.arrayToDelimitedString(content, "','"))
    		.append("'")
    		.append(encloseWithParenthesis ? ")" : "")
    		.toString();
    		getInternalBuilder().append(contentArray)
            .append(" ");
    	}
        return this;
    }

    /**
     * Long appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder append(long content) {
    	getInternalBuilder().append(content)
        .append(" ");
        return this;
    }

    /**
     * Long array appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder append(long[] content) {
    	if (content.length>0) {
    		getInternalBuilder().append(Arrays.toString(content).replace("[", "(").replace("]", ")"))
            .append(" ");
    	}
        return this;
    }

    /**
     * Char appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder append(char content) {
    	getInternalBuilder().append("'").append(content).append("' ");
        return this;
    }
    
    /**
     * Date appender.
     * 
     * @param content
     */
    public OrmCriteriaBuilder append(Date content) {
    	getInternalBuilder().append("'").append(formatter.format(content)).append("' ");
        return this;
    }
    
    /**
     * Where appender.
     * 
     * @param fieldName
     * @param sqlOperator
     * @return "where ${alias}.${field0}, ${alias}.${field1} "
     */
    public OrmCriteriaBuilder appendWhere(String fieldName, String sqlOperator) {
    	getInternalBuilder().append("where ");
   		appendSegment(fieldName, sqlOperator);
    	return this;
    }

    /**
     * Group by appender.
     * 
     * @param fields
     * @return "group by ${alias}.${fieldName0}, ${alias}.${fieldName1} "
     */
    public OrmCriteriaBuilder appendGroupBy(String... fieldNames) {
    	String separator = "";
    	getInternalBuilder().append("group by");
    	for (String fieldName: fieldNames) {
    		append(separator).appendWithPrefix(fieldName);
    		separator = ", ";
    	}
    	return this;
    }

    /**
     * Order by appender.
     * 
     * @param fields
     * @return "order by ${alias}.${fieldName0}, ${alias}.${fieldName1} "
     */
    public OrmCriteriaBuilder appendOrderBy(String... fieldNames) {
    	String separator = "";
    	getInternalBuilder().append("order by");
    	for (String fieldName: fieldNames) {
    		append(separator).appendWithPrefix(fieldName);
    		separator = ", ";
    	}
    	return this;
    }

    /**
     * Sub criteria appender.
     * 
     * @param criteriaBuilder
     */
    public OrmCriteriaBuilder append(OrmCriteriaBuilder criteriaBuilder) {
        segmentCount++;
        openParenthesis();
        getInternalBuilder().append(criteriaBuilder.getCriteriaAsString());
        closeParenthesis();
        return this;
    }

    /**
     * Open parenthesis and increment a parenteheses counter.
     */
    public OrmCriteriaBuilder openParenthesis() {
    	getInternalBuilder().append("(");
        openCount++;
        return this;
    }

    /**
     * Open parenthesis and increment a parenteheses counter
     * if a condition is true.
     */
    public OrmCriteriaBuilder openParenthesis(boolean condition) {
        if (condition) {
            openParenthesis();
        }
        return this;
    }

    /**
     * If parenteheses counter is positive, close parenthesis and 
     * decrement it.
     */
    public void closeParenthesis() {
        if (openCount > 0) {
        	getInternalBuilder().append(") ");
            openCount--;
        }
    } 
    
    //- property getters

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
    
    /**
     * Add count to segment count.
     * 
     * @param count
     */
    public void addSegmentCount(int count) {
    	segmentCount += count;
    }
    
}
