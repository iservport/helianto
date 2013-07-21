package org.helianto.core.filter.base;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.def.NavigationMode;
import org.helianto.core.filter.FormFilter;
import org.helianto.core.form.NavigableForm;
import org.helianto.core.form.SearchForm;
import org.helianto.core.form.TypeForm;
import org.helianto.user.domain.UserGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Base class to filters that wrap a form.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <F>
 */
public abstract class AbstractFilterAdapter<F> 
	extends AbstractFilter 
	implements FormFilter<F> {
	
	private static final long serialVersionUID = 1L;
	private F form; 

	/**
	 * Constructor.
	 * 
	 * @param form
	 */
	public AbstractFilterAdapter(F form) {
		this.form = form;
	}

	/**
	 * The form.
	 */
	public F getForm() {
		return form;
	}
	
	/**
	 * Internal filter setter.
	 * 
	 * @param form
	 */
	protected void setForm(F form) {
		this.form = form;
	}
	
	/**
	 * True if the form is assignable from TypeForm.
	 */
	protected boolean hasPolimorphicCriterion() {
		if (TypeForm.class.isAssignableFrom(getForm().getClass())) {
			char type = ((TypeForm) getForm()).getType();
			return type!=0 && type!=' ' && type!='_';
		}
		return false;
	}
		
	@Override
	public void preProcessPolimorphicFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("class", ((TypeForm) getForm()).getType(), mainCriteriaBuilder);
		mainCriteriaBuilder.addSegmentCount(1);
	}
	
	/**
	 * True if the form is assignable from NavigableForm.
	 */
	protected boolean hasNavigableCriterion() {
		if (NavigableForm.class.isAssignableFrom(getForm().getClass())) {
			String nodePath = ((NavigableForm) getForm()).getParentPath();
			return nodePath!=null && nodePath.length()>0;
		}
		return false;
	}
		
	@Override
	public void preProcessNavigableFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		NavigationMode navigationMode = ((NavigableForm) getForm()).getNavigationMode();
		logger.debug("Navigation mode is {}", navigationMode);			
		if (navigationMode==null) {
			return;
		}
		else if (navigationMode==NavigationMode.FETCH_SIBLINGS) {
			appendEqualLessCaseFilter("parentPath", ((NavigableForm) getForm()).getParentPath(), mainCriteriaBuilder);
		} 
		else if (navigationMode.equals(NavigationMode.FETCH_DESCENDANTS)) {
			appendStartLikeFilter("parentPath", ((NavigableForm) getForm()).getCurrentPath(), mainCriteriaBuilder);
		}
		else {
			return;
		}
		mainCriteriaBuilder.addSegmentCount(1);
	}
	
	/**
	 * Convenience to get the form as SearchForm, if possible.
	 */
	protected SearchForm getSearchForm() {
		if (getForm() instanceof SearchForm) {
			return (SearchForm) getForm();
		}
		return null;
	}
	
	@Override
	public boolean isSelection() {
		return isSelection(getForm());
	}
	
    /**
     * When true, switch filter to SELECT mode.
     * 
     * <p>
     * Convenient when filter properties correspond to the natural key. By default, filters do not return an unique result.
     * </p>
     */
	public boolean isSelection(F form) {
		return false;
	}
	
	@Override
	public boolean isSearch() {
		if (getSearchForm()!=null) {
			return getSearchForm().getSearchString()!=null 
				&& getSearchForm().getSearchString().length()>0;
		}
		return false;
	}
	
	/**
	 * Array of words to search in fields.
	 */
	@Override
	protected String[] getSearchWords() {
		if (getSearchForm()!=null) {
			if (getSearchForm().getSearchString()!=null) {
				return getSearchForm().getSearchString().split(" ");
			}
		}
		return new String[0];
	}
	
	@Override
	public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof UserGroup) ) return false;
        @SuppressWarnings("unchecked")
		AbstractFilterAdapter<F> castOther = (AbstractFilterAdapter<F>) other; 
        
        return ((this.getForm()==castOther.getForm()) || ( this.getForm()!=null && castOther.getForm()!=null && this.getForm().equals(castOther.getForm()) ));
	}
	
	/**
	 * hashCode
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + (getForm() == null ? 0 : this.getForm().hashCode());
		return result;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractFilterAdapter.class);

}
