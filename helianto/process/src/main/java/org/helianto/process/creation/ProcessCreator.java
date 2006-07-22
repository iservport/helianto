package org.helianto.process.creation;

import org.helianto.process.Characteristic;
import org.helianto.process.DocumentDetail;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;

public interface ProcessCreator {
	
    public Process processFactory(Part part, String processCode);

    public Process processFactory(Part part, String processCode,
            String processName);

    public Operation operationFactory(Process process, String operationCode);

    public Operation operationFactory(Process process, String operationCode,
            String operationName);

    public DocumentDetail operationDetailFactory(Operation operation);

    public Characteristic characteristicFactory(Operation operation);

}
