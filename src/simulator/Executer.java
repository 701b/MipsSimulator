/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

package simulator;

import simulator.data.*;
import simulator.instruction.FunctionCode;
import simulator.instruction.IFormatInstruction;
import simulator.instruction.Instruction;
import simulator.instruction.JFormatInstruction;
import simulator.instruction.Opcode;
import simulator.instruction.RFormatInstruction;

public class Executer {
	
	private Register register;
	private DataMemory dataMemory;
	

	public Executer(Register register, DataMemory dataMemory) {
		this.register = register;
		this.dataMemory = dataMemory;
	}
	
	
	public boolean execute(Instruction instruction) throws SyscallExitException, InvalidSyscallException {
		if (instruction instanceof RFormatInstruction) {
			return executeRFormat((RFormatInstruction) instruction);
		} else if (instruction instanceof IFormatInstruction) {
			return executeIFormat((IFormatInstruction) instruction);
		} else if (instruction instanceof JFormatInstruction) {
			return executeJFormat((JFormatInstruction) instruction);
		}
		
		return false;
	}

	private boolean executeRFormat(RFormatInstruction rFormatInstruction) throws SyscallExitException, InvalidSyscallException {
		int firstSrcReg = rFormatInstruction.getFirstSourceRegister();
		int secondSrcReg = rFormatInstruction.getSecondSourceRegister();
		int destReg = rFormatInstruction.getDestinationRegister();
		int shiftAmount = rFormatInstruction.getShiftAmount();
		
		long temp1;
		long temp2;
		
		if (rFormatInstruction.getFunctionCode() == FunctionCode.UNKNOWN) {
			register.pcReg += 4;
			return false;
		}
		
		switch (rFormatInstruction.getFunctionCode()) {
			case ADD:
				register.reg[destReg] = register.reg[firstSrcReg] + register.reg[secondSrcReg];
					
				register.pcReg += 4;
				break;

			case ADDU:
				register.reg[destReg] = register.reg[firstSrcReg] + register.reg[secondSrcReg];
				
				register.pcReg += 4;
				break;
				
			case AND:
				register.reg[destReg] = register.reg[firstSrcReg] & register.reg[secondSrcReg];
				
				register.pcReg += 4;
				break;
				
			case NOR:
				register.reg[destReg] = ~(register.reg[firstSrcReg] | register.reg[secondSrcReg]);
				
				register.pcReg += 4;
				break;
				
			case OR:
				register.reg[destReg] = register.reg[firstSrcReg] | register.reg[secondSrcReg];
				
				register.pcReg += 4;
				break;
				
			case SLT:
				if (register.reg[firstSrcReg] < register.reg[secondSrcReg]) {
					register.reg[destReg] = 1;
				} else {
					register.reg[destReg] = 0;
				}
				
				register.pcReg += 4;
				break;
				
			case SLTU:
				temp1 = register.reg[firstSrcReg] & 0XFFFFFFFFL;
				temp2 = register.reg[secondSrcReg] & 0xFFFFFFFFL;
				
				if (temp1 < temp2) {
					register.reg[destReg] = 1;
				} else {
					register.reg[destReg] = 0;
				}
				
				register.pcReg += 4;
				break;
				
			case SUB:
				register.reg[destReg] = register.reg[firstSrcReg] - register.reg[secondSrcReg];

				register.pcReg += 4;
				break;
				
			case SUBU:
				register.reg[destReg] = register.reg[firstSrcReg] - register.reg[secondSrcReg];
				
				register.pcReg += 4;
				break;
				
			case XOR:
				register.reg[destReg] = register.reg[firstSrcReg] ^ register.reg[secondSrcReg];
				
				register.pcReg += 4;
				break;
				
			case SLL:
				register.reg[destReg] = register.reg[secondSrcReg] << shiftAmount;
				
				register.pcReg += 4;
				break;
				
			case SLLV:
				register.reg[destReg] = register.reg[secondSrcReg] << (register.reg[firstSrcReg] & 0xF);
				
				register.pcReg += 4;
				break;
				
			case SRA:
				register.reg[destReg] = register.reg[secondSrcReg] >> shiftAmount;
				
				register.pcReg += 4;
				break;
				
			case SRAV:
				register.reg[destReg] = register.reg[secondSrcReg] >> (register.reg[firstSrcReg] & 0xF);
				
				register.pcReg += 4;
				break;
				
			case SRL:
				register.reg[destReg] = register.reg[secondSrcReg] >>> shiftAmount;
				
				register.pcReg += 4;
				break;
				
			case SRLV:
				register.reg[destReg] = register.reg[secondSrcReg] >>> (register.reg[firstSrcReg] & 0xF);
				
				register.pcReg += 4;
				break;
				
			case DIV:
				register.hiReg = register.reg[firstSrcReg] % register.reg[secondSrcReg];
				register.loReg = register.reg[firstSrcReg] / register.reg[secondSrcReg]; 
				
				register.pcReg += 4;
				break;
				
			case DIVU:
				temp1 = register.reg[firstSrcReg] & 0XFFFFFFFFL;
				temp2 = register.reg[secondSrcReg] & 0xFFFFFFFFL;
				
				register.hiReg = (int) (temp1 % temp2);
				register.loReg = (int) (temp1 / temp2);
				
				register.pcReg += 4;
				break;
				
			case MFHI:
				register.reg[destReg] = register.hiReg;
				
				register.pcReg += 4;
				break;
				
			case MFLO:
				register.reg[destReg] = register.loReg;
				
				register.pcReg += 4;
				break;
				
			case MTHI:
				register.hiReg = register.reg[firstSrcReg];
				
				register.pcReg += 4;
				break;
				
			case MTLO:
				register.loReg = register.reg[firstSrcReg];
				
				register.pcReg += 4;
				break;
				
			case MULT:
				temp1 = ((long) register.reg[firstSrcReg]) * ((long) register.reg[secondSrcReg]);
				
				register.hiReg = (int) (temp1 >>> 32);
				register.loReg = (int) temp1;
				
				register.pcReg += 4;
				break;
				
			case MULTU:
				temp1 = register.reg[firstSrcReg] & 0XFFFFFFFFL;
				temp2 = register.reg[secondSrcReg] & 0xFFFFFFFFL;
				
				temp1 = temp1 * temp2;
				
				register.hiReg = (int) (temp1 >>> 32);
				register.loReg = (int) temp1;
				
				register.pcReg += 4;
				break;
				
			case JALR:
				break;
				
			case JR:
				register.pcReg = register.reg[firstSrcReg];
				break;
				
			case SYSCALL:
				switch (register.reg[Register.RESULTS]) {
					case 1:
						System.out.print(register.reg[Register.FIRST_ARGUMENT]);
						break;

					case 4:
						for (int index = 0; ; index++) {
							char c = (char) dataMemory.getMemory(register.reg[Register.FIRST_ARGUMENT] + index);

							if (c == 0) {
								break;
							}

							System.out.print(c);
						}

						break;

					case 10:
						register.pcReg += 4;
						throw new SyscallExitException();

					default:
						register.pcReg += 4;
						throw new InvalidSyscallException();
				}

				register.pcReg += 4;
				break;
			
			default:
				return false;
		}
		
		return true;
	}
	
