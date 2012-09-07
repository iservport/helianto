package org.helianto.resource.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.core.filter.form.FolderForm;

/**
 * Abstract folder form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractFolderFormFilterAdapter<F extends FolderForm> extends AbstractTrunkFilterAdapter<F> {

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
