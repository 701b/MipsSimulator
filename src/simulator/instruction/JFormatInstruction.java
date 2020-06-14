/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.instruction;

public class JFormatInstruction extends AbstractInstruction {

	public JFormatInstruction(int instructionCode) {
		super(instructionCode);
	}
	
	public int getAddress() {
		int address = (instructionCode << 6) >>> 6;
		
		return address;
	}
	
}
