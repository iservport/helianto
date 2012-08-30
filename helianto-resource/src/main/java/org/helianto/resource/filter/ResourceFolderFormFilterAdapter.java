package org.helianto.resource.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.resource.form.ResourceFolderForm;

/**
 * Resource folder form filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class ResourceFolderFormFilterAdapter extends AbstractTrunkFilterAdapter<ResourceFolderForm> {

	private static final long serialVersionUID = 1L;

	public ResourceFolderFormFilterAdapter(ResourceFolderForm form) {
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
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("resourceType", getForm().getResourceType(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "folderCode ASC";
	}

}
