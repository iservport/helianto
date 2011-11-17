package org.helianto.core.filter.base;

import org.helianto.core.UserGroup;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.form.NavigableForm;
import org.helianto.core.filter.form.TypeForm;


/**
 * Base class to filters that wrap a form.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <F>
 */
public abstract class AbstractFilterAdapter<F> extends AbstractFilter {
	
	private static final long serialVersionUID = 1L;
	private F form; 

	/**
	 * Constructor.
	 * 
	 * @param form
	 */
	public AbstractFilterAdapter(F form) {
		this.form = form;
		reset();
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
	
	// TODO implement this later
	
//	/**
//	 * True if the form is assignable from ParentForm.
//	 */
//	@Override
//	protected boolean hasParentCriterion() {
//		if (ParentForm.class.isAssignableFrom(getForm().getClass())) {
//			return true;
//		}
//		return super.hasParentCriterion();
//	}
//		
//	@Override
//	public void preProcessParentFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
//		mainCriteriaBuilder.appendAnd(hasPolimorphicCriterion());
//		appendEqualFilter(new StringBuilder(((ParentForm<?>) getForm()).getParentName()).append(".id").toString(), 
//				((ParentForm<?>) getForm()).getParentId(), mainCriteriaBuilder);
//	}
	
	
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
			String nodePath = ((NavigableForm) getForm()).getNodePath();
			return nodePath!=null && nodePath.length()>0;
		}
		return false;
	}
		
	@Override
	public void preProcessNavigableFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (((NavigableForm) getForm()).isStrict()) {
			appendEqualLessCaseFilter("nodePath", ((NavigableForm) getForm()).getNodePath(), mainCriteriaBuilder);
		} 
		else {
			appendStartLikeFilter("nodePath", ((NavigableForm) getForm()).getNodePath(), mainCriteriaBuilder);
		}
		mainCriteriaBuilder.addSegmentCount(1);
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

}