	private boolean executeIFormat(IFormatInstruction iFormatInstruction) {
		int destReg = iFormatInstruction.getDestinationRegister();
		int srcReg = iFormatInstruction.getSourceRegister();
		short constant = iFormatInstruction.getConstant();
		
		if (iFormatInstruction.getOpcode() == Opcode.UNKNOWN) {
			register.pcReg += 4;
			return false;
		}

		try {
			switch (iFormatInstruction.getOpcode()) {
				case ADDI:
					register.reg[destReg] = register.reg[srcReg] + constant;

					register.pcReg += 4;
					break;

				case ADDIU:
					register.reg[destReg] = register.reg[srcReg] + constant;

					register.pcReg += 4;
					break;

				case ANDI:
					register.reg[destReg] = register.reg[srcReg] & (constant & 0x0000FFFF);

					register.pcReg += 4;
					break;

				case LUI:
					register.reg[destReg] = constant << 16;

					register.pcReg += 4;
					break;

				case ORI:
					register.reg[destReg] = register.reg[srcReg] | (constant & 0x0000FFFF);

					register.pcReg += 4;
					break;

				case SLTI:
					if (register.reg[srcReg] < constant) {
						register.reg[destReg] = 1;
					} else {
						register.reg[destReg] = 0;
					}

					register.pcReg += 4;
					break;

				case SLTIU:
					long temp1 = (register.reg[srcReg] & 0xFFFFFFFFL);
					long temp2 = (((int) constant) & 0xFFFFFFFFL);

					if (temp1 < temp2) {
						register.reg[destReg] = 1;
					} else {
						register.reg[destReg] = 0;
					}

					register.pcReg += 4;
					break;

				case XORI:
					register.reg[destReg] = register.reg[srcReg] ^ (constant & 0x0000FFFF);

					register.pcReg += 4;
					break;

				case LW:
					if ((register.reg[srcReg] + constant) % 4 != 0) {
						register.pcReg += 4;
						throw new MemoryAddressAlignmentException(register.reg[srcReg] + constant);
					}


					register.reg[destReg] = ((dataMemory.getMemory(register.reg[srcReg] + constant) & 0xFF) << 24)
											| ((dataMemory.getMemory(register.reg[srcReg] + constant + 1) & 0xFF) << 16)
											| ((dataMemory.getMemory(register.reg[srcReg] + constant + 2) & 0xFF) << 8)
											| (dataMemory.getMemory(register.reg[srcReg] + constant + 3) & 0xFF);


					register.pcReg += 4;
					break;

				case LH:
					if ((register.reg[srcReg] + constant) % 2 != 0) {
						register.pcReg += 4;
						throw new MemoryAddressAlignmentException(register.reg[srcReg] + constant);
					}

					register.reg[destReg] = (dataMemory.getMemory(register.reg[srcReg] + constant) << 8)
											| (dataMemory.getMemory(register.reg[srcReg] + constant + 1) & 0x000000FF);

					register.pcReg += 4;
					break;

				case LHU:
					if ((register.reg[srcReg] + constant) % 2 != 0) {
						register.pcReg += 4;
						throw new MemoryAddressAlignmentException(register.reg[srcReg] + constant);
					}

					register.reg[destReg] = ((dataMemory.getMemory(register.reg[srcReg] + constant) & 0x000000FF) << 8)
											| (dataMemory.getMemory(register.reg[srcReg] + constant + 1) & 0x000000FF);

					register.pcReg += 4;
					break;

				case LB:
					register.reg[destReg] = dataMemory.getMemory(register.reg[srcReg] + constant);

					register.pcReg += 4;
					break;

				case LBU:
					register.reg[destReg] = dataMemory.getMemory(register.reg[srcReg] + constant) & 0x000000FF;

					register.pcReg += 4;
					break;

				case SW:
					if ((register.reg[srcReg] + constant) % 4 != 0) {
						register.pcReg += 4;
						throw new MemoryAddressAlignmentException(register.reg[srcReg] + constant);
					}

					dataMemory.setMemory((byte) ((register.reg[destReg] & 0xFF000000) >>> 24), register.reg[srcReg] + constant);
					dataMemory.setMemory((byte) ((register.reg[destReg] & 0x00FF0000) >>> 16), register.reg[srcReg] + constant + 1);
					dataMemory.setMemory((byte) ((register.reg[destReg] & 0x0000FF00) >>> 8), register.reg[srcReg] + constant + 2);
					dataMemory.setMemory((byte) ((register.reg[destReg] & 0x000000FF)), register.reg[srcReg] + constant + 3);

					register.pcReg += 4;
					break;

				case SH:
					if ((register.reg[srcReg] + constant) % 2 != 0) {
						register.pcReg += 4;
						throw new MemoryAddressAlignmentException(register.reg[srcReg] + constant);
					}

					dataMemory.setMemory((byte) ((register.reg[destReg] & 0x0000FF00) >>> 8), register.reg[srcReg] + constant);
					dataMemory.setMemory((byte) (register.reg[destReg] & 0x000000FF), register.reg[srcReg] + constant + 1);

					register.pcReg += 4;
					break;

				case SB:
					dataMemory.setMemory((byte) (0xFF & register.reg[destReg]), register.reg[srcReg] + constant);

					register.pcReg += 4;
					break;

				case BEQ:
					if (register.reg[srcReg] == register.reg[destReg]) {
						register.pcReg += ((int) constant) << 2;
					}

					register.pcReg += 4;
					break;

				case BNE:
					if (register.reg[srcReg] != register.reg[destReg]) {
						register.pcReg += ((int) constant) << 2;;
					}

					register.pcReg += 4;
					break;

				default:
					return false;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			register.pcReg += 4;
			throw new MemoryAddressOutOfRangeException((register.reg[srcReg] + constant));
		}
		
		return true;
	}
	
	private boolean executeJFormat(JFormatInstruction instruction) {
		int address = instruction.getAddress();
		
		switch (instruction.getOpcode()) {
			case J:
				register.pcReg = (register.pcReg & 0xF0000000) | (address << 2);
				break;
				
			case JAL:
				register.reg[Register.RETURN_ADDRESS] = register.pcReg + 4;
				register.pcReg = (register.pcReg & 0xF0000000) | (address << 2);
				break;
				
			default:
				return false;
		}
		
		return true;
	}
	
}
