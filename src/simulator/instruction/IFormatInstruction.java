/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.instruction;

public class IFormatInstruction extends AbstractInstruction {
	
	public IFormatInstruction(int instructionCode) {
		super(instructionCode);
	}
	
	public int getSourceRegister() {
		int sourceRegisterNumber = (instructionCode << 6) >>> 27;
		
		return sourceRegisterNumber;
	}
	
	public int getDestinationRegister() {
		int destinationRegisterNumber = (instructionCode << 11) >>> 27;
		
		return destinationRegisterNumber;
	}
	
	public short getConstant() {
		short constant = (short) ((instructionCode << 16) >>> 16);
		
		return constant;
	}

}
