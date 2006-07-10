package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.process.Characteristic;
import org.helianto.process.DocumentDetail;
import org.helianto.process.MaterialType;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Unit;

public interface ProcessCreator {
	
    public Process processFactory(Part part, String processCode);

    public Process processFactory(Part part, String processCode,
            String processName);

    public Operation operationFactory(Process process, String operationCode);

    public Operation operationFactory(Process process, String operationCode,
            String operationName);

    public DocumentDetail operationDetailFactory(Operation operation);

    public Characteristic characteristicFactory(Operation operation);

    public Unit unitFactory(Entity entity, String unitCode, String unitName);

    public Unit unitFactory(Unit parent, String unitCode, String unitName);

    public MaterialType materialFactory(Unit unit, String materialName);

    public MaterialType materialFactory(MaterialType parent, Unit unit,
            String materialName);

}
