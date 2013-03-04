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

package org.helianto.core.filter.base;


import java.io.Serializable;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.CriteriaFilter;
import org.helianto.core.filter.ParentFilter;
import org.helianto.core.filter.PolymorphicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to create criteria using alias and a criteria builder.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
public abstract class AbstractAliasFilter 

	implements Serializable
	, CriteriaFilter<OrmCriteriaBuilder>
	, Cloneable 

{
	
    private String objectAlias;
    private OrmCriteriaBuilder mainCriteriaBuilder;
    private String orderByString = "";
    
    /**
     * Default constructor.
     */
    public AbstractAliasFilter() {
    	this("alias");
    }
    
    /**
     * Alias constructor.
     * 
     * @param alias
     */
    public AbstractAliasFilter(String alias) {
    	setObjectAlias(alias);
    }
    
    /**
     * Provide a name to be used by the filter.
     */
    public String getObjectAlias() {
    	return this.objectAlias;
    }
    public void setObjectAlias(String objectAlias) {
		this.objectAlias = objectAlias;
	}
    
    /**
     * The main criteria builder.
     */
    public OrmCriteriaBuilder getMainCriteriaBuilder() {
		return mainCriteriaBuilder;
	}
    public void setMainCriteriaBuilder(OrmCriteriaBuilder mainCriteriaBuilder) {
		this.mainCriteriaBuilder = mainCriteriaBuilder;
	}
    
    public String createSelectAsString() {
    	return null;
    }
    
	/**
	 * Delegate criteria creation to a chain of criteria builder processors.
	 * 
	 * <p>
	 * If this filter has an embedded criteria builder, use it, otherwise create a new one,
	 * </p> 
	 */
	public String createCriteriaAsString() {
		if (getMainCriteriaBuilder()!=null) {
			return createCriteriaAsString(getMainCriteriaBuilder());
		}
        return createCriteriaAsString(new OrmCriteriaBuilder(getObjectAlias()));
    }
	
	/**
	 * Delegate criteria creation to a chain of criteria builder processors.
	 * 
	 * @param mainCriteriaBuilder
	 */
	public String createCriteriaAsString(OrmCriteriaBuilder mainCriteriaBuilder) {
        preProcessFilter(mainCriteriaBuilder);
        if (isSelection()) {
        	logger.debug("Filter in 'selection' mode.");
        	doSelect(mainCriteriaBuilder);
        	postSelection();
        }
        else if (isSearch()) {
        	logger.debug("Filter in 'search' mode.");
        	doSearch(mainCriteriaBuilder);
        	postSearch(mainCriteriaBuilder);
        }
        else {
        	logger.debug("Filter in 'filter' mode.");
        	doFilter(mainCriteriaBuilder);
            postProcessFilter(mainCriteriaBuilder);
        }
        logger.debug("Filter query: {}", mainCriteriaBuilder.getCriteriaAsString());
        return mainCriteriaBuilder.getCriteriaAsString();
    }
	
    /**
     * If true, a unique result is expected, otherwise, a collection.
     * 
     * <p>
     * Convenient when filter properties correspond to the natural key. By default, filters do not return an unique result.
     * </p>
     */
	public boolean isSelection() {
		return false;
	}
	
	/**
	 * Called after {@link #doSelect(OrmCriteriaBuilder)}
	 */
	protected void postSelection() {
		
	}
	
    /**
     * If true, a search result is expected.
     */
	public boolean isSearch() {
		return false;
	}
	
	/**
	 * Called after {@link #doSearch(OrmCriteriaBuilder)
	 */
	protected void postSearch(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendOrderBy(getOrderByString(), (OrmCriteriaBuilder) mainCriteriaBuilder);
	}
	
	// processors
	
	/**
	 * Subclasses overriding this method should create query segments
	 * to be included for both selection and filter operations.
	 * 
	 * @param filter
	 * @param mainCriteriaBuilder
	 */
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = false;
		if (hasPolimorphicCriterion()) {
			mainCriteriaBuilder.appendAnd(connect);
			connect = true;
			preProcessPolimorphicFilter(mainCriteriaBuilder);
		}
		if (hasParentCriterion()) {
			mainCriteriaBuilder.appendAnd(connect);
			connect = true;
			preProcessParentFilter(mainCriteriaBuilder);
		}
		if (hasNavigableCriterion()) {
			mainCriteriaBuilder.appendAnd(connect);
			connect = true;
			preProcessNavigableFilter(mainCriteriaBuilder);
		}
		return connect;
	}
	
	/**
	 * Polimorphic pre-processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void preProcessPolimorphicFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.append(((PolymorphicFilter<?>) this).getClazz());
		logger.debug("Class restriction applied using {}.", ((PolymorphicFilter<?>) this).getClazz());
	}
	
	/**
	 * Parent pre-processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void preProcessParentFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter(getParentName().append(".id").toString(), ((ParentFilter) this).getParentId(), mainCriteriaBuilder);
		logger.debug("Parent restriction applied using {}.", ((ParentFilter) this).getParent());
	}
	
	/**
	 * Navigable pre-processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	public void preProcessNavigableFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		// Delegate to subclasses, if needed
	}
	
	/**
	 * True if there is a segment for polimorphic criterion.
	 */
	protected boolean hasPolimorphicCriterion() {
		return this instanceof PolymorphicFilter<?> && ((PolymorphicFilter<?>) this).getClazz()!=null;
	}
	
	/**
	 * True if there is a segment for parent criterion.
	 */
	protected boolean hasParentCriterion() {
		return this instanceof ParentFilter && ((ParentFilter) this).getParentId()>0;
	}
	
	protected boolean hasNavigableCriterion() {
		return false;
	}
	
	/**
	 * Convenience to return a builder initialized with the "parent" field string to
	 * be used in polymorphic filters.
	 */
	protected StringBuilder getParentName() {
		return new StringBuilder("parent");
	}
	
	/**
	 * Hook to the selection processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected abstract void doSelect(OrmCriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Hook to the search processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void doSearch(OrmCriteriaBuilder mainCriteriaBuilder) {
		String[] searchWords = getSearchWords();
		String[] fieldNames = getFieldNames();
		OrmCriteriaBuilder searchCriteriaBuilder = new OrmCriteriaBuilder(mainCriteriaBuilder.getAlias());
		for (String fieldName: fieldNames) {
			OrmCriteriaBuilder fieldCriteriaBuilder = new OrmCriteriaBuilder(mainCriteriaBuilder.getAlias());
			for (String fieldContent: searchWords) {
		    	if (fieldContent!=null && fieldContent.length()>0) {
		    		fieldCriteriaBuilder.appendOr().appendSegment(fieldName, "like", "lower")
		            .appendLike(fieldContent.toLowerCase());
		        }
			}
			searchCriteriaBuilder.appendOr().append(fieldCriteriaBuilder);
		}
		mainCriteriaBuilder.appendAnd().append(searchCriteriaBuilder);
	}
	
	/**
	 * Array of field names to be searched.
	 */
	protected String[] getFieldNames() {
		return new String[0];
	}
	
	/**
	 * Array of words to search in fields.
	 */
	protected String[] getSearchWords() {
		return new String[0];
	}
	
	/**
	 * Hook to the filter processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	public abstract void doFilter(OrmCriteriaBuilder mainCriteriaBuilder);
	
	/**
	 * Hook to the filter post-processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
	protected void postProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendOrderBy(getOrderByString(), (OrmCriteriaBuilder) mainCriteriaBuilder);
	}
		
	// appenders
	
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, String fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=")
            .appendString(fieldContent);
        }
    }
    
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualLessCaseFilter(String fieldName, String fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=", "lower")
            .appendString(fieldContent.toLowerCase());
        }
    }
    
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, int fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	appendEqualFilter(fieldName, fieldContent, false, criteriaBuilder);
    }
    
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param ignoreOnlyIfNegative
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, int fieldContent, boolean ignoreOnlyIfNegative, OrmCriteriaBuilder criteriaBuilder) {
    	if ((!ignoreOnlyIfNegative && fieldContent>0) | (ignoreOnlyIfNegative && fieldContent>=0)) {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=")
            .append(fieldContent);
        }
    }
    
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, long fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent>0) {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=")
            .append(fieldContent);
        }
    }
    
    /**
     * Equal appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendEqualFilter(String fieldName, char fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=0 && fieldContent!=' ' && fieldContent!='_') {
            criteriaBuilder.appendAnd().appendSegment(fieldName, "=")
            .append(fieldContent);
        }
    }
    
    /**
     * Case sensitive like appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendLikeCaseFilter(String fieldName, String fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
    		criteriaBuilder.appendAnd().appendSegment(fieldName, "like")
            .appendLike(fieldContent);
        }
    }
    
    /**
     * Case insensitive like appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendLikeFilter(String fieldName, String fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
    		criteriaBuilder.appendAnd().appendSegment(fieldName, "like", "lower")
            .appendLike(fieldContent.toLowerCase());
        }
    }
    
    /**
     * Case insensitive start like appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendStartLikeFilter(String fieldName, String fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
    		criteriaBuilder.appendAnd().appendSegment(fieldName, "like", "lower")
            .appendStartLike(fieldContent.toLowerCase());
        }
    }
    
    /**
     * Integer array in appender.
     * 
     * @param fieldName
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendInArray(String fieldName, int[] fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length>0) {
    		criteriaBuilder.appendAnd().appendSegment(fieldName, "in")
            .append(fieldContent);
        }
    }
    
    /**
     * Helper method to convert a string array into an integer array.
     * 
     * <p>
     * May be used if a parameter array is received as string array and needs to be converted before 
     * {@link #appendInArray(String, int[], OrmCriteriaBuilder)} is called.
     * </p>
     * 
     * @param fieldContent
     */
    public static int[] stringArrayConverter(String[] fieldContent) {
    	if (fieldContent==null) {
        	return new int[0];
        }
    	int[] integerArray = new int[fieldContent.length];
    	String toParse = "";
    	try {
    		for (int i=0; i<integerArray.length; i++) {
    			toParse = fieldContent[i];
    			integerArray[i] = Integer.parseInt(toParse);
    		}
    	}
    	catch (Exception e) {
    		logger.warn("Unable to parse {} as integer.", toParse);
    	}
    	return integerArray;
    }
    
    /**
     * Base order by segment.
     * 
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendOrderBy(String fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	if (fieldContent!=null && fieldContent.length()>0) {
        	String[] fieldNames = fieldContent.split(",");
        	if (fieldNames.length>0) {
        		criteriaBuilder.appendOrderBy(fieldNames);
            }
    	}
    }
    
    /**
     * Result set ordering.
	 */
	public String getOrderByString() {
		return this.orderByString;
	}
    public void setOrderByString(String orderByString) {
		this.orderByString = orderByString;
	}
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }
    
    private static Logger logger = LoggerFactory.getLogger(AbstractAliasFilter.class);

}
