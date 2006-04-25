package org.helianto.process;

public interface ProcessCreator {
	
	public Process createProcess(Part part, String processCode);
	
	public Process createProcess(Part part, String processCode, String processName);
	
	public Operation createOperation(Process process, String operationCode);
	
	public Operation createOperation(Process process, String operationCode, String operationName);
	
	public DocumentDetail createOperationDetail(Operation operation);
	
	public Characteristic createCharacteristic(Operation operation);

}
