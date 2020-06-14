/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.instruction;

public abstract class AbstractInstruction implements Instruction {
	
	protected int instructionCode;
	
	public AbstractInstruction(int instructionCode) {
		this.instructionCode = instructionCode;
	}

	@Override
	public int getInstructionCode() {
		return instructionCode;
	}

	@Override
	public Opcode getOpcode() {
		Opcode opcode = Opcode.intToOpcode(instructionCode >>> 26);
		
		return opcode;
	}

}