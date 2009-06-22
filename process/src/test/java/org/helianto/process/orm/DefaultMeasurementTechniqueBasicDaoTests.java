package org.helianto.process.orm;

import org.helianto.core.test.AbstractHibernateBasicDaoTest;
import org.helianto.process.MeasurementTechnique;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultMeasurementTechniqueBasicDaoTests extends AbstractHibernateBasicDaoTest<MeasurementTechnique, DefaultMeasurementTechniqueDao> {


	@Override
	protected MeasurementTechnique doCreateTarget() {
		return new MeasurementTechnique();
	}

	@Override
	protected DefaultMeasurementTechniqueDao doCreateDao() {
		return new DefaultMeasurementTechniqueDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select measurementtechnique from MeasurementTechnique measurementtechnique ";
	}

}

