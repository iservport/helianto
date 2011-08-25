package org.helianto.process.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractTrunkFilterAdapter;
import org.helianto.process.MeasurementTechnique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Measurement technique filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class MeasurementTechniqueFilterAdapter<T extends MeasurementTechniqueForm> extends AbstractTrunkFilterAdapter<T> {

    private static final long serialVersionUID = 1L;
	private Class<? extends MeasurementTechnique> clazz;

    /**
     * Default constructor.
     * 
     * @param processDocument
     */
    public MeasurementTechniqueFilterAdapter(T measurementTechnique) {
    	super(measurementTechnique);
    	reset();
    }
    
    /**
     * Force filter to standards.
     */
    public void reset() {
    }
    
	@Override
	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (getClazz()!=null && !getClazz().equals(MeasurementTechnique.class)) {
	        logger.debug("Measurement technique class is: '{}'", getClazz());
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
	}
	
	@Override
	public boolean isSelection() {
		return getForm().getMeasurementTechniqueCode()!=null && getForm().getMeasurementTechniqueCode().length()>0;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("measurementTechniqueCode", getForm().getMeasurementTechniqueCode(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("measurementTechniqueName", getForm().getMeasurementTechniqueName(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "measurementTechniqueCode";
	}

	/**
	 * Subclass
	 */
	public Class<? extends MeasurementTechnique> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends MeasurementTechnique> clazz) {
		this.clazz = clazz;
	}

	private static Logger logger = LoggerFactory.getLogger(MeasurementTechniqueFilterAdapter.class);

}
