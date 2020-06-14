/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.instruction;

public class RFormatInstruction extends AbstractInstruction {
	
	public RFormatInstruction(int instructionCode) {
		super(instructionCode);
	}
	
	public int getFirstSourceRegister() {
		int firstSourceRegisterNumber = (instructionCode << 6) >>> 27;
		
		return firstSourceRegisterNumber;
	}
	
	public int getSecondSourceRegister() {
		int secondSourceRegisterNumber = (instructionCode << 11) >>> 27;
		
		return secondSourceRegisterNumber;
	}
	
	public int getDestinationRegister() {
		int destinationRegisterNumber = (instructionCode << 16) >>> 27;
		
		return destinationRegisterNumber;
	}
	
	public int getShiftAmount() {
		int shiftAmount = (instructionCode << 21) >>> 27;
		
		return shiftAmount;
	}
	
	public FunctionCode getFunctionCode() {
		FunctionCode functionCode = FunctionCode.intToFunctionCode((instructionCode << 26) >>> 26);
		
		return functionCode;
	}
	
}
