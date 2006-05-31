package org.helianto.process;

import org.helianto.process.Characteristic;
import org.helianto.process.DocumentDetail;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;

public interface ProcessCreator {
	
	public Process createProcess(Part part, String processCode);
	
	public Process createProcess(Part part, String processCode, String processName);
	
	public Operation createOperation(Process process, String operationCode);
	
	public Operation createOperation(Process process, String operationCode, String operationName);
	
	public DocumentDetail createOperationDetail(Operation operation);
	
	public Characteristic createCharacteristic(Operation operation);

}
