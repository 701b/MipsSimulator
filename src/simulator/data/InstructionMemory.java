/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator.data;

import java.util.ArrayList;
import java.util.List;

import simulator.Converter;
import simulator.instruction.*;

public class InstructionMemory {
	
	public static final int MAXIMUM_OF_ADDRESS = 0x00010000;
	
	public byte[] memory;
	

	public InstructionMemory() {
		memory = new byte[MAXIMUM_OF_ADDRESS];
		
		init();
	}
	
	
	public void init() {
		for (int i = 0; i < MAXIMUM_OF_ADDRESS; i++) {
			memory[i] = (byte) 0xFF;
		}
	}
	
	public void storeInstruction(byte[] instructionCode) {
		for (int i = 0; i < instructionCode.length; i++) {
			memory[i] = instructionCode[i]; 
		}
	}

	public Instruction getInstruction(int address) {
		Instruction instruction;
		byte[] machineCodes = new byte[4];
		int word;
		int opcodeNumber;

		try {
			for (int j = 0; j < 4; j++) {
				machineCodes[j] = memory[address + j];
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new MemoryAddressOutOfRangeException(address);
		}

		word = Converter.fourByteToInt(machineCodes);
		opcodeNumber = (word & 0xFC000000) >>> 26;

		switch (Opcode.intToOpcode(opcodeNumber)) {
			case R_FORMAT:
				instruction = new RFormatInstruction(word);
				break;

			case ADDI:
			case ADDIU:
			case ANDI:
			case LUI:
			case ORI:
			case SLTI:
			case SLTIU:
			case XORI:
			case BEQ:
			case BNE:
			case LB:
			case LBU:
			case LH:
			case LHU:
			case LW:
			case SB:
			case SW:
				instruction = new IFormatInstruction(word);
				break;

			case J:
			case JAL:
				instruction = new JFormatInstruction(word);
				break;

			default:
				instruction = new RFormatInstruction(word);
				break;
		}

		return instruction;
	}
	
	public List<Instruction> getInstructionList() {
		List<Instruction> instructionList = new ArrayList<>();
		
		for (int i = 0; i < MAXIMUM_OF_ADDRESS; i += 4) {
			instructionList.add(getInstruction(i));
		}
		
		return instructionList;
	}
}
