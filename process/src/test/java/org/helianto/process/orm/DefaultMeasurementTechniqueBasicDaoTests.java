package org.helianto.process.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.process.MeasurementTechnique;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultMeasurementTechniqueBasicDaoTests extends AbstractBasicDaoTest<MeasurementTechnique, DefaultMeasurementTechniqueDao> {


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

