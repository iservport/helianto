package org.helianto.document.filter.internal;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.internal.AbstractEntityIdFilterAdapter;
import org.helianto.core.form.FolderForm;

/**
 * Abstract folder form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractFolderFormFilterAdapter<F extends FolderForm> 
	extends AbstractEntityIdFilterAdapter<F> 
{

	private static final long serialVersionUID = 1L;

	public AbstractFolderFormFilterAdapter(F form) {
		super(form);
	}
	
	@Override
	public boolean isSelection() {
		return super.isSelection() && getForm().getFolderCode()!=null && getForm().getFolderCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("folderCode", getForm().getFolderCode(), mainCriteriaBuilder);
	}

	@Override
	public String getOrderByString() {
		return "folderCode ASC";
	}

}
