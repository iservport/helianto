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

package org.helianto.core.criteria.classic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


/**
 * Classic implementation to build an EJB-QL string criteria, appropriate to be appended
 * after the "where" keyword in a query.
 * 
 * <p><code>CriteriaBuilder</code> supports nesting, i.e., a
 * <code>CriteriaBuilder</code> instance can be built from 
 * one or more second-level instances. It is not thread-safe.</p>
 * 
 * @author Mauricio Fernandes de Castro
 * @see {@link org.helianto.core.criteria.CriteriaBuilder}
 */
public class CriteriaBuilderClassic {

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
    public CriteriaBuilderClassic() {
        this("", DEFAULT_SQL_CONVERSION_DATE_FORMAT);
    }
    
    /**
     * Default prefix constructor.
     * 
     * @param prefix
     */
    public CriteriaBuilderClassic(String prefix) {
        this(prefix, DEFAULT_SQL_CONVERSION_DATE_FORMAT);
    }
    
    /**
     * Constructor taking a date format string.
     * 
     * @param prefix
     * @param dateFormat
     */
    public CriteriaBuilderClassic(String prefix, String dateFormat) {
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
     * Filed name appender that can suppress the initial connector.
     * 
     * @param fieldName
     * @param sqlOperator
     */
    public CriteriaBuilderClassic appendSegment(String fieldName, String sqlOperator) {
        return appendSegment(fieldName, sqlOperator, "");
    }
    
    /**
     * Filed name appender that can suppress the initial connector.
     * 
     * @param fieldName
     * @param sqlOperator
     * @param sqlFunction
     */
    public CriteriaBuilderClassic appendSegment(String fieldName, String sqlOperator, String sqlFunction) {
        segmentCount++;
        if (!sqlFunction.equals("")) {
            criteria.append(sqlFunction).append("(");
        }
        if (prefix!=null && prefix.length()==0) {
            criteria.append(fieldName);
        }
        else {
            criteria.append(prefix)
            .append(".")
            .append(fieldName);
        }
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
    public CriteriaBuilderClassic appendAnd() {
        if (segmentCount>0) {
            criteria.append(DEFAULT_AND_CONNECTOR);
        }
        return this;
    }
    
    /**
     * Conditional and appender.
     */
    public CriteriaBuilderClassic appendAnd(boolean condition) {
        if (condition) {
            return appendAnd();
        }
        return this;
    }
    
    /**
     * Or appender.
     */
    public CriteriaBuilderClassic appendOr() {
        if (segmentCount>0) {
            criteria.append(DEFAULT_OR_CONNECTOR);
        }
        return this;
    }
    
    /**
     * Conditional or appender.
     */
    public CriteriaBuilderClassic appendOr(boolean condition) {
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
    public CriteriaBuilderClassic append(String content) {
        criteria.append(content)
        .append(" ");
        return this;
    }

    /**
     * String appender.
     * 
     * @param content
     */
    public CriteriaBuilderClassic appendWithPrefix(String content) {
    	if (prefix.length()>0) {
            criteria.append(prefix)
            .append(".");
    	}
    	criteria.append(content)
            .append(" ");
        return this;
    }

    /**
     * Class appender.
     * 
     * @param content
     */
    public CriteriaBuilderClassic append(Class<?> clazz) {
    	if (clazz!=null) {
            criteria.append(prefix)
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
    public CriteriaBuilderClassic appendString(String content) {
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
    public CriteriaBuilderClassic appendLike(String content) {
        criteria.append("'%").append(content).append("%' ");
        return this;
    }

    /**
     * Integer appender.
     * 
     * @param content
     */
    public CriteriaBuilderClassic append(int content) {
        criteria.append(content)
        .append(" ");
        return this;
    }

    /**
     * Integer array appender.
     * 
     * @param content
     */
    public CriteriaBuilderClassic append(int[] content) {
    	if (content.length>0) {
            criteria.append(Arrays.toString(content).replace("[", "(").replace("]", ")"))
            .append(" ");
    	}
        return this;
    }

    /**
     * Long appender.
     * 
     * @param content
     */
    public CriteriaBuilderClassic append(long content) {
        criteria.append(content)
        .append(" ");
        return this;
    }

    /**
     * Long array appender.
     * 
     * @param content
     */
    public CriteriaBuilderClassic append(long[] content) {
    	if (content.length>0) {
            criteria.append(Arrays.toString(content).replace("[", "(").replace("]", ")"))
            .append(" ");
    	}
        return this;
    }

    /**
     * Char appender.
     * 
     * @param content
     */
    public CriteriaBuilderClassic append(char content) {
        criteria.append("'").append(content).append("' ");
        return this;
    }
    
    /**
     * Date appender.
     * 
     * @param content
     */
    public CriteriaBuilderClassic append(Date content) {
        criteria.append("'").append(formatter.format(content)).append("' ");
        return this;
    }
    
    /**
     * Select appender.
     * 
     * @param fieldNames
     * @return "select ${prefix}.${fieldName0}, ${prefix}.${fieldName1} "
     */
    public CriteriaBuilderClassic appendSelect(String... fieldNames) {
    	String separator = "";
    	criteria.append("select");
    	if (fieldNames.length>0) {
        	for (String fieldName: fieldNames) {
        		append(separator).appendWithPrefix(fieldName);
        		separator = ", ";
        	}
    	}
    	else {
    		criteria.append(" ")
        	.append(prefix)
        	.append(" ");
    	}
    	return this;
    }

    /**
     * From class appender.
     * 
     * @param clazz
     * @return "from ${clazz.simpleName} ${prefix} "
     */
    public CriteriaBuilderClassic appendFrom(Class<?> clazz) {
    	criteria.append("from ")
    	.append(clazz.getSimpleName())
        .append(" ")
    	.append(prefix)
    	.append(" ");
    	return this;
    }

    /**
     * Where appender.
     * 
     * @param fieldName
     * @param sqlOperator
     * @return "where ${prefix}.${field0}, ${prefix}.${field1} "
     */
    public CriteriaBuilderClassic appendWhere(String fieldName, String sqlOperator) {
    	criteria.append("where ");
   		appendSegment(fieldName, sqlOperator);
    	return this;
    }

    /**
     * Group by appender.
     * 
     * @param fields
     * @return "group by ${prefix}.${fieldName0}, ${prefix}.${fieldName1} "
     */
    public CriteriaBuilderClassic appendGroupBy(String... fieldNames) {
    	String separator = "";
    	criteria.append("group by");
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
     * @return "order by ${prefix}.${fieldName0}, ${prefix}.${fieldName1} "
     */
    public CriteriaBuilderClassic appendOrderBy(String... fieldNames) {
    	String separator = "";
    	criteria.append("order by");
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
    public CriteriaBuilderClassic append(CriteriaBuilderClassic criteriaBuilder) {
        segmentCount++;
        openParenthesis();
        criteria.append(criteriaBuilder.getCriteriaAsString());
        closeParenthesis();
        return this;
    }

    /**
     * Open parenthesis and increment a parenteheses counter.
     */
    public CriteriaBuilderClassic openParenthesis() {
        criteria.append("(");
        openCount++;
        return this;
    }

    /**
     * Open parenthesis and increment a parenteheses counter
     * if a condition is true.
     */
    public CriteriaBuilderClassic openParenthesis(boolean condition) {
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
            criteria.append(") ");
            openCount--;
        }
    } 
    
//    /**
//     * Append a date range.
//     * 
//     * @param fieldName
//     * @param dateRange
//     * @deprecated see AbstractDateRangeFilterAdapter
//     */
//    public void appendDateRange(String fieldName, DateRange dateRange) {
//    	if (dateRange.getFromDate()==null && dateRange.getToDate()==null) {
//    		return;
//    	}
//    	appendAnd().openParenthesis(true);
//        if (dateRange.getFromDate()!=null) {
//        	appendSegment(fieldName, ">=").append(dateRange.getFromDate());
//        }
//        if (dateRange.getToDate()!=null) {
//        	appendAnd(dateRange.getFromDate()!=null)
//            .appendSegment(fieldName, "<")
//            .append(dateRange.getToDate());
//        }
//        closeParenthesis();
//    }
//    
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
